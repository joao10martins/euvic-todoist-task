package com.example.euvictodoist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.euvictodoist.R
import com.example.euvictodoist.models.PostResponse

class PostsAdapter(private val callback: PostsAdapterCallback) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){

    private var posts = listOf<PostResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return PostViewHolder(inflater, inflater.context)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class PostViewHolder(itemView : View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<AppCompatTextView>(R.id.postTitle)
        private val body = itemView.findViewById<AppCompatTextView>(R.id.postBody)

        fun bind(item: PostResponse) {
            title.text = item.title
            body.text = item.body
            itemView.setOnClickListener {
                callback.onItemSelected(item.id)
            }
        }
    }

    fun setData(posts: List<PostResponse>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    interface PostsAdapterCallback {
        fun onItemSelected(id: Int)
    }
}