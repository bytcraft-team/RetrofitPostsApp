package com.example.retrofit.repository

import com.example.retrofit.model.Comment
import com.example.retrofit.model.Post
import com.example.retrofit.service.RetrofitClient



class PostRepository {
    private val apiService = RetrofitClient.instance

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()

        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }


    suspend fun addPost(post: Post): Post? {
        return try {
            val response = apiService.addPost(post)

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }

        } catch (e: Exception) {
            null
        }
    }

    suspend fun updatePost(postId: Int, post: Post) : Post?{
        return try {
            val response = apiService.updatePost(postId,post)
            if(response.isSuccessful){
                response.body()
            }else{
                null
            }
        }catch (e: Exception){
            null
        }
    }

}