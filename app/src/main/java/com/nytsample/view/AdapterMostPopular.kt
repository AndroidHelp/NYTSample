package com.nytsample.view

import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nytsample.R
import com.squareup.picasso.Picasso

class AdapterMostPopular : RecyclerView.Adapter<AdapterMostPopular.ItemViewHolder> {

    private lateinit var listData: ArrayList<ResultItem>
    private lateinit var itemClickListener: MostPopularItemClickListener

    constructor(listData: ArrayList<ResultItem>, itemClickListener: MostPopularItemClickListener) : super() {
        this.listData = listData
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_most_popular_item, parent, false);
        return ItemViewHolder(view);
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder?.bindItem(listData[position])
        holder?.itemView.setOnClickListener {
            itemClickListener.itemClick(position, listData[position])
        }
    }

    class ItemViewHolder : RecyclerView.ViewHolder {

        var textTitle: TextView
        var textDate: TextView
        var textByLine: TextView
        var imageArticle: ImageView

        constructor(itemView: View) : super(itemView) {
            textTitle = itemView.findViewById(R.id.text_title)
            textByLine = itemView.findViewById(R.id.text_byline)
            textDate = itemView.findViewById(R.id.text_date)
            imageArticle = itemView.findViewById(R.id.image_article)
        }

        fun bindItem(resultItem: ResultItem) {

            textTitle.text = resultItem.title
            textByLine.text = resultItem.byline
            textDate.text = resultItem.published_date

            if (resultItem.media[0].type.equals("image"))
           //     println("image: "+resultItem.media[0].toString())
             //   println("image: "+resultItem.media[0].media_metadata[0].url)
                Picasso.get().load(resultItem.media[0].media_metadata[0].url).into(imageArticle)
        }

    }
}