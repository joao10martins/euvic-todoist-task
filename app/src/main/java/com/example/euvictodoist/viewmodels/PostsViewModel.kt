package com.example.euvictodoist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.euvictodoist.base.BaseViewModel
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.network.repository.PostRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class PostsViewModel(private val repository: PostRepository) : BaseViewModel() {

    private val _postList = MutableLiveData<ArrayList<PostResponse>>()
    val postList: LiveData<ArrayList<PostResponse>> get() = _postList

    fun requestPostsList() {
        viewModelScope.launch {
            _postList.postValue(repository.getPostsList())
        }
    }
}