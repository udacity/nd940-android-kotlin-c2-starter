package com.udacity.asteroidradar

import com.udacity.asteroidradar.api.NeoWsService

class MainRepository {

    val neoWsService = NeoWsService()

    suspend fun fetchLatest() {
        println("*** fetchLatest")
        val list = neoWsService.fetchLast()
        println("*** list $list")
    }
}