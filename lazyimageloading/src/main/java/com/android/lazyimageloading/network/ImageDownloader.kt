package com.android.lazyimageloading.network

import android.graphics.Bitmap

interface ImageDownloader {

    fun download(url:String,callBack:(Bitmap?)->Unit)

}