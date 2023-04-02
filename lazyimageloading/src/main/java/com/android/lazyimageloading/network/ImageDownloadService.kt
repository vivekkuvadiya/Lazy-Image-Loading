package com.android.lazyimageloading.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ImageDownloadService:ImageDownloader {

    override fun download(url: String, callBack: (Bitmap?) -> Unit) {
        val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .build()
        CoroutineScope(Dispatchers.IO).launch{
            okHttpClient.newCall(request).execute().use {response ->
                if (response.isSuccessful){
                    val inputStream = response.body?.byteStream()
                    inputStream?.let {
                        val bitmap = BitmapFactory.decodeStream(it)
                        withContext(Dispatchers.Main){
                            callBack(bitmap)
                        }
                    }
                }else{
                    withContext(Dispatchers.Main){
                        callBack(null)
                    }
                }
            }
        }

    }

}