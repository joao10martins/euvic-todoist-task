package com.example.euvictodoist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.euvictodoist.R
import com.example.euvictodoist.models.CommentResponse

class CommentsAdapter() : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>(){

    private var comments = listOf<CommentResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        // Data binding is not being used in the layout due to a delicate bug associated with the binding of views with a dynamic size.
        // Causing the layout to be faulted, with incorrect view sizes.
        /*val binding = ListItemCommentBinding.inflate(LayoutInflater.from(parent.context))
        return CommentsViewHolder(binding)*/

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)
        return CommentsViewHolder(inflater, inflater.context)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class CommentsViewHolder(itemView : View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val username = itemView.findViewById<AppCompatTextView>(R.id.commentUsername)
        private val email = itemView.findViewById<AppCompatTextView>(R.id.commentEmail)
        private val body = itemView.findViewById<AppCompatTextView>(R.id.commentBody)

        fun bind(item: CommentResponse) {
            username.text = item.name
            email.text = item.email
            body.text = item.body
            /*binding.item = item
            binding.executePendingBindings()*/
        }
    }

    fun setData(comments: List<CommentResponse>) {
        this.comments = comments
        notifyDataSetChanged()
    }

}