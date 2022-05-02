package com.jmartinez.dvla.network

import com.jmartinez.dvla.response.RegistrationNumber
import com.jmartinez.dvla.response.VehicleRegistrationResponse
import retrofit2.http.*

interface  VehicleRegistrationApi {
    @FormUrlEncoded

    @POST("/v1/vehicles")
    suspend fun getVehicleRegistration(
        @Header("Content-Type") content_type:String = "application/json",
        @Header("x-api-key") apikey : String = "mG1zaRgSH21lGk5mHwqgV6Y4oGkm8UpX5VNbfHoN",
        //Testing where the error of connection is
    @Body registrationNumber: RegistrationNumber = RegistrationNumber("AA19AAA")
    ): VehicleRegistrationResponse

}
