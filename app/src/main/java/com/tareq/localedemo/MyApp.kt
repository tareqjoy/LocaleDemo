package com.tareq.localedemo

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes

class MyApp: Application() {
    private var currentLocaleContext: Context? = null
    val storage : Storage by lazy {
        Storage(this)
    }

    fun invalidateConfiguration(){
        //updating app configuration according to locale change
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(LocaleUtil.getLocaleFromPrefCode(Storage(this).getPreferredLocale()))
        currentLocaleContext = createConfigurationContext(configuration)
    }

    fun getStringInLocale(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        //getting string from app context
        return currentLocaleContext?.resources?.getString(stringRes, *formatArgs) ?: ""
    }
}