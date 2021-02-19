package com.eva.postify.feature_list.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eva.postify.data.model.Post
import com.eva.postify.feature_list.databinding.ItemPostBinding

internal class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var posts = listOf<Post>()
    var onLoadMore: () -> Unit = {}
    var onItemClicked: (post: Post) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(posts[position])
        if (position == posts.lastIndex) {
            onLoadMore()
        }
        holder.itemView.setOnClickListener {
            onItemClicked(posts[position])
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(postsList: List<Post>) {
        posts = postsList
        notifyDataSetChanged()
    }

    class PostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.postTitle.text = post.title
            binding.postDescription.text = post.body
        }
    }
}