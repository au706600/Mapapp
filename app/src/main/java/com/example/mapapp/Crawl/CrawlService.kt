package com.example.mapapp

import android.location.Location
import android.util.Log
import kotlinx.coroutines.delay
import java.time.LocalDateTime

const val CRAW_SERVICE_TAG = "CRAW_SERVICE"

class CrawlService(private val locationService: LocationService) {
    val crawls = mutableMapOf<Long, Crawl>()
    var currentCrawl: Crawl? = null

    companion object {
        var idGenerator = 1L;
        var interval = 10L//seconds;
        var max = 60 * 24//minutes

    }

    fun getCrawl(id: Long): Crawl? {
        return crawls[id]
    }

    suspend fun startCrawl(time: LocalDateTime, name: String) {
        if (currentCrawl != null) {
            throw IllegalStateException("Active crawl")
        }
        currentCrawl = Crawl(idGenerator++, name, time)
        doCrawl()
    }

    private suspend fun doCrawl() {
        Log.v(
            CRAW_SERVICE_TAG,
            "DoCrawl called ${LocalDateTime.now()} on crawl:${currentCrawl?.name}"
        )

        if (currentCrawl != null && currentCrawl!!.tags.isNotEmpty()) {
            Log.v(
                CRAW_SERVICE_TAG,
                "Location:${currentCrawl?.tags?.last()?.location}"
            )
        }
        while (currentCrawl != null) {
            currentCrawl?.addLocation(LocalDateTime.now(), locationService.getCurrentLocation())
            delay(interval * 1000)
            doCrawl()
        }
    }

    fun endCrawl(time: LocalDateTime) {
        if (currentCrawl == null) {
            throw IllegalStateException("No Active crawl")
        }
        currentCrawl?.endCrawl(time)
        crawls[currentCrawl?.id!!] = currentCrawl!!
        currentCrawl = null
    }

    fun addTag(time: LocalDateTime, location: Location) {
        if (currentCrawl == null) {
            throw IllegalStateException("No Active crawl")
        }
        currentCrawl?.addLocation(time, location)
    }

}