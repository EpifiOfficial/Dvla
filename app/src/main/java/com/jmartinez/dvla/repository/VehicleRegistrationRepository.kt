package com.jmartinez.dvla.repository

import com.jmartinez.dvla.network.VehicleRegistrationApi
import com.jmartinez.dvla.repository.BaseRepository
import com.jmartinez.dvla.response.RegistrationNumber

class VehicleRegistrationRepository(

    private val api: VehicleRegistrationApi
): BaseRepository(){

    suspend fun getVehicleRegistration(
        //registrationNumber: RegistrationNumber

    ) = safeApiCall {

        api.getVehicleRegistration(/*registrationNumber*/)
    }



}