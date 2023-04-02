package com.android.lazyimageloading.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.android.lazyimageloading.cache.ImageCache
import okhttp3.OkHttpClient
import okhttp3.Request

class NewImageDownload(
    val url: String,
    val imageView: ImageView,
    val callBack:(Bitmap,String)->Unit
) : DownloadTask<Bitmap?>() {

    private val uiHandler = Handler(Looper.getMainLooper())


    override fun download(url: String): Bitmap? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                val inputStream = response.body?.byteStream()
                inputStream?.let {
                    val bitmap = BitmapFactory.decodeStream(it)
                    return bitmap
                }
            } else {

                return null

            }
        }
        return null
    }

    override fun call(): Bitmap? {
        val bitmap = download(url)
        bitmap?.let {
            if (imageView.tag == url) {
                uiHandler.post { imageView.setImageBitmap(bitmap) }
            }
            callBack(bitmap,url)
        }
        return bitmap
    }


}