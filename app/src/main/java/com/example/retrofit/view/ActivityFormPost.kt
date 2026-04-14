package com.example.retrofit.view

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.R
import com.example.retrofit.model.Post
import com.example.retrofit.viewModel.PostViewModel

class ActivityFormPost : AppCompatActivity() {

    // Initialisation du ViewModel pour interagir avec les données
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_post)

        val edtUserId = findViewById<EditText>(R.id.userId)
        val edtTitle = findViewById<EditText>(R.id.etTitre)
        val edtBody = findViewById<EditText>(R.id.etBody)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val txtResult = findViewById<TextView>(R.id.textResult)


        // On récupère les données envoyées par l'activité précédente via l'Intent
        val postId = intent.getIntExtra("id", -1)
        val postTitle = intent.getStringExtra("title")
        val postBody = intent.getStringExtra("body")

        if (postId != -1) {
            edtTitle.setText(postTitle)
            edtBody.setText(postBody)
            btnAdd.text = "Modifier"
            edtUserId.isEnabled = false
        }

        btnAdd.setOnClickListener {
            val userIdString = edtUserId.text.toString()
            val titre = edtTitle.text.toString()
            val body = edtBody.text.toString()

            if (titre.isEmpty() || body.isEmpty() || (postId == -1 && userIdString.isEmpty())) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = userIdString.toIntOrNull() ?: 1

            if (postId != -1) {
                //  crée un objet Post avec l'ID existant pour la mise à jour :
                val updatedPost = Post(id = postId, userId = userId, title = titre, body = body)
                viewModel.updatePost(postId, updatedPost)
            } else {
                // crée un nouveau Post  :
                val newPost = Post(id = null, userId = userId, title = titre, body = body)
                viewModel.createNewPost(newPost)
            }
        }


        // Observation de la création d'un post
        viewModel.createdPost.observe(this) { post ->
            if (post != null) {
                txtResult.text =" Post créé avec succès\n\n" +
                        "ID: ${post.id}\n" +
                        "UserId: ${post.userId}\n" +
                        "Title: ${post.title}\n" +
                        "Body: ${post.body}"
                Toast.makeText(this, "Ajout réussi !", Toast.LENGTH_SHORT).show()

            }
        }

        // Observation de la modification d'un post
        viewModel.updatPost.observe(this) { post ->
            if (post != null) {
                txtResult.text = "✅ Post modifié avec succès\nID: ${post.id}"
                Toast.makeText(this, "Modification réussie !", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}