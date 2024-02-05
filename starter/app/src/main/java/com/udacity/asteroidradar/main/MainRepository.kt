package com.udacity.asteroidradar.main

import com.udacity.asteroidradar.api.NeoWsService

class MainRepository {

    val neoWsService = NeoWsService()

    suspend fun fetchLatest() {
        println("*** fetchLatest")
        try {
            val list = neoWsService.fetchLast()
            println("*** list $list")
        }
        catch (e : Exception) {
            e.printStackTrace()
        }

    }
}