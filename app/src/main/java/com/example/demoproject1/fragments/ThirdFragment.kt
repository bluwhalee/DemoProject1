package com.example.demoproject1.fragments

import android.app.DownloadManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demoproject1.MainApplication
import com.example.demoproject1.databinding.FragmentThirdBinding
import com.example.demoproject1.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

    // region properties
    private lateinit var binding: FragmentThirdBinding
    private lateinit var galleryLauncher : ActivityResultLauncher<String>

    // region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentThirdBinding.inflate(layoutInflater)
        init()
        return binding.root
    }

    // region private methods
    private fun init(){
        setupGallaryActivity()
        setupOnClicks()
    }

    private fun setupGallaryActivity(){
         galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val galleryUri = it
            try{
                binding.ivUpload.setImageURI(galleryUri)
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun setupOnClicks(){
        binding.apply {

            btnUpdate.setOnClickListener{
                launchGallery()
            }

            btnDownload.setOnClickListener{
                startDownload()
            }

        }
    }
    private fun launchGallery(){
        galleryLauncher.launch(Constants.gallery.imageType)
    }
    private fun startDownload()
    {
//       val request = DownloadManager.Request(binding.edtUrl.text.toString().toUri())
//            .setMimeType("image/*")
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ii.jpg")

        DownloadManager.Request(binding.edtUrl.text.toString().toUri()).apply {
            setMimeType(Constants.gallery.imageType)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ii.jpg")

            activity?.getSystemService(DownloadManager::class.java)?.enqueue(this)
        }

//        activity?.getSystemService(DownloadManager::class.java)?.enqueue(request)
    }
}
