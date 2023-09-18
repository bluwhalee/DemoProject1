package com.example.demoproject1.bindingAdapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("firstString","secondString")
fun addSpace(view: TextView, firstString: String, secondString: String) {
        view.text = "$firstString $secondString"
}