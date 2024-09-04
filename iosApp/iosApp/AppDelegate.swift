
import Foundation
import UIKit
import ComposeApp

class AppDelegate : NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        initiateSdk(grpcClientFactory: GrpcFactory())
        return true
    }
}



