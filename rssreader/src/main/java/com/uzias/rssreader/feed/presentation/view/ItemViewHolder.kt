package com.uzias.rssreader.feed.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_feed_item.view.*

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleView: TextView = view.textview_title
    val imageView: ImageView = view.imageview_image
    val descriptionView: TextView = view.textview_description
    val dateView: TextView = view.textview_date
}