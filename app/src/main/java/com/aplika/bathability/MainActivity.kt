package com.aplika.bathability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aplika.bathability.ui.theme.BathabilityTheme
import com.aplika.core.navigation.destination.MapDestination
import com.aplika.feature.map.MapUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BathabilityTheme { MainUI() } }
    }

    @Composable
    private fun MainUI() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MapDestination.route
        ) {
            composable(route = MapDestination.route) { MapUI(navController = navController) }
        }

    }
}