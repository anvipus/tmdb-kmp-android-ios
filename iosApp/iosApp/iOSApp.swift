import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinIosKt.doInitKoinIos()
        //KoinBridge().start()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
