package com.example.testapplication.repository

import com.example.testapplication.api.ApiInterface

class AppRepository(
    private val apiInterface: ApiInterface
) {

    suspend fun getTotalJobList() = apiInterface.getTotalJobList()

}