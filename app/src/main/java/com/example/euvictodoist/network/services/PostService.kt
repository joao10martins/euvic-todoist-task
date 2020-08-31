package com.example.euvictodoist.network.services

import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface PostService {

    // Note: the 'suspend' keyword can be only be called from within a Coroutine, otherwise we'll get a compiler error.

    @GET("posts")
    suspend fun getPostsList() : ArrayList<PostResponse>

    @GET("posts/{id}/comments")
    suspend fun getCommentsList(@Path("id") id : Int) : ArrayList<CommentResponse>
}