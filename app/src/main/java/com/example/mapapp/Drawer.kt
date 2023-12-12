package com.example.mapapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Drawer(drawerState: DrawerState, menuItems: MenuItemModels, content: @Composable () -> Unit) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.onBackground,
                drawerContentColor = Color.Cyan
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.race),
                        contentDescription = "Pubs"
                    )
                }
                Box(modifier = Modifier.padding(top = 120.dp)) {
                    Column {
                        menuItems.models.map {
                            MenuItem(model = it.copy(onClick = {
                                it.onClick()
                                //close()
                            }))
                        }
                    }
                }

            }
        },
        gesturesEnabled = true,
        content = content

    )
}