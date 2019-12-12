package com.example.submission1.widget

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.example.submission1.R
import com.example.submission1.pages.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class FavouriteStackWidget : AppWidgetProvider() {
    companion object {
        private const val ONCLICK = "com.example.submission1.ONCLICK"
    }


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val intent = Intent(context, StackWidgetService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

        val views = RemoteViews(context.packageName, R.layout.fav_home_widget)
        views.setRemoteAdapter(R.id.stack_view, intent)
        views.setEmptyView(R.id.stack_view, R.id.empty_view)

        val onclicked = Intent(context, MainActivity::class.java)
        onclicked.action = ONCLICK
        onclicked.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

        val onclickedTemplate = TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(onclicked)
            .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)

        views.setPendingIntentTemplate(R.id.stack_view,onclickedTemplate)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }


}
