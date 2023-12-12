package com.example.mapapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CrawlView(service: CrawlService, id: Long) {
    val crawl = remember { mutableStateOf<Crawl?>(null) }
    LaunchedEffect(key1 = Unit) {
        crawl.value = service.getCrawl(id)
    }
    Column(
        modifier = Modifier
            .background(color = Color.Red)
            .fillMaxSize()
    ) {
        if (crawl.value != null)
            MapWidget(crawl = crawl.value!!)
    }

}