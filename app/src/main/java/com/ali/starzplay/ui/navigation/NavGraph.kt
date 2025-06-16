package com.ali.starzplay.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ali.starzplay.ui.screen.NavGraphs
import com.ali.starzplay.ui.screen.destinations.PlayerScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navHostEngine = rememberNavHostEngine()
    val navController = navHostEngine.rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentDestination != PlayerScreenDestination.route) {
                TopAppBar(
                    title = { Text("Starz Play") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Black.copy(0.05f)
                    )
                )
            }
        }
    ) { innerPadding ->
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            engine = navHostEngine,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}