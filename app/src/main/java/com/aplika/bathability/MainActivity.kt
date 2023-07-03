package com.aplika.bathability

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aplika.bathability.ui.theme.BathabilityTheme
import com.aplika.core.navigation.destination.CollectPointDetailsDestination
import com.aplika.core.navigation.destination.MapDestination
import com.aplika.feature.collect_point_details.CollectPointDetailsUI
import com.aplika.feature.map.MapUI
import com.aplika.feature.menu.MenuUI
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BathabilityTheme { MainUI() } }
    }

    @Composable
    private fun MainUI() {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { MenuUI(navController = navController, drawerState = drawerState) },
            gesturesEnabled = drawerState.isOpen,
            content = {
                ModalBottomSheetLayout(bottomSheetNavigator) {
                    NavHost(
                        navController = navController,
                        startDestination = MapDestination.route
                    ) {
                        composable(route = MapDestination.route) { MapUI(navController = navController) }
                        bottomSheet(route = CollectPointDetailsDestination.route) { CollectPointDetailsUI() }
                    }
                }
            }
        )

    }
}