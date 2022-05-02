package com.jmartinez.dvla.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jmartinez.dvla.HomeViewModel
import com.jmartinez.dvla.repository.BaseRepository
import com.jmartinez.dvla.repository.VehicleRegistrationRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: BaseRepository,

    ): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(repository as VehicleRegistrationRepository)as T
            else->throw IllegalArgumentException("View Model class not found")

        }


    }



}