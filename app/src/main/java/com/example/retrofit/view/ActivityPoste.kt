package com.example.retrofit.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.adapter.PostAdapter
import com.example.retrofit.viewModel.PostViewModel

class ActivityPoste : AppCompatActivity() {

    lateinit var editText : EditText
    val viewModel : PostViewModel by viewModels()
    lateinit var btnSearch : Button
    lateinit var btnPosts : Button
    lateinit var textPost : TextView
    lateinit var textPosts : RecyclerView

    lateinit var btnAjout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_posts)
        editText = findViewById(R.id.EditTextId)
        btnSearch = findViewById(R.id.btnSearch)
        btnPosts = findViewById(R.id.btnPosts)
        textPost = findViewById(R.id.result)
        textPosts = findViewById(R.id.result2)
        btnAjout = findViewById(R.id.btnAjout)

        textPosts.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            val postId = editText.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)

            } else {
                textPost.text = "Veuillez entrer un ID valide."
            }
        }
        viewModel.post.observe(this) { post ->
            textPost.text = post?.let {
                "Id : ${it.id} \n Titre: ${it.title} \n Contenu : ${it.body}"
            } ?: "Aucun post trouve."

        }


        textPosts.layoutManager = LinearLayoutManager(this)
        btnPosts.setOnClickListener {
            viewModel.fetchPosts()
        }
        viewModel.posts.observe(this){posts ->
            textPosts.adapter = PostAdapter(posts)


        }


        textPost.setOnClickListener {
            val postId = editText.text.toString().toIntOrNull()
            if (postId != null) {
                val intent = Intent(this, ActivityComments::class.java)
                intent.putExtra("postId", postId)
                startActivity(intent)
            } else {
                textPost.text = "Veuillez entrer un ID valide."
            }


        }

        btnAjout.setOnClickListener {
            val intent = Intent(this, ActivityFormPost::class.java)
            startActivity(intent)
        }
    }
}
