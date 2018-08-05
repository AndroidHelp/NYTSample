package com.nytsample.view.details

import android.content.Intent
import android.net.Uri
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.katsoft.entrance.view.base.BaseActivity
import com.nytsample.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_article_details.*


class ActivityArticleDetails : BaseActivity() {

    private var readMoreURL: String? = null

    override fun getContentView(): Int {
        return R.layout.layout_article_details
    }

    override fun initView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
    }

    override fun initData() {

    }

    override fun getValues() {
        val bundle = intent.extras
        if (bundle != null) {
            val title = bundle.getString("title")
            val url = bundle.getString("url")
            val imageURL = bundle.getString("imageURL")
            val publishedDate = bundle.getString("publishedDate")
            val byline = bundle.getString("byline")

            Picasso.get().load(imageURL).into(image_article)
            text_title.text = title
            text_byline.text = byline
            text_date.text = publishedDate
            readMoreURL = url
        }
    }

    override fun initListener() {
        text_readMore.setOnClickListener {
            if (readMoreURL != null) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(readMoreURL))
                startActivity(browserIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}