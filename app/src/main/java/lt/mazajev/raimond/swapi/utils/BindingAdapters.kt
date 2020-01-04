package com.freetimeprojects.mijiareader.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, items: List<T>?) {
    if (items == null) return
    (recyclerView.adapter as? BindableAdapter<T>)?.submitList(items)
}

interface BindableAdapter<T> {
    fun submitList(data: List<T>?)
}