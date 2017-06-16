package com.uzias.rssreader.core.presentation

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.uzias.rssreader.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.util_recyclerview_with_feedback.view.*

class RecyclerViewWithFeedback : RelativeLayout {

    enum class State {
        LOADING, EMPTY, FILLED
    }

    private var observerListLoaded: Disposable? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.util_recyclerview_with_feedback, this)
        recyclerView_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        if (attrs != null) {
            val icon = getIcon(attrs)
            if (icon != -1) {
                imageView_icon.setImageResource(icon)
            }

            val labelRes = getLabel(attrs)
            if (labelRes != -1) {
                textView_label.text = context.getString(labelRes)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        observerListLoaded?.dispose()
    }

    private fun getIcon(attrs: AttributeSet): Int {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.RecyclerViewWithFeedback,
                0, 0)
        try {
            return a.getResourceId(R.styleable.RecyclerViewWithFeedback_icon, -1)
        } finally {
            a.recycle()
        }
    }

    private fun getLabel(attrs: AttributeSet): Int {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.RecyclerViewWithFeedback,
                0, 0)
        try {
            return a.getResourceId(R.styleable.RecyclerViewWithFeedback_label, -1)
        } finally {
            a.recycle()
        }
    }

    fun setState(state: State) {
        when (state) {
            State.LOADING -> {
                linearLayout_emptyDisclaimerContainer.visibility = LinearLayout.INVISIBLE
                recyclerView_list.visibility = LinearLayout.INVISIBLE
                spinner_loading.visibility = LinearLayout.VISIBLE
            }
            State.EMPTY -> {
                linearLayout_emptyDisclaimerContainer.visibility = LinearLayout.VISIBLE
                recyclerView_list.visibility = LinearLayout.INVISIBLE
                spinner_loading.visibility = LinearLayout.INVISIBLE
            }
            State.FILLED -> {
                linearLayout_emptyDisclaimerContainer.visibility = LinearLayout.INVISIBLE
                recyclerView_list.visibility = LinearLayout.VISIBLE
                spinner_loading.visibility = LinearLayout.INVISIBLE
            }
        }
    }

    fun getRecyclerView(): RecyclerView = recyclerView_list

}