package com.udacity.asteroidradar.main.domain.usecases

import com.udacity.asteroidradar.common.domain.Repository
import com.udacity.asteroidradar.common.domain.model.Asteroid
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAsteroidsUseCase @Inject constructor(private val  repository: Repository) {

     operator fun invoke () : Flow<List<Asteroid>> {
        return repository.getAllAsteroids()
    }
}
