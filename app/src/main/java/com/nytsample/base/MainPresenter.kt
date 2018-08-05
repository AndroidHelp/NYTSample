package com.katsoft.entrance.view.base

/**
 * Created by Hemant Katariya on 1/8/2018.
 */
interface MainPresenter<in V : MvpView> {
    fun attachedView(mMvpView: V)
    fun detachView()
}