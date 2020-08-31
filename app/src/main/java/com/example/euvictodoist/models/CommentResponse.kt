package com.example.euvictodoist.models

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class CommentResponse(
    @SerializedName("postId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name")val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("body") val body : String
) : BaseObservable()