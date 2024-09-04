package com.profilog.telemetry.sample.app.home.samples

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.profilog.telemetry.sample.app.home.samples.devices.AddDeviceScreen
import com.profilog.telemetry.sample.app.home.samples.devices.DevicesScreen
import com.profilog.telemetry.sample.app.home.samples.rawSteps.RawStepsScreen
import com.profilog.telemetry.sample.app.home.samples.services.ServicesScreen
import com.profilog.telemetry.sample.app.home.samples.sleep.SleepScreen
import com.profilog.telemetry.sample.app.home.samples.steps.StepsScreen
import com.profilog.telemetry.sample.app.home.samples.weightHeight.WeightHeightScreen

fun NavGraphBuilder.samplesNavigation(navHostController: NavHostController) {
    composable("services") {
        ServicesScreen {
            navHostController.navigateUp()
        }
    }
    composable("steps") {
        StepsScreen {
            navHostController.navigateUp()
        }
    }
    composable("rawSteps") {
        RawStepsScreen {
            navHostController.navigateUp()
        }
    }

    composable("sleep") {
        SleepScreen {
            navHostController.navigateUp()
        }
    }

    composable("weightHeight") {
        WeightHeightScreen {
            navHostController.navigateUp()
        }
    }

    navigation(startDestination = "myDevices", route = "devices") {
        composable("myDevices") {
            DevicesScreen(
                onAddDeviceClick = { navHostController.navigate("addDevice") }
            ) {
                navHostController.navigateUp()
            }
        }
        composable("addDevice") {
            AddDeviceScreen {
                navHostController.navigateUp()
            }
        }
    }
}