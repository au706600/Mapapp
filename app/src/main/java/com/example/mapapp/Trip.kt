package com.example.mapapp

import com.google.android.gms.maps.model.LatLng

data class Trip(val title: String, val description: String, val points: List<LatLng>)