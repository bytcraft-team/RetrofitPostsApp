package com.example.retrofit.view

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.R
import com.example.retrofit.model.Post
import com.example.retrofit.viewModel.PostViewModel

class ActivityFormPost : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_post)

        val edtUserId = findViewById<EditText>(R.id.userId)
        val edtTitle = findViewById<EditText>(R.id.etTitre)
        val edtBody = findViewById<EditText>(R.id.etBody)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val txtResult = findViewById<TextView>(R.id.textResult)

        btnAdd.setOnClickListener {
            val userId = edtUserId.text.toString().toIntOrNull()
            val titre = edtTitle.text.toString()
            val body = edtBody.text.toString()

            if (userId == null || titre.isEmpty() || body.isEmpty()) {
                Toast.makeText(this, "Remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val post = Post(
                userId = userId,
                title = titre,
                body = body
            )

            viewModel.createNewPost(post)
        }

        viewModel.createdPost.observe(this) { post ->
            if (post != null) {
                txtResult.text =
                    "✅ Post créé avec succès\n\n" +
                            "ID: ${post.id}\n" +
                            "UserId: ${post.userId}\n" +
                            "Title: ${post.title}\n" +
                            "Body: ${post.body}"
            } else {
                txtResult.text = "❌ Erreur lors de l'ajout"
            }
        }
    }
}
