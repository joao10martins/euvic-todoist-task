package com.example.euvictodoist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.euvictodoist.adapters.CommentsAdapter
import com.example.euvictodoist.adapters.PostsAdapter
import com.example.euvictodoist.base.BaseViewModel
import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.network.repository.PostRepository
import kotlinx.coroutines.launch
import java.util.*

class PostsViewModel(private val repository: PostRepository) : BaseViewModel() {

    var postsAdapter = PostsAdapter()
    var commentsAdapter = CommentsAdapter()
    var postList = MutableLiveData<ArrayList<PostResponse>>()
    var postCommentsList = MutableLiveData<ArrayList<CommentResponse>>()

    fun requestPostsList() {
        viewModelScope.launch {
            postList.postValue(repository.getPostsList())
        }
    }
    fun requestPostCommentsList(postId : Int) {
        viewModelScope.launch {
            postCommentsList.postValue(repository.getPostCommentsList(postId))
        }
    }

    fun setPostList(list : List<PostResponse>) {
        postsAdapter.setData(list)
    }
    fun setPostCommentsList(list : List<CommentResponse>) {
        commentsAdapter.setData(list)
    }

    fun getAdapterPostList() : PostsAdapter {
        return postsAdapter
    }
    fun getAdapterPostCommentsList() : CommentsAdapter {
        return commentsAdapter
    }
}