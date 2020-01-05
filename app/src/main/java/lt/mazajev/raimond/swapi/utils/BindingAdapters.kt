package lt.mazajev.raimond.swapi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, items: List<T>?) {
    if (items == null) return
    (recyclerView.adapter as? BindableAdapter<T>)?.submitList(items)
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    if (resource == 0) return
    imageView.setImageResource(resource)
}

interface BindableAdapter<T> {
    fun submitList(data: List<T>?)
}