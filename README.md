# Telemetry sdk installation

- kotlin 2.0.10
- minSdk = 24
- compileSdk = 34

- add maven repositories into build.gradle
```
// repository of Telemetry SDK
maven("https://mymavenrepo.com/repo/q4vqkfpPHT4X1N7Qxui5/") 
```
## Android

- coreLibraryDesugaring must be enabled in build.gradle

```
android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    dependencies {
        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    }
}
```

### Health Connect
configuration instructions can be found at https://profi-log.net/docs/telemetry/current/telemetry/telemetry/com.profilog.telemetry.records.services/-health-connect/index.html

## iOS

### GRPC
Telemetry SDK uses GRPC to communicate with backend server. GRPC must be implemented in ios app.
#### in build.gradle 
- apply plugin co.touchlab.skie https://skie.touchlab.co/Installation (this plugin generates swift friendly code to consume suspend/flow from ios app)
- expose Telemetry SDK to ios app
```
kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true

            export("com.profilog.telemetry:telemetry:5.0.0-alpha02")
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api("com.profilog.telemetry:telemetry:5.0.0-alpha02")
        }
    }
}
```

#### in ios app
- install Google GRPC swift package https://github.com/grpc/grpc-swift.git to ios app
- add all swift files located in Grpc directory (https://github.com/magicware/TelemetrySample/tree/main/iosApp/iosApp/Grpc). If needed, replace 'import ComposeApp' for your framework name.
- initialize SDK in UIApplicationDelegate application(_:didFinishLaunchingWithOptions:)
```
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    initiateSdk(grpcClientFactory: GrpcFactory())
    return true
}
```
```
fun initiateSdk(grpcClientFactory: GrpcClientFactory) {
    TelemetryInitializer.initialize(
        grpcClientFactory = grpcClientFactory
    )
}
```

### URL handling
Telemetry SDK needs to be notified about urls opening application (result handler for external service connection)
- add CFBundleURLTypes into info.plist (ios app will be opened for urls with scheme $(PRODUCT_BUNDLE_IDENTIFIER))
```
<key>CFBundleURLTypes</key>
<array>
    <dict>
		<key>CFBundleURLSchemes</key>
		<array>
			<string>$(PRODUCT_BUNDLE_IDENTIFIER)</string>
		</array>
	</dict>
</array>
```
- add onOpenURL(perform:) to View with UrlHandler.shared.handle(url: url)
```
var body: some Scene {
    WindowGroup {
        ContentView().onOpenURL(perform: { url in
            UrlHandler.shared.handle(url: url)
        })
    }
}
```
### Background processing
Telemetry SKD uses background processing
- add UIBackgroundModes (processing) into info.plist
```
<key>UIBackgroundModes</key>
    <array>
	    <string>processing</string>
</array>
```
- add background task identifier $(PRODUCT_BUNDLE_IDENTIFIER).bgtask into BGTaskSchedulerPermittedIdentifiers
```
<key>BGTaskSchedulerPermittedIdentifiers</key>
<array>
	<string>$(PRODUCT_BUNDLE_IDENTIFIER).bgtask</string>
</array>
```
### Apple Health
- in Signing & Capabilities add HealthKit capability
- add NSHealthShareUsageDescription with user readable message to info.plist
```
<key>NSHealthShareUsageDescription</key>
<string>App needs to be allowed to read data from HK</string>
```

### Bluetooth
- add NSBluetoothAlwaysUsageDescription to info.plist
```
<key>NSBluetoothAlwaysUsageDescription</key>
<string>Bluetooth is required for connecting to devices</string>
```

## Devices
Devices are available via DeviceManager.
Each device family is developed as separate plugin and must be included via build.gradle. Currently only Beurer plugin is provided as multiplatform plugin.

### build.gradle
```
sourceSets {
    commonMain.dependencies {
        ...
        implementation("com.profilog.telemetry.devices.beurer:plugin:2.0.0")
        ...
    }
}
```
### iOS app
Each plugin must be included into TelemetryInitializer.initialize fun.
```
fun initiateSdk(grpcClientFactory: GrpcClientFactory) {
    TelemetryInitializer.initialize(
        grpcClientFactory = grpcClientFactory,
        devicePluginInitializers = setOf(BeurerPluginInitializer())
    )
}
```

# Telemetry Sample App Usage
Telemetry SDK need to be configured by ConfigurationRepository.setup fun. It is used in AppViewModel. Please fill there your project configuration:
```
const val PROJECT_CODE = "my_project_code"
const val PROJECT_URI = "https://project_uri"
const val PROJECT_SECRET = "my_project_secret_key"
```
After startup, app requires filling user name. This user is authenticated internally by SDK. Authentication is observable in ConfigurationRepository.configuration()

In commonMain/.../app/home/samples you can find some sample usages of SDK.

# Key docs
SDK setup
- https://profi-log.net/docs/telemetry/current/telemetry/telemetry/com.profilog.telemetry.config/-configuration-repository/setup.html
- https://profi-log.net/docs/telemetry/current/telemetry/telemetry/com.profilog.telemetry.config/-configuration-repository/configuration.html

Services
- https://profi-log.net/docs/telemetry/current/telemetry/telemetry/com.profilog.telemetry.records.services/-external-service-repository/index.html

Records
- https://profi-log.net/docs/telemetry/current/telemetry/records/com.profilog.telemetry.records/-records-client/index.html

Devices
- https://profi-log.net/docs/telemetry/current/telemetry/telemetry/com.profilog.telemetry.devices/-device-manager/index.html
