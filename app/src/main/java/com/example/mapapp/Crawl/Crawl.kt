package com.example.mapapp

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import java.lang.IllegalStateException
import java.time.LocalDateTime

const val MIN_DIST = 10.0

class Crawl(val id: Long, val name: String, val start: LocalDateTime) {
    var endTime: LocalDateTime? = null
    val tags = mutableListOf<CrawlInstance>()

    fun firstPosition(): LatLng {
        return LatLng(tags.first().location.latitude,tags.first().location.longitude)
    }
    fun toLatLng(): List<LatLng> {
        return tags.map { LatLng(it.location.latitude, it.location.longitude) }
    }

    fun addLocation(time: LocalDateTime, location: Location) {
        if (endTime != null) {
            throw IllegalStateException("Crawl has ended")
        }
        if (tags.isEmpty() || location.distanceTo(tags.last().location) >= MIN_DIST) {
            tags.add(CrawlInstance(time, location))
        }
    }

    fun endCrawl(time: LocalDateTime): Crawl {
        if (endTime != null) {
            throw IllegalStateException("Crawl has already ended")
        }
        endTime = time
        return this
    }

}

data class CrawlInstance(val time: LocalDateTime, val location: Location)