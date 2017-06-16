package com.uzias.rssreader.core.presentation

interface BasePresenter {

    fun attachTo(view: BaseView)

    fun detachFrom(view: BaseView)

}