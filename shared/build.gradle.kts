import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            linkerOpts(
                "-lsqlite3",
                "-L/Applications/Xcode.app/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/usr/lib"
            )
        }
    }



    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.serialization.json)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.sqldelight.runtime)
                implementation(libs.napier)
                implementation(libs.kotlinx.datetime)
                implementation(libs.multiplatform.settings)
                implementation(libs.koin.core)
                implementation(libs.sqldelight.extensions)
                implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
                implementation(libs.sqldelight.android)
                implementation(libs.koin.android)
            }
        }

        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.darwin)
                implementation(libs.sqldelight.native)
            }
        }

        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

        iosMain.dependsOn(commonMain)
    }
}

android {
    namespace = "com.anvipus.explore.kmp.shared"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
}

val sdkName: String = System.getenv("SDK_NAME") ?: "iphonesimulator"
val targetName = when {
    sdkName.startsWith("iphoneos") -> "iosArm64"
    sdkName.startsWith("iphonesimulator") -> "iosX64"
    else -> throw GradleException("Unknown SDK_NAME: $sdkName")
}

val framework = (kotlin.targets.getByName(targetName) as KotlinNativeTarget)
    .binaries.getFramework("Release")

tasks.register<Sync>("syncFramework") {
    val outputDir = File(buildDir, "XCFrameworks/Release")
    dependsOn(framework.linkTask)
    from({ framework.outputDirectory })
    into(outputDir)
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.anvipus.explore.kmp.db"
        sourceFolders = listOf("sqldelight")
    }
}

