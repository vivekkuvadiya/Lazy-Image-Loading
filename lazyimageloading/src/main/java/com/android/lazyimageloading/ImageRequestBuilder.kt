package com.android.lazyimageloading

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.android.lazyimageloading.cache.ImageCache
import com.android.lazyimageloading.network.ImageDownloader
import com.android.lazyimageloading.network.NewImageDownload
import java.util.concurrent.Executors

class ImageRequestBuilder(private val context: Context) {

    lateinit var url: String
    private lateinit var diskCache:ImageCache
    lateinit var memoryCache:ImageCache
    lateinit var imageDownloader:ImageDownloader
    private var placeHolder: Drawable? = null
    private var errorPlaceHolder: Drawable? = null

    private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())


    fun setImageDiskCache(diskCache: ImageCache):ImageRequestBuilder {
        this.diskCache = diskCache
        return this
    }

    fun setImageMemoryCache(memoryCache:ImageCache):ImageRequestBuilder{
        this.memoryCache = memoryCache
        return this
    }

    fun setImageDownloadServices(imageDownloader:ImageDownloader):ImageRequestBuilder{
        this.imageDownloader = imageDownloader
        return this
    }

    fun setUrl(url: String): ImageRequestBuilder {
        this.url = url
        return this
    }

    fun setPlaceHolder(@DrawableRes resourceId: Int): ImageRequestBuilder {
        this.placeHolder = AppCompatResources.getDrawable(context, resourceId)
        return this
    }

    fun setPlaceHolder(drawable: Drawable): ImageRequestBuilder {
        this.placeHolder = drawable
        return this
    }


    fun into(imageView: ImageView) {
        val image = memoryCache.getImage(url)
        if (image != null) {
            imageView.setImageBitmap(image)
            return
        }
        val diskImage = diskCache.getImage(url)
        if (diskImage != null) {
            imageView.setImageBitmap(diskImage)
            memoryCache.putImage(url, diskImage)
            return
        }
        imageView.setImageResource(0)
        placeHolder?.let {
            imageView.setImageDrawable(it)
        }
        imageView.tag = url

            executorService.submit(NewImageDownload(url,imageView){bitmap: Bitmap, url: String ->
                diskCache.putImage(url,bitmap)
                memoryCache.putImage(url,bitmap)
            })

    }




}