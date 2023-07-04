package dev.aplika.bathability

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.aplika.bathability.ui.theme.BathabilityTheme
import dev.aplika.core.navigation.destination.AboutDestination
import dev.aplika.core.navigation.destination.CollectPointDetailsDestination
import dev.aplika.core.navigation.destination.MapDestination
import dev.aplika.feature.about.AboutUI
import dev.aplika.feature.collect_point_details.CollectPointDetailsUI
import dev.aplika.feature.map.MapUI
import dev.aplika.feature.menu.MenuUI
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalPermissionsApi::class)
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
                        composable(route = MapDestination.route) { MapUI(navController = navController, drawerState = drawerState) }
                        bottomSheet(route = CollectPointDetailsDestination.route) { CollectPointDetailsUI() }
                        composable(route = AboutDestination.route) { AboutUI(navController = navController, versionName = BuildConfig.VERSION_NAME) }
                    }
                }
            }
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationsPermissionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
            if (!notificationsPermissionState.status.isGranted) {
                SideEffect {
                    notificationsPermissionState.launchPermissionRequest()
                }
            }
        }
    }
}