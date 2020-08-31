package com.example.euvictodoist.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewBinding {

    companion object {

        /**
         * Custom binding RecycleView view method to use in XML
         */
        @JvmStatic
        @BindingAdapter("setAdapter")
        fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
            adapter?.let {
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = it
            }
        }
    }
}