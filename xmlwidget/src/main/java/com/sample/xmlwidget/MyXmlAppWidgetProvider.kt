package com.sample.xmlwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 * This class handles updates for the XML-based widget.
 */
class MyXmlAppWidgetProvider : AppWidgetProvider() {

    // Action for button click
    companion object {
        const val ACTION_BUTTON_CLICK = "com.sample.xmlwidget.ACTION_BUTTON_CLICK"
    }

    /**
     * Called when the App Widget is updated. This is called periodically based on updatePeriodMillis,
     * when the user adds the widget, or when the device boots.
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets of this class, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     * Called when an instance of the App Widget is created for the first time.
     */
    override fun onEnabled(context: Context) {
        // Perform any setup when the first widget instance is added
        Toast.makeText(context, "XML Widget Enabled!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Called when the last instance of the App Widget is deleted.
     */
    override fun onDisabled(context: Context) {
        // Perform any cleanup when the last widget instance is removed
        Toast.makeText(context, "XML Widget Disabled!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Handles custom broadcast intents.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (context != null && intent?.action == ACTION_BUTTON_CLICK) {
            // Handle the button click
            val appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Update the widget content immediately on button click
                val appWidgetManager = AppWidgetManager.getInstance(context)
                updateAppWidget(context, appWidgetManager, appWidgetId)
                Toast.makeText(context, "XML Widget Updated!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

/**
 * Updates the specific App Widget instance.
 * @param context The context.
 * @param appWidgetManager The AppWidgetManager instance.
 * @param appWidgetId The ID of the widget instance to update.
 */
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    // RemoteViews allows you to update views in another process (the home screen)
    val views = RemoteViews(context.packageName, R.layout.xml_widget_layout)

    // Update the message TextView with current time
    val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    views.setTextViewText(R.id.xml_widget_message, "Last updated: $currentTime")

    // Create an Intent for the button click
    val buttonIntent = Intent(context, MyXmlAppWidgetProvider::class.java).apply {
        action = MyXmlAppWidgetProvider.ACTION_BUTTON_CLICK
        // Pass the widget ID so we know which widget to update
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    }

    // Create a PendingIntent to be triggered when the button is clicked
    // FLAG_UPDATE_CURRENT: If the PendingIntent already exists, then keep it but replace its extra data with the new Intent.
    // FLAG_IMMUTABLE: Required for Android 6.0 (API 23) and higher. Makes the PendingIntent immutable.
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        appWidgetId, // Use widget ID as request code for unique PendingIntents per widget
        buttonIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Set the PendingIntent as the click listener for the button
    views.setOnClickPendingIntent(R.id.xml_widget_button, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}