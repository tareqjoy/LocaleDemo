package com.tareq.localedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(getString(R.string.first_activity))

        hello_activity.setText(getString(R.string.hello_world))
        hello_app.setText((applicationContext as MyApp).getStringInLocale(R.string.hello_world))

        button2.setOnClickListener {
            startActivity(Intent(this, LocaleChangeActivity::class.java))
        }
    }
}