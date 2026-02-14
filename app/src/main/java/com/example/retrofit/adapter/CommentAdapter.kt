package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.Comment



class CommentAdapter( private var comments : List<Comment>)
    : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){


        class CommentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val nom : TextView = itemView.findViewById(R.id.nameId)
            val body : TextView = itemView.findViewById(R.id.bodyId)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment , parent , false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.nom.text = comment.name
        holder.body.text = comment.body
    }

    override fun getItemCount(): Int {
       return comments.size
    }

    fun updateData(nouveauComments : List<Comment>){
        comments = nouveauComments
        notifyDataSetChanged()
    }
}