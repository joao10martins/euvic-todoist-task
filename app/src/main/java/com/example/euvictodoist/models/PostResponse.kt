package com.example.euvictodoist.models

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

class PostResponse(
    @SerializedName("userId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title")val title : String,
    @SerializedName("body") val body : String
) : BaseObservable()