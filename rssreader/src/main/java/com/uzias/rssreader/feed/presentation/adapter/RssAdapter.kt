package com.uzias.rssreader.feed.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.uzias.rssreader.core.presentation.BaseAdapter
import com.uzias.rssreader.feed.presentation.RssListener
import com.uzias.rssreader.feed.presentation.model.PresentationRss
import com.uzias.rssreader.feed.presentation.view.RssViewHolder

class RssAdapter(
        context: Context,
        data: MutableList<PresentationRss>,
        layout: Int, val rssListener: RssListener
) : BaseAdapter<PresentationRss>(context, data, layout) {

    override fun getViewHolder(view: View) = RssViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RssViewHolder) {
            val rss = getItem(position)

            rss?.let {
                with(holder){
                    urlView.text = it.url
                    holder.itemView.setOnClickListener {
                        rssListener.clicked(rss)
                    }
                }
            }
        }
    }
}
