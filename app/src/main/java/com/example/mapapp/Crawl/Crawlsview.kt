package com.example.mapapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CrawlsView(service: CrawlService, navigate: (id: Long) -> Unit) {
    val list = remember { mutableStateOf(emptyList<Crawl>()) }

    LaunchedEffect(key1 = Unit) {
        list.value = service.crawls.values.toList()
    }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onSurface)
            .fillMaxSize()
    ) {
        list.value.map {
            CrawlListItem(crawl = it, navigate)
        }
    }
}

@Composable
fun CrawlListItem(crawl: Crawl, navigate: (id: Long) -> Unit) {
    Row(modifier = Modifier.clickable {
        navigate(crawl.id)
    }) {
        Text("Name:${crawl.name}")
        Text("Started:${crawl.start}")
    }
}