package com.app.xandone.yfun.ui.base

import io.reactivex.subscribers.ResourceSubscriber

/**
 * author: xandone
 * created on: 2017/9/30 0:38
 */
class RxSubscriber<T> : ResourceSubscriber<T>() {

    override fun onNext(t: T) {
        
    }

    override fun onError(t: Throwable?) {
    }

    override fun onComplete() {
    }

}