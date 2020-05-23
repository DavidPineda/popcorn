package com.androidavanzado.popcorn.retrofit

import com.androidavanzado.popcorn.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object {
        var instance: TheMovieDBClient? = null
            get() {
                if (field == null) {
                    instance = TheMovieDBClient()
                }
                return instance
            }
    }

    init {
        // Incluir el interceptor que se definio para las peticiones http
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val client = okHttpClientBuilder.build()

        // Construir el cliente retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // Instanciamos servicio retrofit a partir del objeto retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService() = theMovieDBService
}