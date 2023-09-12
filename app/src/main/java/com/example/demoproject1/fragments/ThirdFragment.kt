package com.example.demoproject1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demoproject1.MainApplication
import com.example.demoproject1.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        binding.btnUpload.setOnClickListener{
            galleryLauncher.launch("image/*")
        }
        binding.btnDownload.setOnClickListener{
            val url = binding.etURL.text.toString()
            Glide.with(MainApplication.applicationContext()).load(url).into(binding.ivDownload)
        }
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {}
    }
}