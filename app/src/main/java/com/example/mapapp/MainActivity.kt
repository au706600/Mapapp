package com.example.mapapp

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mapapp.ui.theme.MapappTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.time.LocalDateTime
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var mapsClient: FusedLocationProviderClient
    private lateinit var geoCoder: Geocoder
    private lateinit var service: LocationService
    private lateinit var crawlService: CrawlService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapsClient = LocationServices.getFusedLocationProviderClient(this)
        geoCoder = Geocoder(this, Locale.getDefault())
        service = LocationService(this, mapsClient, geoCoder)
        service.requestPermission()
        crawlService = CrawlService(service)
        setContent {
            MapappTheme {
                // A surface container using the 'background' color from the theme
                val navHostController = rememberNavController()
                //val crawl = Crawl(id = 1, name = "MyCrawl", start = LocalDateTime.now())

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    AppScaffold(title = "test", controller = navHostController) {
                        Navigation(navHostController = navHostController, crawlService)
                    }
                    Column {
                        val crawl = crawlService.currentCrawl
                        if (crawl != null) {
                            MapWidget(crawl = crawl)
                        }
                        else {
                            Text("Error")
                        }
                        LocationWidget(service = service)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MapappTheme {
        Greeting("Android")
    }
}