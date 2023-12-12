package com.example.mapapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun CrawlControl(service: CrawlService) {

    val scope = rememberCoroutineScope()
    val name = remember { mutableStateOf("NoName") }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier.focusTarget(),
            value = name.value,
            onValueChange = { name.value = it })
        Button(onClick = {
            scope.launch { service.startCrawl(LocalDateTime.now(), name.value) }
        }) {
            Text("Start Crawl")
        }
        Button(onClick = {
            scope.launch {
                service.endCrawl(LocalDateTime.now())
            }
        }) {
            Text("Stop Crawl")
        }
    }

}