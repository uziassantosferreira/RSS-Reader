package com.uzias.rssreader.core.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.uzias.rssreader.R

abstract class BaseAdapter<T>(
        protected var context: Context,
        protected var data: MutableList<T>,
        protected var layout: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 500L
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)

        return getViewHolder(view)
    }

    abstract fun getViewHolder(view: View): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    override fun getItemCount() = data.size

    /*
    PUBLIC INTERFACE
     */
    fun setSlideAnimationFromLeft(viewToAnimate: View, duration: Long = DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS) {
        val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        animation.duration = duration
        viewToAnimate.startAnimation(animation)
    }

    fun setSlideAnimationFromRight(viewToAnimate: View, duration: Long = DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        animation.duration = duration
        viewToAnimate.startAnimation(animation)
    }

    fun getItem(index: Int): T? {
        if (index < data.size && index >= 0) {
            return data[index]
        }
        return null
    }

    val firstItem: T?
        get() = data[0]

    val lastItem: T?
        get() = data[itemCount - 1]

    fun indexOf(item: T): Int {
        return data.indexOf(item)
    }

    fun addItem(item: T) {
        data.add(item)
        notifyItemInserted(data.size)
    }

    fun removeItem(index: Int = -1, item: T? = null) {
        if (index != -1 && index >= 0 && index < data.size) {
            data.removeAt(index)
            notifyItemRemoved(index)
        } else if (item != null && data.contains(item)) {
            val pos = data.indexOf(item)
            data.remove(item)
            notifyItemRemoved(pos)
        }
    }

    fun clear(){
        data.removeAll(data)
        notifyDataSetChanged()
    }
}