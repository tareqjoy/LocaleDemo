package com.tareq.localedemo

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    private lateinit var oldPrefLocaleCode : String
    protected val storage : Storage by lazy {
        (application as MyApp).storage
    }

    private fun resetTitle() {
        try {
            val label = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA).labelRes;
            if (label != 0) {
                setTitle(label);
            }
        } catch (e: PackageManager.NameNotFoundException) {

        }
    }

    override fun attachBaseContext(newBase: Context) {
        val currentLocaleCode = Storage(newBase).getPreferredLocale()
        val prefLocale = LocaleUtil.getLocaleFromPrefCode(currentLocaleCode)
        val localeUpdatedContext: ContextWrapper = LocaleUtil.updateContextLocale(newBase, prefLocale)
        oldPrefLocaleCode = currentLocaleCode
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).invalidateConfiguration()
        resetTitle()
    }

    override fun onResume() {
        val currentLocaleCode = Storage(this).getPreferredLocale()
        if(oldPrefLocaleCode != currentLocaleCode){
            (application as MyApp).invalidateConfiguration()
            recreate()
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }
}