package com.example.mapapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AppBar(title: String, modifier: Modifier = Modifier, onMenu: () -> Unit) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)
        )
    ) {
        IconButton(onClick = onMenu) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "MenuButton",
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
        Text(
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .weight(2f)
                .align(alignment = Alignment.CenterVertically),
            text = title
        )

    }
}