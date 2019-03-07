package com.example.myapplication

import android.util.Log
import com.example.myapplication.server.ServerResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

object NetworkManager {

    val api = Retrofit.Builder()
        .baseUrl("https://api.formation-android.fr/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(API::class.java)

    suspend fun getProduct(code : String) : Product? {
        return try {
            val serverResponse = api.getProduct(code).await()
            serverResponse.response?.toProduct()
        } catch (e: Exception) {
            null
        }
    }

}

interface API {
    @GET("getProduct")
    fun getProduct(@Query("barcode") code: String) : Deferred<ServerResponse>
}