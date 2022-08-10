package com.udacity.asteroidradar.main.domain.usecases

import com.udacity.asteroidradar.common.domain.Repository
import com.udacity.asteroidradar.common.domain.model.PictureOfDay
import javax.inject.Inject

class GetPictureOfDayUseCase @Inject constructor( private val  repository: Repository) {

    suspend operator fun invoke () : PictureOfDay{
       return repository.requestPictureOfDay()
    }
}