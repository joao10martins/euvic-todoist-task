package com.example.euvictodoist.ui.comments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.euvictodoist.R
import com.example.euvictodoist.databinding.ActivityCommentsBinding
import com.example.euvictodoist.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject

class CommentsActivity : AppCompatActivity() {

    val postsViewModel : PostsViewModel by inject()
    lateinit var binding: ActivityCommentsBinding
    private var postId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)
        binding.apply {
            lifecycleOwner = this@CommentsActivity
            viewModel = postsViewModel
        }
        supportActionBar?.title = "Comments"

        postId = intent.getIntExtra("postId", 0)
        initActivity()
    }


    private fun initActivity() {
        postsViewModel.requestPostCommentsList(postId) // Requests the TODOS list from the API.
        postsViewModel.postCommentsList.observe({lifecycle}) {
            postsViewModel.setPostCommentsList(it)
            binding.rvCommentsList.scheduleLayoutAnimation()
        }
    }
}