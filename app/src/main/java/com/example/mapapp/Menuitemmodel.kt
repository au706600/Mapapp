package com.example.mapapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

data class MenuItemModel(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val contentDescription: String, val onClick: () -> Unit
)

data class MenuItemModels(val models: List<MenuItemModel>)


@Composable
fun MenuItem(model: MenuItemModel) {
    NavigationDrawerItem(
        modifier = Modifier
            .padding(top = 15.dp),
        label = {
            Text(
                text = model.title,
                style = TextStyle(color = Color.White)
            )
        },
        selected = false,
        icon = {
            Icon(
                imageVector = model.icon,
                contentDescription = model.contentDescription,
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        onClick = {
            model.onClick()
        }
    )

}