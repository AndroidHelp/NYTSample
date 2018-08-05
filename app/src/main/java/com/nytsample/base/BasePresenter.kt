package com.katsoft.entrance.view.base

import android.content.Context
import com.nytsample.network.APIClient
import com.nytsample.network.APIMethods


/**
 * Created by Hemant Katariya on 1/8/2018.
 */
open class BasePresenter<T : MvpView> : MainPresenter<T> {

    var context: Context? = null
    lateinit var mvpView: T
    private lateinit var apiMethods: APIMethods

    override fun attachedView(mMvpView: T) {
        mvpView = mMvpView
        apiMethods = APIClient.create()
    }

    @Suppress("UNUSED_EXPRESSION")
    override fun detachView() {
        false
    }

    fun getAPIMethods(): APIMethods {
        return apiMethods
    }

    private fun isViewAttached(): Boolean {
        return true
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("use presenter before, please call first attachview")

}