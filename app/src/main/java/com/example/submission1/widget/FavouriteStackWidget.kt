package com.example.submission1.widget

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.submission1.R
import com.example.submission1.pages.DetailMovieActivity
import com.example.submission1.pages.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class FavouriteStackWidget : AppWidgetProvider() {
    companion object {
        private const val ONCLICK = "com.example.submission1.ONCLICK"
        const val EXTRA_ITEM = "com.example.submission1.EXTRA_ITEM"
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

        val onclicked = Intent(context, DetailMovieActivity::class.java)
        onclicked.action = ONCLICK
        onclicked.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

        val onclickedTemplate = TaskStackBuilder.create(context)
            .addNextIntentWithParentStack(onclicked)
            .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)



        val launch = Intent(context, MainActivity::class.java)
        val pending = PendingIntent.getBroadcast(context,0,onclicked,PendingIntent.FLAG_UPDATE_CURRENT)
        val oddd = PendingIntent.getActivity(context,0,launch,0)
//        views.setOnClickPendingIntent(R.id.stack_view,oddd)
        views.setPendingIntentTemplate(R.id.stack_view,onclickedTemplate)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if(intent?.action != null){
            if (intent.action == ONCLICK){
                val viewIndex = intent.getIntExtra(EXTRA_ITEM,0)
                Toast.makeText(context,"Touched View $viewIndex",Toast.LENGTH_SHORT).show()

            }
        }
    }
}
