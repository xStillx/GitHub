package com.example.github.features.search.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.ItemRepositoryBinding
import com.example.github.databinding.ItemUserBinding
import com.example.github.features.search.domain.model.Git
import com.example.github.features.search.domain.model.Item

class SearchAdapter(
private val git: Git,
private val onUserClick: (url: String) -> Unit
): RecyclerView.Adapter<ViewHolder>() {


    class UserViewHolder(view: View, private val onUserClick: (url: String) -> Unit): ViewHolder(view) {

        private val viewBinding: ItemUserBinding by viewBinding(ItemUserBinding::bind)

        fun bind(item: Item){
            viewBinding.tvName.text = item.login
            Glide.with(viewBinding.ivAvatar).load(item.avatarUrl).into(viewBinding.ivAvatar)
            viewBinding.tvScore.text = item.score.toString()
            viewBinding.clUser.setOnClickListener {
                onUserClick.invoke(item.htmlUrl!!)
            }
        }

    }

    class RepositoryViewHolder(view: View): ViewHolder(view){

        private val viewBinding: ItemRepositoryBinding by viewBinding(ItemRepositoryBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(item: Item){
            viewBinding.tvName.text = item.name
            viewBinding.tvForks.text = "${item.forksCount}\nForks"
            viewBinding.tvDescription.text = item.description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)

        return when(viewType){
            R.layout.item_user -> UserViewHolder(inflate.inflate(viewType, parent, false), onUserClick)
            R.layout.item_repository -> RepositoryViewHolder(inflate.inflate(viewType, parent, false))
            else -> throw IllegalArgumentException("Unsupported layout")
        }
    }

    override fun getItemCount(): Int {
        return git.items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is UserViewHolder -> {
                holder.bind(git.items[position])
            }
            is RepositoryViewHolder -> {
                holder.bind(git.items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(git.items[position].userType){
            true -> R.layout.item_user
            false -> R.layout.item_repository
        }
    }
}