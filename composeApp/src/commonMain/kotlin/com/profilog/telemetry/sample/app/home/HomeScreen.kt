package com.profilog.telemetry.sample.app.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    model: HomeScreenViewModel = viewModel { HomeScreenViewModel() }
) {
    val user by model.user.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Telemetry sample") },
                actions = {
                    user?.also {
                        Text(it.name)
                        AnimatedVisibility(it.isLogged) {
                            Icon(Icons.Default.Done, null)
                        }
                    }

                    IconButton(
                        onClick = { model.logout() }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, null)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(padding),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MenuItem("Services") {
                    navHostController.navigate("services")
                }
            }

            item {
                MenuItem("Daily steps") {
                    navHostController.navigate("steps")
                }
            }

            item {
                MenuItem("Raw Steps") {
                    navHostController.navigate("rawSteps")
                }
            }

            item {
                MenuItem("Sleep") {
                    navHostController.navigate("sleep")
                }
            }

            item {
                MenuItem("Weight + Height") {
                    navHostController.navigate("weightHeight")
                }
            }

            item {
                MenuItem("Devices") {
                    navHostController.navigate("myDevices")
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text, style = MaterialTheme.typography.titleLarge)
            Icon(Icons.AutoMirrored.Filled.NavigateNext, null)
        }
    }
}