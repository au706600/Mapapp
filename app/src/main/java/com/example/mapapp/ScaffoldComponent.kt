package com.example.mapapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(modifier = modifier) {
        Row {
            topBar()
        }
        Row {
            content()
        }
    }
}