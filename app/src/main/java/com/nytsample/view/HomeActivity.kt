package com.nytsample.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.katsoft.entrance.view.base.BaseActivity
import com.nytsample.R
import com.nytsample.view.details.ActivityArticleDetails
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_error.*

class HomeActivity : BaseActivity(), MostPopularItemClickListener, MvpMostPopular {

    private var TAG: String = "Most Popular"

    private lateinit var adapterMostPopular: AdapterMostPopular
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var listMostPopularData: ArrayList<ResultItem> = ArrayList()

    private lateinit var presenterMostPopular: PresenterMostPopular

    override fun getContentView(): Int {
        return R.layout.activity_home
    }

    override fun initView() {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView_mostPopular.layoutManager = linearLayoutManager

        adapterMostPopular = AdapterMostPopular(listMostPopularData, this)
        recyclerView_mostPopular.adapter = adapterMostPopular
    }

    override fun initData() {
        presenterMostPopular = PresenterMostPopular(context)
        presenterMostPopular.attachedView(this)

        presenterMostPopular.getMostPopularArticles("mostpopular", "mostviewed", "all-sections", "1")
    }

    override fun getValues() {
    }

    override fun initListener() {
    }

    override fun itemClick(position: Int, resultItem: ResultItem) {
        val intent = Intent(this, ActivityArticleDetails::class.java)
        val bundle = Bundle()
        bundle.putString("title", resultItem.title)
        bundle.putString("url", resultItem.url)
        bundle.putString("imageURL", resultItem.media[0].media_metadata[5].url)
        bundle.putString("publishedDate", resultItem.published_date)
        bundle.putString("byline", resultItem.byline)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun successMostPopular(results: ArrayList<ResultItem>) {
        progressBar.visibility = View.GONE
        if (results.size > 0) {
            hideErrorLayout()
            if (!listMostPopularData.isEmpty()) listMostPopularData.clear()

            listMostPopularData.addAll(results)
            adapterMostPopular.notifyDataSetChanged()
        }

    }

    private fun hideErrorLayout() {
        linear_errorLayout.visibility = View.GONE
        recyclerView_mostPopular.visibility = View.VISIBLE
    }

    private fun showErrorLayout(message: String) {
        recyclerView_mostPopular.visibility = View.GONE
        linear_errorLayout.visibility = View.VISIBLE
        text_message.text = message
    }

    override fun failedMostPopular(message: String) {
        Log.d(TAG, message)
        progressBar.visibility = View.GONE
        showErrorLayout(message)
    }

    override fun connectedToInternet(message: String) {
        Log.d(TAG, message)
        progressBar.visibility = View.GONE
        showErrorLayout(getString(R.string.message_no_internet_connection))
    }
}
