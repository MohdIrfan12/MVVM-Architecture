package com.mia.mvvvmcarchitecture.common.dependencyInjection

import com.mia.mvvvmcarchitecture.common.eventbus.EventBus
import com.mia.mvvvmcarchitecture.networking.Constants
import com.mia.mvvvmcarchitecture.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class CompositionRoot {
    private val mRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val mEventBus by lazy {
        EventBus()
    }

    fun getStackOverFlowApi(): StackoverflowApi {
        return mRetrofit.create(StackoverflowApi::class.java)
    }

    fun getEventBus(): EventBus {
        return mEventBus;
    }
}