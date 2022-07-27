//package com.udacity.asteroidradar.ui.main
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.udacity.asteroidradar.repository.AsteroidRepository
//
//class MainViewModelFactory(
//    private val repository: AsteroidRepository
//) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Model Class not assignable from MainViewModel.kt")
//    }
//}