package com.darshan.androidtutorial.data

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requst = chain.request()
        fullPathSegmentProcessors()[requst.url().encodedPath()]?.let {
            return it.invoke(requst)
        }
        throw Exception("Mock data not provided for ${requst.url()}")
    }


    private fun fullPathSegmentProcessors(): Map<String, (Request) -> Response> {
        return mapOf(WORLD_STAT_URL to { request ->
            MockDataProvider(fileName = "newslist.json").getDataResponse(
                request
            )
        })
    }

    companion object {
        const val WORLD_STAT_URL = "/v2/everything"
    }
}