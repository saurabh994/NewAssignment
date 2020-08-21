package com.example.assignmentproject.ui.news

import androidx.recyclerview.widget.DiffUtil
import com.example.assignmentproject.R
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.ui.base.adapter.BaseListAdapter
import com.example.assignmentproject.ui.base.adapter.BaseViewHolder

class MainAdapter(val listener: MainNavigator) : BaseListAdapter<ArticlesItem>(AvatarDiffCallback) {

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_news

    override fun getCallbackForPosition(position: Int): Any = listener

    override fun onBindViewHolder(holder: BaseViewHolder<ArticlesItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            getItem(position)?.apply {
//                listener.onClickItem(position, this)
            }
        }
    }
    companion object {
        val AvatarDiffCallback = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.source?.name == newItem.source?.name
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
