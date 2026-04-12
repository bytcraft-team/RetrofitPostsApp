package com.example.retrofit.repository

import com.example.retrofit.service.RetrofitClient
import com.example.retrofit.model.Comment

class CommentRepository {
    val api = RetrofitClient.instance


    suspend fun getCommentByPost(postId : Int) : MutableList<Comment>?{
        return try {
            api.getCommentByPost(postId)
        }catch (e : Exception){
           null
        }
    }
}