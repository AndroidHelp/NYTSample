package com.nytsample.view

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MostPopularItem(var status: String?, var copyright: String?, var num_results: Long, var results: ArrayList<ResultItem>?)

data class ResultItem(var url: String?, var byline: String?, var type: String?, var title: String?, var abstract: String?, var published_date: String?, var source: String?, var views: Long?, var media: ArrayList<MediaItem>)

data class MediaItem(var type: String?, var subtype: String?, var caption: String?, var copyright: String?, var approved_for_syndication: Int?, @SerializedName("media-metadata") var media_metadata: ArrayList<MetadataItem>)


data class MetadataItem(var url: String?, var format: String?, var height: Int?, var width: Int?)