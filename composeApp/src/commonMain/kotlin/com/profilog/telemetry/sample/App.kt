package com.profilog.telemetry.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    appViewModel: AppViewModel = viewModel { AppViewModel() }
) {
    MaterialTheme {

        val navController = rememberNavController()
        Navigation(navController)

        val user by appViewModel.user.collectAsStateWithLifecycle()

        when {
            user == null -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }

            user!!.isBlank() -> {
                LaunchedEffect(Unit) {
                    navController.navigate("login")
                }
            }

            else -> {
                LaunchedEffect(Unit) {
                    navController.navigate("home")
                }
            }
        }
    }
}