package com.uzias.rssreader.core.presentation

interface BaseView {

    fun getPresenter(): BasePresenter

    fun injectDependencies()

    fun bindPresenter() {
        getPresenter().attachTo(this)
    }

    fun unbindPresenter() {
        getPresenter().detachFrom(this)
    }

    fun showLoading()

    fun dismissLoading()

}