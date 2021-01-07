package com.mia.mvvvmcarchitecture.common

import android.app.Application
import com.mia.mvvvmcarchitecture.common.dependencyInjection.CompositionRoot

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class CommonApplication : Application() {

    private lateinit var mCompositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        mCompositionRoot = CompositionRoot()
    }

    fun getCompositionRoot(): CompositionRoot {
        return mCompositionRoot;
    }
}