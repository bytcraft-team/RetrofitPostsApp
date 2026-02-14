package com.example.retrofit.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.adapter.CommentAdapter
import com.example.retrofit.viewModel.PostViewModel

class ActivityComments : AppCompatActivity() {
    val viewModel: PostViewModel by viewModels()
    lateinit var adapter : CommentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(this)

        adapter = CommentAdapter(emptyList())
        recycler.adapter = adapter

        val postId = intent.getIntExtra("postId", 0)
        Log.d("POST_ID", "postId = $postId")

        if (postId != 0) {
            viewModel.fetchCommetById(postId)
        }


        viewModel.comments.observe(this) { comments ->
            adapter.updateData(comments)

            }
        }

        }





