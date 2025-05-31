package com.sample.glancewidget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Main GlanceAppWidget for our example.
 * This defines the UI using Composables.
 */
class MyGlanceAppWidget : GlanceAppWidget() {

    // This is where you define the UI of your widget using Compose
    override suspend fun provideGlance(context: Context, id: GlanceId) {
       provideContent {
           MyGlanceWidgetContent(context)
       }
    }

    // Define the content of the widget as a Composable function
    @Composable
    fun MyGlanceWidgetContent(context: Context) {
        // Get current time for display
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        // Box to apply background and corner radius to the entire widget area
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .appWidgetBackground() // Applies default widget background styling
                .background(Color(0xFF202020)) // Dark background for contrast
                .cornerRadius(16.dp) // Rounded corners for the widget
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = context.getString(R.string.glance_widget_title),
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = GlanceModifier.fillMaxWidth()
                )
                Spacer(modifier = GlanceModifier.height(8.dp))
                Text(
                    text = "Last updated:\n$currentTime",
                    style = TextStyle(
                        color = ColorProvider(Color.LightGray),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = GlanceModifier.fillMaxWidth()
                )
                Spacer(modifier = GlanceModifier.height(16.dp))

                // Button to trigger a refresh
                // actionRunCallback will trigger the onRun method in MyGlanceWidgetActionCallback
                Text(
                    text = context.getString(R.string.glance_widget_button_text),
                    modifier = GlanceModifier
                        .background(Color(0xFFBB86FC)) // Purple button color
                        .cornerRadius(8.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable(
                            onClick = actionRunCallback<RefreshAction>()
                        ), // Make text clickable
                    style = TextStyle(color = ColorProvider(Color.White), fontSize = 14.sp)
                )
            }
        }
    }
}

/**
 * ActionCallback for handling clicks on the Glance widget.
 * This is triggered by actionRunCallback.
 */
class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        MyGlanceAppWidget().update(context, glanceId)
    }
}