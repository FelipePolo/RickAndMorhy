package com.felipepolo.pruebaenvivo.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}