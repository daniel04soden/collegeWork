package com.example.assignment1.Views

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.navigation.NavController
import com.example.assignment1.ViewModels.UserViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel: UserViewModel){
    val currentUser by viewModel.currentUser.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var steps by remember { mutableLongStateOf(0L) }

    val healthConnectClient = remember { HealthConnectClient.getOrCreate(context) }
    val permissions = setOf(HealthPermission.getReadPermission(StepsRecord::class))

    suspend fun readSteps(healthConnectClient: HealthConnectClient, onStepsRead: (Long) -> Unit) {
        try {
            val end = LocalDateTime.now()
            val start = end.toLocalDate().atStartOfDay()
            val request = AggregateRequest(
                metrics = setOf(StepsRecord.COUNT_TOTAL),
                timeRangeFilter = TimeRangeFilter.between(start, end)
            )
            val result = healthConnectClient.aggregate(request)
            onStepsRead(result[StepsRecord.COUNT_TOTAL] ?: 0L)
        } catch (e: Exception) {
            Log.d("HealthConnect", "Error reading steps", e)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissionsMap ->
        if (permissionsMap.values.all { it }) {
            coroutineScope.launch {
                readSteps(healthConnectClient) { stepCount ->
                    steps = stepCount
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val granted = healthConnectClient.permissionController.getGrantedPermissions()
        if (granted.containsAll(permissions)) {
            readSteps(healthConnectClient) { stepCount ->
                steps = stepCount
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title= {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Improvio: Home")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                currentUser?.let { WelcomeCard(user = it, activity = "Home") }
                Spacer(modifier = Modifier.height(20.dp))

                Text("Today's Steps: $steps")
                Button(onClick = { permissionLauncher.launch(permissions.toTypedArray()) }) {
                    Text("Sync Steps")
                }

                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Journalling",
                    viewModel.currentUser.value?.journalStreak ?: 0 ,
                    onClick = {
                        navController.navigate("journalling")
                    })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Meditation",
                    viewModel.currentUser.value?.meditationStreak ?: 0 ,
                    onClick = {
                        navController.navigate("meditation")
                    })
                Spacer(modifier = Modifier.height(20.dp))
                NavButton(
                    "Food Tracking",
                    viewModel.currentUser.value?.calorieStreak ?: 0 ,
                    onClick = {
                        navController.navigate("food")
                    })
            }
        }
    )
}
