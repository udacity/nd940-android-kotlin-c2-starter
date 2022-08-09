package com.udacity.asteroidradar.domain

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)
