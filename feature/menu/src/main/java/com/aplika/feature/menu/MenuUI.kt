package com.aplika.feature.menu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aplika.core.resources.R
import kotlinx.coroutines.launch

@Composable
fun MenuUI(
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val selectedItem = rememberSaveable { mutableStateOf(MenuItem.MAP) }

    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))

        Text(text = stringResource(id = R.string.app_name))

        MenuItem.values().forEach { menuItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        painter = painterResource(id = menuItem.iconResId),
                        contentDescription = stringResource(id = menuItem.iconContentDescriptionResId)
                    )
                },
                label = { Text(text = stringResource(id = menuItem.labelResId)) },
                selected = menuItem == selectedItem.value,
                onClick = {
                    coroutineScope.launch {
                        navController.navigate(menuItem.destination.route)
                        drawerState.close()
                    }
                    selectedItem.value = menuItem
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}