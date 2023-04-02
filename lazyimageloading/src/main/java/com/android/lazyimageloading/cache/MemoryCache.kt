package com.android.lazyimageloading.cache

import android.graphics.Bitmap
import android.util.LruCache

class MemoryCache: LruCache<String, Bitmap>(((Runtime.getRuntime().maxMemory()/1024)/4).toInt()),ImageCache {
    override fun getImage(key: String): Bitmap? {
        return get(key)
    }

    override fun putImage(key: String, bitmap: Bitmap) {
        put(key,bitmap)
    }
}