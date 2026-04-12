package com.example.retrofit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.Comment
import androidx.lifecycle.ViewModel
import com.example.retrofit.repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {

    val repository = CommentRepository()

    private val _comments = MutableLiveData<List<Comment>>()
    val comments : LiveData<List<Comment>> = _comments

    fun fetchCommentsByPost(postId : Int) {
        viewModelScope.launch {
            _comments.value = repository.getCommentByPost(postId) ?: emptyList()
        }
    }
}