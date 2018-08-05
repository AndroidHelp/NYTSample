package com.nytsample.view

import android.content.Context
import com.katsoft.entrance.view.base.BasePresenter
import com.nytsample.utils.AppUtils.Companion.isOnline
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PresenterMostPopular : BasePresenter<MvpMostPopular> {

    private var subscription: Subscription? = null

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun attachedView(mMvpView: MvpMostPopular) {
        super.attachedView(mMvpView)
    }

    override fun detachView() {
        super.detachView()
        if (subscription != null)
            subscription!!.unsubscribe()
    }

    fun getMostPopularArticles(type: String, viewType: String, sections: String, period: String) {
        checkViewAttached()
        if (isOnline(this!!.context!!))
            mostPopularArticles(type, viewType, sections, period)
        else mvpView.connectedToInternet("sorry, no internet connection")
    }

    private fun mostPopularArticles(type: String, viewType: String, sections: String, period: String) {
        subscription = getAPIMethods().getMostPopular(type, viewType, sections, period).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
            if (result.isSuccessful) mvpView.successMostPopular(result.body()!!.results!!)
            else mvpView.failedMostPopular(result.errorBody().toString())
        }, { t: kotlin.Throwable? ->
            try {
                mvpView.failedMostPopular(t.toString())
            } catch (e: Exception) {
                mvpView.failedMostPopular(e.localizedMessage)
            }
        })
    }

}