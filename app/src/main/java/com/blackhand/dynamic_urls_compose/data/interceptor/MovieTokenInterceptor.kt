package com.blackhand.dynamic_urls_compose.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MovieTokenInterceptor @Inject constructor(): Interceptor {
    // Inject shared preference in constructor please Habibi and dont forget to provide shared pref
    override fun intercept(chain: Interceptor.Chain): Response {
        //Don't try this at home you should get token when sign in or register then save token and pass it in interceptor
        val sendToken = chain.request().newBuilder().header(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZWExNjBkNjI5ODNlOTI0NTY5ZDdmZjJlNDFkMjY0YSIsInN1YiI6IjY2M2ZmOWQ5NzM2MTM4NjA3ZDdlOTNiYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BoyXFVspJ0TDci91ItnYDVtNgLrhsD1fNoOWCIaJYhI"
        ).build()
        return chain.proceed(sendToken)
    }

}