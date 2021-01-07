package com.mia.mvvvmcarchitecture.common.eventbus

import com.mia.mvvvmcarchitecture.common.observer.BaseObservable

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
class EventBus : BaseObservable<EventBus.EventBusListener>() {

    interface EventBusListener {
        fun onEvent(event: Any)
    }

    fun postEvent(event: Any) {
        for (listener in getObserver()) {
            listener.onEvent(event)
        }
    }
}