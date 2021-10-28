package com.example.euvictodoist.viewmodels

import androidx.lifecycle.LiveData
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

class CommentsViewModel(private val repository: PostRepository) : BaseViewModel() {

    private val _postCommentsList = MutableLiveData<ArrayList<CommentResponse>>()
    val postCommentsList : LiveData<ArrayList<CommentResponse>> = _postCommentsList

    fun requestPostCommentsList(postId : Int) {
        viewModelScope.launch {
            _postCommentsList.postValue(repository.getPostCommentsList(postId))
        }
    }
}