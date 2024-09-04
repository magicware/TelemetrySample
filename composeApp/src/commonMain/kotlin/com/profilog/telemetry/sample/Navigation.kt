package com.profilog.telemetry.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.profilog.telemetry.sample.app.home.HomeScreen
import com.profilog.telemetry.sample.app.login.LoginScreen
import com.profilog.telemetry.sample.app.home.samples.rawSteps.RawStepsScreen
import com.profilog.telemetry.sample.app.home.samples.samplesNavigation
import com.profilog.telemetry.sample.app.home.samples.services.ServicesScreen
import com.profilog.telemetry.sample.app.home.samples.sleep.SleepScreen
import com.profilog.telemetry.sample.app.home.samples.steps.StepsScreen

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navHostController,
        startDestination = "home"
    ) {
        composable("login") {
            LoginScreen()
        }
        composable("home") {
            HomeScreen(navHostController)
        }

        samplesNavigation(navHostController)
    }
}