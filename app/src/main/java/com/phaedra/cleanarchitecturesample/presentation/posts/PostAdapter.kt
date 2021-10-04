package com.phaedra.cleanarchitecturesample.presentation.posts

import com.phaedra.cleanarchitecturesample.R
import com.phaedra.cleanarchitecturesample.databinding.ItemPostsBinding
import com.phaedra.cleanarchitecturesample.domain.model.Post
import com.phaedra.cleanarchitecturesample.presentation.base.BaseRecyclerAdapter
import javax.inject.Inject

class PostAdapter @Inject constructor()
    : BaseRecyclerAdapter<Post, ItemPostsBinding>() {
    override fun getLayout() = R.layout.item_posts

    override fun onBindViewHolder(holder: Companion.BaseViewHolder<ItemPostsBinding>, position: Int) {
        holder.binding.model = items[position]
        holder.binding.executePendingBindings()
        val model=items[position]
        holder.itemView.setOnClickListener {
            listener?.invoke(holder.binding.root, items[position],position )
        }

    }
}