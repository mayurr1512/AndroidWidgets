package com.sample.androidwidgets

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to show info about XML Widget
        val btnXmlWidget: Button = findViewById(R.id.btnXmlWidget)
        btnXmlWidget.setOnClickListener {
            val intent = Intent(this, XmlWidgetExplanationActivity::class.java)
            startActivity(intent)
        }

        // Button to show info about Glance Widget
        val btnGlanceWidget: Button = findViewById(R.id.btnGlanceWidget)
        btnGlanceWidget.setOnClickListener {
            val intent = Intent(this, GlanceWidgetExplanationActivity::class.java)
            startActivity(intent)
        }
    }
}