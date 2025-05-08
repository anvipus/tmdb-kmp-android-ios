import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    let repository = MovieRepositoryFactoryKt.createMovieRepository()
                    let movies = repository.getPopularMovies()
                    
                    // Log jumlah film yang berhasil diambil
                        print("✅ Fetched \(movies.count) movies")

                        // Log isi detail tiap film (jika Movie punya property seperti title)
                        for movie in movies {
                            print("🎬 \(movie.title ?? "No Title")")
                        }
                    
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundColor(.accentColor)
                    Text("SwiftUI: \(Greeting().greet())")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
