package com.example.mapapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapWidget(crawl: Crawl) {
    //val currentPos = remember { mutableStateOf(LatLng(56.157101, 10.190372)) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(crawl.firstPosition(), 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Polyline(
            color = Color.Magenta,
            pattern = listOf(Dash(2f)),
            points = crawl.toLatLng()
        )

        Marker(
            state = MarkerState(position = crawl.firstPosition()),
            title = "TEST",
            snippet = "Marker in:"
        )
    }
}