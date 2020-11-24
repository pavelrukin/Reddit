package com.pavelrukin.reddit.ui.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.pavelrukin.reddit.R
import com.pavelrukin.reddit.databinding.ImageFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageFragment : Fragment() {
    private lateinit var binding: ImageFragmentBinding
    val args: ImageFragmentArgs by navArgs()
    val viewModel: ImageViewModel by viewModel()
    val TAG = "ImageFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.image_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load(args.thumb.url.replace(".gifv", ".jpg"))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        viewModel.saveImage(resource,args.thumb.author)
                        Toast.makeText(requireContext(), "Download Started", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        Toast.makeText(requireContext(), "Downloaded!", Toast.LENGTH_SHORT).show()
                    }
                })
        }
            val replace = args.thumb.url.replace(".gifv", ".jpg")
              Glide.with(this).asBitmap().load(replace).error(R.drawable.ic_image_not_supported).into(binding.largeImage)
    }


}