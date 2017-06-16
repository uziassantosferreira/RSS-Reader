package com.uzias.rssreader.feed.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.uzias.rssreader.core.presentation.BaseAdapter
import com.uzias.rssreader.feed.presentation.ItemListener
import com.uzias.rssreader.feed.presentation.model.PresentationItem
import com.uzias.rssreader.feed.presentation.view.ItemViewHolder

class ItemAdapter(
        context: Context,
        data: MutableList<PresentationItem>,
        layout: Int, val itemListener: ItemListener
) : BaseAdapter<PresentationItem>(context, data, layout) {

    override fun getViewHolder(view: View) = ItemViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = getItem(position)
            item?.let {
                with(holder){
                    itemView.setOnClickListener{itemListener.clicked(item)}
                    titleView.text = item.title
                    descriptionView.text = item.description
                    dateView.text = item.pubDate
                    if (item.imageUrl.isNotEmpty()){
                        Picasso.with(context).load(item.imageUrl).into(imageView)
                    }
                }
            }
        }
    }
}
