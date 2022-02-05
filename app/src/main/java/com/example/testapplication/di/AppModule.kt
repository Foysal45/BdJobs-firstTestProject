package com.example.testapplication.di

import com.example.testapplication.api.ApiInterface
import com.example.testapplication.repository.AppRepository
import com.example.testapplication.utils.AppConstant
import com.example.testapplication.utils.RetrofitUtils.createCache
import com.example.testapplication.utils.RetrofitUtils.createOkHttpClient
import com.example.testapplication.utils.RetrofitUtils.getGson
import com.example.testapplication.utils.RetrofitUtils.retrofitInstance
import com.example.testapplication.viewmodel.JobListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single { getGson() }
    single { createCache(get()) }
    single { createOkHttpClient(get()) }
    single(named("normal")) { createOkHttpClient(get()) }

    single(named("api")) { retrofitInstance(AppConstant.BASE_URL_API, get(), get()) }

    single { ApiInterface(get(named("api"))) }

    single { AppRepository(get()) }

    viewModel { JobListViewModel(get()) }


}