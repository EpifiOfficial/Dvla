package com.jmartinez.dvla

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jmartinez.dvla.base.BaseViewModel
import com.jmartinez.dvla.network.Resource
import com.jmartinez.dvla.repository.VehicleRegistrationRepository
import com.jmartinez.dvla.response.RegistrationNumber
import com.jmartinez.dvla.response.VehicleRegistrationResponse

import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: VehicleRegistrationRepository
): BaseViewModel(repository) {

    private val _vehicleRegistrationResponse:MutableLiveData<Resource<VehicleRegistrationResponse>> = MutableLiveData()
    val vehicleRegistrationResponse:LiveData<Resource<VehicleRegistrationResponse>>
        get() = _vehicleRegistrationResponse

    fun getVehicleRegistrationResponse(
        //registrationNumber: RegistrationNumber
    )=viewModelScope.launch{
        _vehicleRegistrationResponse.value = repository.getVehicleRegistration(/*registrationNumber*/)
        _vehicleRegistrationResponse.value = Resource.loading
    }


}