package com.example.euvictodoist.network.repository

import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.network.ApiClient
import com.example.euvictodoist.network.services.PostService
import java.util.*

class PostRepository(api : ApiClient) {

    private var postService : PostService = api.getApiClient().create(PostService::class.java)

    suspend fun getPostsList() : ArrayList<PostResponse> =
        runCatching {
            postService.getPostsList()
        }.getOrElse {
            throw Exception(it.message)
        }

    suspend fun getPostCommentsList(id : Int) : ArrayList<CommentResponse> =
        runCatching {
            postService.getCommentsList(id)
        }.getOrElse {
            throw Exception(it.message)
        }
}