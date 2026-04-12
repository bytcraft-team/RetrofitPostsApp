package com.example.retrofit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.Comment
import com.example.retrofit.model.Post
import com.example.retrofit.repository.PostRepository
import kotlinx.coroutines.launch


class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _posts = MutableLiveData<List<Post>>()
    val posts : LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            _posts.value = repository.getPosts() ?: emptyList()
        }
    }

    private val _post = MutableLiveData<Post?>()
    val post : LiveData<Post?> = _post

    fun fetchPostById(postId: Int){
        viewModelScope.launch {
            _post.value = repository.getPostById(postId)
        }
    }

    private val _comments = MutableLiveData<List<Comment>>()
    val comments : LiveData<List<Comment>> = _comments

    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost

    fun createNewPost(post: Post) {
        viewModelScope.launch {
            _createdPost.value = repository.addPost(post)
        }
    }

}