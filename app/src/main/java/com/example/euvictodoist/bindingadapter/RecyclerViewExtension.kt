package com.example.euvictodoist.bindingadapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.prepareAdapter(adapter: RecyclerView.Adapter<*>?) {
    adapter?.let {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        this.adapter = it
    }
}