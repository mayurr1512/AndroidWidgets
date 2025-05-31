package com.sample.androidwidgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GlanceWidgetExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glance_widget_explanation)
        supportActionBar?.title = getString(R.string.glance_widget_explanation_title)
    }
}