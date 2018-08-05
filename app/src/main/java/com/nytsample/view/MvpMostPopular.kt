package com.nytsample.view

import com.katsoft.entrance.view.base.MvpView

interface MvpMostPopular:MvpView {

    fun successMostPopular(results: ArrayList<ResultItem>)

    fun failedMostPopular(message: String)
}