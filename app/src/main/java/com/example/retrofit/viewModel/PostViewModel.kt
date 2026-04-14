package com.example.retrofit.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.Post
import com.example.retrofit.repository.PostRepository
import kotlinx.coroutines.launch


class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _posts = MutableLiveData<List<Post>>()
    val posts : LiveData<List<Post>?> = _posts

    fun fetchPosts() {
        viewModelScope.launch {

            val result = repository.getPosts()
            _posts.value = result ?: emptyList()
        }
    }

    private val _post = MutableLiveData<Post?>()
    val post : LiveData<Post?> = _post

    fun fetchPostById(postId: Int){
        viewModelScope.launch {
            val result = repository.getPostById(postId)
            if(result != null) {
                _post.value = result
            }
        }
    }



    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost

    fun createNewPost(post: Post) {
        viewModelScope.launch {
            _createdPost.value = repository.addPost(post)
        }
    }

    private val _updatPost = MutableLiveData<Post?>()
    val updatPost : LiveData<Post?> = _updatPost



    fun updatePost(id : Int , post : Post) {
        viewModelScope.launch {
            val result = repository.updatePost(id , post)

            if(result != null ){
                val list = _posts.value?.toMutableList() ?: mutableListOf()
                val index = list.indexOfFirst { it.id == id  }

                if(index != -1){
                    list[index] = result

                    _posts.value = list
                }

                _updatPost.value = result

            }
        }
    }

    private val _deletePost = MutableLiveData<Boolean>()
    val deletePost : LiveData<Boolean> = _deletePost

    fun deletePost(id : Int){
        viewModelScope.launch {
            val result = repository.deletePost(id)
            if(result){
                val list = _posts.value?.toMutableList() ?: mutableListOf()

                list.removeIf { it.id == id }

                _posts.value = list

                _deletePost.value = true

            }else{
                _deletePost.value = false

            }
        }
    }

}