package com.pavelrukin.reddit.ui.image

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.pavelrukin.reddit.utils.FileUtils


class ImageViewModel(val fileUtils: FileUtils) : ViewModel() {



    fun saveImage(image: Bitmap,id:String) {
        fileUtils.saveImage(image,id)


    }
}