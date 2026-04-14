package com.example.retrofit.model

data class Post (
        val userId : Int ,
        val title : String ,
        val body : String,
        val id : Int? = null,
        ) : java.io.Serializable