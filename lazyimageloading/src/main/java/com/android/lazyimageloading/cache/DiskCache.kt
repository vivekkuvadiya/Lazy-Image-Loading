package com.android.lazyimageloading.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Environment.isExternalStorageRemovable
import com.android.lazyimageloading.utils.encodeToBase64
import kotlinx.coroutines.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class DiskCache(private val context:Context):ImageCache {

    override fun getImage(key: String): Bitmap? {
        val cacheDirectory = getCacheDirectory()
        if (cacheDirectory.exists() && cacheDirectory.length() > 0){
            val file = File(getCacheDirectory(),key.encodeToBase64())
            val contains = cacheDirectory.listFiles()?.contains(file)
            if (contains == true){
                return BitmapFactory.decodeFile(file.path)
            }
        }
        return null
    }



    override fun putImage(key: String, bitmap: Bitmap) {
        CoroutineScope(Dispatchers.IO).launch {
            val file = File(getCacheDirectory(), createKey(key))
            putBimapToDisk(file,bitmap)
        }
    }

    private fun putBimapToDisk(file: File, bitmap: Bitmap){
        val outputStream = BufferedOutputStream(FileOutputStream(file))
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
    }

    private fun createKey(imageUrl:String):String{
        return imageUrl.encodeToBase64()
    }

    private fun getCacheDirectory(): File {
        val cachePath =
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
                || !isExternalStorageRemovable()
            ) {
                context.externalCacheDir?.path
            } else {
                context.cacheDir.path
            }
        val file = File(cachePath + File.separator + "LazyImageCache")
        if (!file.exists())
            file.mkdirs()
        return file
    }
}