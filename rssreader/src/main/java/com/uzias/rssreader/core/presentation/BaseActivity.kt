package com.uzias.rssreader.core.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.uzias.rssreader.core.application.RSSReaderApplication
import com.uzias.rssreader.core.di.AppComponent

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        bindPresenter()
    }

    fun getAppComponent() : AppComponent {
        return (application as RSSReaderApplication).component
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}