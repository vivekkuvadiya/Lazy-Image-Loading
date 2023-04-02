package com.android.lazyimageloading.cache

import android.graphics.Bitmap

interface ImageCache {

    fun getImage(key:String):Bitmap?
    fun putImage(key:String,bitmap: Bitmap)

}