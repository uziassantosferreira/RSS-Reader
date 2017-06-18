package com.uzias.rssreader.feed.presentation.presenter

import com.uzias.rssreader.core.presentation.BaseView
import com.uzias.rssreader.feed.domain.usecase.AddRss
import com.uzias.rssreader.feed.domain.usecase.GetRss
import com.uzias.rssreader.feed.domain.usecase.RefreshRss
import com.uzias.rssreader.feed.presentation.mapper.PresentationRssMapper
import com.uzias.rssreader.feed.presentation.model.PresentationRss
import com.uzias.rssreader.feed.presentation.view.FeedView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable

class FeedPresenterImpl(var addRss: AddRss, var getRss: GetRss, var refreshRss: RefreshRss):
        FeedPresenter {

    private val compositeDispose: CompositeDisposable = CompositeDisposable()

    private var presentationRssSelected: PresentationRss? = null

    private lateinit var view: FeedView

    override fun clickedButtonAdd() {
        view.openDialogInputUrl()
    }

    override fun clickedButtonOkInputUrl(url: String) {
        view.showLoading()
        compositeDispose.add(
                addRss.setUrl(url)
                        .run()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { PresentationRssMapper.transformFrom(it) }
                        .subscribe{
                            view.dismissLoading()
                            view.addRss(it)
                        }
        )
    }

    override fun refreshFeedActioned() {
        presentationRssSelected?.let {
            view.showLoading()
            compositeDispose.add(
                    refreshRss.setUrl(it.url)
                            .run()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map { PresentationRssMapper.transformFrom(it) }
                            .subscribe{
                                view.removeRss(presentationRssSelected!!)
                                setPresentationSelected(it)
                                view.dismissLoading()
                                view.addRss(it)
                                view.setSelectedRss(it)
                            }
            )
        }
        view.dismissSwipeLoading()
    }

    override fun setPresentationSelected(presentationRss: PresentationRss) {
        presentationRssSelected = presentationRss
    }


    override fun attachTo(view: BaseView) {
        if (view is FeedView) {
            this.view = view
            compositeDispose.add(
                    getRss.run()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map { PresentationRssMapper.transformFrom(it) }
                            .subscribe{
                                view.addRss(it)
                            }
            )
        } else {
            throw IllegalArgumentException("FeedPresenter requires a view of type FeedView")
        }
    }

    override fun detachFrom(view: BaseView) {
        compositeDispose.clear()
    }

}