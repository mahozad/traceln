package com.pleon.buyt

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.pleon.buyt.billing.IabHelper
import com.pleon.buyt.di.DaggerAppComponent
import com.pleon.buyt.repository.SubscriptionRepository
import com.pleon.buyt.util.LocaleUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class BuytApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    /**
     * This is for android N and higher.
     *
     * Because dagger is initialized in onCreate (after this method), we had to create a new
     * localeUtil object here.
     *
     * To let android resource framework to fetch and display appropriate string resources based on
     * user’s language preference, we need to override the base Context of the application
     * to have default locale configuration.
     */
    override fun attachBaseContext(cxt: Context) {
        super.attachBaseContext(LocaleUtil().setLocale(cxt))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeUtil.setLocale(this)
    }
}
