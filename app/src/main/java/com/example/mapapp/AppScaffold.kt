package com.example.mapapp

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun AppScaffold(title: String, controller: NavHostController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val open: () -> Unit = { scope.launch { drawerState.open() } }
    val close: () -> Unit = { scope.launch { drawerState.close() } }
    val menuModels = MenuItemModels(
        listOf(
            MenuItemModel("1", "Home", Icons.Default.Home, "Home") {
                controller.navigate("Home")
                close()
            },
            MenuItemModel("2", "CrawlControl", Icons.Default.AccountCircle, "CrawlControl") {
                controller.navigate("Crawl")
                close()
            },
            MenuItemModel("3", "CrawlsView", Icons.Default.Call, "CrawlsView") {
                controller.navigate("CrawlsView")
                close()
            }
        )

    )
    Drawer(drawerState = drawerState, menuItems = menuModels) {
        ScaffoldComponent(
            modifier = Modifier.background(MaterialTheme.colorScheme.onSurface),
            topBar = {
                AppBar(title = title, onMenu = open)
            }, content = content
        )
    }

}