package com.example.submission1.widget

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.example.submission1.BuildConfig.BASE_URL_POSTER
import com.example.submission1.R
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType
import com.example.submission1.model.Movie
import com.example.submission1.pages.MainActivity
import com.example.submission1.provider.FavoriteHelper
import com.example.submission1.utils.Constant
import java.net.URL


class StackRemoteViewsAdapter(
    val context: Context
) : RemoteViewsService.RemoteViewsFactory {
    private val tag = StackRemoteViewsAdapter::class.java.simpleName
    private val mWidgetItems = mutableListOf<FavMovie?>()
    private val widgetPresenter = WidgetPresenter(context)


    override fun onCreate() {}

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {}

    override fun onDataSetChanged() {
        mWidgetItems.clear()
        mWidgetItems.addAll(widgetPresenter.getWidgetContent())
    }

    override fun getViewAt(position: Int): RemoteViews {
        val movie = mWidgetItems[position]
        val rv = RemoteViews(context.packageName, R.layout.favourite_stack_widget)
        val type : FilmType
        try {
            val imageUrl = URL(BASE_URL_POSTER + "/w185/" + movie?.poster_path)
            val bitmap = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
//            val extras = bundleOf(FavouriteStackWidget.EXTRA_ITEM to position)
            val fillIntent = Intent()
//            val launch = Intent(context, MainActivity::class.java)
//            val searchtype: FavoriteHelper? = null
//            val oddd = PendingIntent.getActivity(context,0,launch,0)
//            val uri = Uri.parse(CONTENT_URI.toString() + "/"+movie?.id)
//            fillIntent.setData(uri)

            println("cek movie id = $movie.id")
//            val movie2: FavMovie? = movie?.id?.let { searchtype?.queryById(it) }
//            movie?.id?.let { searchtype?.queryById(it) }


            fillIntent.putExtra(Constant.INTENT_DATA, movie)
            println("type movie : ${movie?.type}")
            if(movie?.type == "MOVIE"){
                type = FilmType.MOVIE
            }else{
                type = FilmType.TVSHOW
            }

            fillIntent.putExtra(Constant.FRAGMENT_DATA, type)
            rv.setImageViewBitmap(R.id.imageView, bitmap)
            rv.setTextViewText(R.id.tv_title, movie?.origTitle)
//            rv.setOnClickPendingIntent(R.id.imageView,oddd)
            rv.setOnClickFillInIntent(R.id.imageView, fillIntent)

        } catch (e: Exception) {
            Log.e(tag, e.message)
        }
        return rv
    }

}