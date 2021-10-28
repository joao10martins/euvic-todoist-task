package com.example.euvictodoist.ui.comments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.euvictodoist.R
import com.example.euvictodoist.adapters.CommentsAdapter
import com.example.euvictodoist.adapters.PostsAdapter
import com.example.euvictodoist.bindingadapter.prepareAdapter
import com.example.euvictodoist.databinding.ActivityCommentsBinding
import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.viewmodels.CommentsViewModel
import com.example.euvictodoist.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject

class CommentsActivity : AppCompatActivity() {

    companion object {
        private const val ARG_POST_ID = "ARG_POST_ID"
        fun openCommentsActivity(context: Context, id: Int) : Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(ARG_POST_ID, id)
            return intent
        }
    }

    private val commentsViewModel : CommentsViewModel by inject()
    lateinit var binding: ActivityCommentsBinding
    var commentsAdapter = CommentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)
        binding.apply {
            lifecycleOwner = this@CommentsActivity
        }
        supportActionBar?.title = getString(R.string.comments_activity_app_bar_title)

        val postId = intent.getIntExtra(ARG_POST_ID, 0)
        requestPostCommentsList(postId)
    }


    private fun requestPostCommentsList(postId: Int) {
        commentsViewModel.requestPostCommentsList(postId) // Requests the TODOS list from the API.
        commentsViewModel.postCommentsList.observe({lifecycle}) {
            setupData(it)
        }
    }

    private fun setupData(data: ArrayList<CommentResponse>) {
        commentsAdapter.apply {
            setData(data)
        }
        binding.rvCommentsList.prepareAdapter(commentsAdapter)
        binding.rvCommentsList.scheduleLayoutAnimation()
    }
}