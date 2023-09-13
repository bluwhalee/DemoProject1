package com.example.demoproject1.fragments

import android.app.DownloadManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demoproject1.MainApplication
import com.example.demoproject1.databinding.FragmentThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val galleryUri = it
            try{
                binding.ivUpload.setImageURI(galleryUri)
            }catch(e:Exception){
                e.printStackTrace()
            }

        }
        binding = FragmentThirdBinding.inflate(layoutInflater)
        binding.apply {
            btnUpdate.setOnClickListener{
                galleryLauncher.launch("image/*")
            }
            btnDownload.setOnClickListener{
                val url = binding.edtUrl.text.toString()
                val downloadManager = requireActivity().getSystemService(DownloadManager::class.java)
                val request = DownloadManager.Request(url.toUri())
                    .setMimeType("image/*")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ii.jpg")
                downloadManager.enqueue(request)
            }
        }

        return binding.root
    }

}