package com.sample.androidwidgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class XmlWidgetExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_widget_explanation)
        supportActionBar?.title = getString(R.string.xml_widget_explanation_title)
    }
}