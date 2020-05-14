package com.darshan.androidtutorial.data

import okhttp3.*

class MockDataProvider(val fileName: String) {
    fun getDataResponse(request: Request): Response {
        val json: String = readResource(fileName)
        return Response.Builder().code(200)
            .addHeader("Content-Type", "application/json")
            .body(ResponseBody.create(MediaType.parse("application/json"), json)).message(json)
            .request(request).protocol(Protocol.HTTP_2).build()
    }

    fun readResource(resourceName: String): String {
        val fileNameByCountry = "mockdata/$resourceName"
        val classLoader =
            Thread.currentThread().contextClassLoader
            classLoader.getResourceAsStream (fileNameByCountry).use {
                return it.reader().readText()
            }
    }
}


