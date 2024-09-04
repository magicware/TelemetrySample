import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView().onOpenURL(perform: { url in
                UrlHandler.shared.handle(url: url)
            })
        }
    }
}
