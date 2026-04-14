package com.example.retrofit.adapter
import android.view.LayoutInflater
import com.example.retrofit.R
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.model.Post

class PostAdapter (
    private val posts: List<Post> ,
    private val onDeleteClick : (Post) -> Unit,
    private val onUpdateClick : (Post) -> Unit)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    inner class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titre : TextView = itemView.findViewById(R.id.IdTitle)
        val body : TextView = itemView.findViewById(R.id.IdBody)
        val btnD : Button = itemView.findViewById(R.id.btnDelete)

        val btnU : Button = itemView.findViewById(R.id.btnUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post , parent , false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.titre.text = post.title
        holder.body.text = post.body

        holder.btnD.setOnClickListener {
            onDeleteClick(post)
        }

        holder.btnU.setOnClickListener {
            onUpdateClick(post)
        }
    }



    override fun getItemCount(): Int {
        return posts.size
    }
}