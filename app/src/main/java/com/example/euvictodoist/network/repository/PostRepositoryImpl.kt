package com.example.euvictodoist.network.repository;

import com.example.euvictodoist.models.CommentResponse
import com.example.euvictodoist.models.PostResponse;
import com.example.euvictodoist.network.services.PostService;

class PostRepositoryImpl(private val postService: PostService) : PostRepository {

    override suspend fun getPostsList() : ArrayList<PostResponse> =
        runCatching {
            postService.getPostsList()
        }.getOrElse {
            throw Exception(it.message)
        }

    override suspend fun getPostCommentsList(id : Int) : ArrayList<CommentResponse> =
        runCatching {
            postService.getCommentsList(id)
        }.getOrElse {
            throw Exception(it.message)
        }
}