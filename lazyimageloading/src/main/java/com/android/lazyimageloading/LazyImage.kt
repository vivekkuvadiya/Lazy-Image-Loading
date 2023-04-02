package com.android.lazyimageloading

import android.content.Context
import android.view.View
import com.android.lazyimageloading.cache.DiskCache
import com.android.lazyimageloading.cache.ImageCache
import com.android.lazyimageloading.cache.MemoryCache
import com.android.lazyimageloading.network.ImageDownloadService
import com.android.lazyimageloading.network.ImageDownloader

class LazyImage private constructor(context: Context) {

    private val imageRequestBuilder: ImageRequestBuilder = ImageRequestBuilder(context)
    private val diskCache: ImageCache = DiskCache(context)
    private val memoryCache: ImageCache = MemoryCache()
    private val downloadServices: ImageDownloader = ImageDownloadService()


    companion object {

        @Volatile
        private var instance: LazyImage? = null

        @Synchronized
        fun get(context: Context): LazyImage {
            return instance ?: LazyImage(context.applicationContext).also { instance = it }
        }

        @Synchronized
        fun get(view:View): LazyImage {
            return instance ?: LazyImage(view.context.applicationContext).also { instance = it }
        }

    }


    fun load(url: String): ImageRequestBuilder {
        return imageRequestBuilder
            .setImageDiskCache(diskCache)
            .setImageMemoryCache(memoryCache)
            .setImageDownloadServices(downloadServices)
            .setUrl(url)

    }


}