package com.uzias.rssreader

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass

abstract class Rx2TestBase {

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            val scheduler = Schedulers.trampoline()
            RxJavaPlugins.setIoSchedulerHandler({ _ -> scheduler })
            RxJavaPlugins.setInitComputationSchedulerHandler({ _ -> scheduler })
            RxJavaPlugins.setComputationSchedulerHandler({ _ -> scheduler })
            RxJavaPlugins.setNewThreadSchedulerHandler({ _ -> scheduler })
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({ _ -> scheduler })
            RxAndroidPlugins.setMainThreadSchedulerHandler({ _ -> scheduler })
        }
        @JvmStatic
        @AfterClass
        fun tearDownClass() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }

}