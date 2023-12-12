package com.example.mapapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navHostController: NavHostController, crawlService: CrawlService) {
    //val travelApi = TravelApi()
    NavHost(navController = navHostController, startDestination = "Home") {
        composable("Home") {
            Home()
        }
        composable("Crawl") {
            CrawlControl(crawlService)
        }
        composable("CrawlsView") {
            CrawlsView(service = crawlService) {
                navHostController.navigate("CrawlView/$it")
            }
        }
        composable("CrawlView/{id}") {
            val id = it.arguments?.getString("id")?.toLong()
            CrawlView(service = crawlService, id = id!!)
        }
    }
}