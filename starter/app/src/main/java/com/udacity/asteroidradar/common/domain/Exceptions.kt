package com.udacity.asteroidradar.common.domain

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String): Exception(message)

class NoImageException(message: String): Exception(message)
