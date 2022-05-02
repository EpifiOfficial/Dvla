package com.jmartinez.dvla.network

import androidx.viewbinding.BuildConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object{
        private const val BASE_URL = "https://driver-vehicle-licensing.api.gov.uk/vehicle-enquiry/"
        private const val apiKey:String="mG1zaRgSH21lGk5mHwqgV6Y4oGkm8UpX5VNbfHoN"

    }



    fun <API>buildApi(
    api: Class<API>
    ):API{
        val jsonObject = JSONObject()
        jsonObject.put("registrationNumber","AA19AAA")
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    it.addInterceptor(loggingInterceptor)

                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}




/*fun <API>buildApi(
    api: Class<API>
):API{
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Content-Type", "application/json")
                it.addHeader("x-api-key",apiKey)
            }.build())
        }/*.also { client->

            if (BuildConfig.DEBUG){
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }

        }*/.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(api)
}
}*/