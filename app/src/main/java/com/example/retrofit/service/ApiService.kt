package com.example.retrofit.service

import com.example.retrofit.model.Comment
import com.example.retrofit.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int) : Post

    @GET("comments")
    suspend fun getCommentById(@Query("postId") postId: Int) : MutableList<Comment>

    @POST("posts")
    suspend fun addPost(@Body post : Post) : Response<Post>
}

