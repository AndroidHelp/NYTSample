package com.katsoft.entrance.view.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

/**
 * Created by Hemant Katariya on 1/8/2018.
 */
abstract class BaseActivity : AppCompatActivity() {
    public lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(getContentView())
        initView()
        initData()
        getValues()
        initListener()
    }

    abstract fun getContentView(): Int

    abstract fun initView()

    abstract fun initData()

    abstract fun getValues()

    abstract fun initListener()

    fun TAG(tagName: String, string: String) {
        Log.e("Katariya: " + tagName, string)
    }

    public fun showSnackBar(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, message, length).show()
    }

    public fun showSnackBarLong(view: View, message: String, length: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(view, message, length).show()
    }

}