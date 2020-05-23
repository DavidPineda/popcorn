package com.androidavanzado.popcorn.retrofit

import com.androidavanzado.popcorn.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Añadir paramtros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY)
            .addQueryParameter(Constants.URL_PARAM_LANGUAGE, Constants.API_LANGUAGE)
            .build()

        var request = chain.request()

        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}