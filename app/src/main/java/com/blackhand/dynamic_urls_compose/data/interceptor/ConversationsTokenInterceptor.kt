package com.blackhand.dynamic_urls_compose.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConversationsTokenInterceptor @Inject constructor() :
    Interceptor {
    // Inject shared preference in constructor please Habibi and dont forget to provide shared pref
    override fun intercept(chain: Interceptor.Chain): Response {
        //Don't try this at home you should get token when sign in or register then save token and pass it in interceptor
        val sendToken = chain.request().newBuilder().header(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NjM5MGZjNzU1NmNiZGZhZjFiNjY4YmIiLCJpYXQiOjE3MTU2NjA4NjAsImV4cCI6MTcxODI1Mjg2MH0.SEnLe-rylb_ofN6xdZzbaOokrz2ibRBFp7_Fdr30hys"
        ).build()
        return chain.proceed(sendToken)
    }
}