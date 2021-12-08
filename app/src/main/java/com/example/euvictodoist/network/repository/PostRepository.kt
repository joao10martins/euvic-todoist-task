package com.example.euvictodoist.network.repository

import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse
import java.util.ArrayList

interface PostRepository {

    /**
     * Get all posts available
     */
    suspend fun getPostsList() : ArrayList<PostResponse>

    /**
     * Get all comments present in post by identifier
     */
    suspend fun getPostCommentsList(id : Int) : ArrayList<CommentResponse>
}