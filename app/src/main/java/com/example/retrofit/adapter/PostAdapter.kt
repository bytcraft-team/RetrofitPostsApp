package com.example.retrofit.adapter
import android.view.LayoutInflater
import com.example.retrofit.R
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.model.Post

class PostAdapter (private val posts : List<Post>)
    : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titre : TextView = itemView.findViewById(R.id.IdTitle)
        val body : TextView = itemView.findViewById(R.id.IdBody)
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
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}