package com.sample.glancewidget

import androidx.glance.appwidget.GlanceAppWidgetReceiver

/**
 * Receiver for our Glance App Widget.
 * This acts as the entry point for system updates to the widget.
 */
class MyGlanceAppWidgetReceiver : GlanceAppWidgetReceiver() {
    // Specify the GlanceAppWidget that this receiver is associated with
    override val glanceAppWidget: MyGlanceAppWidget = MyGlanceAppWidget()
}