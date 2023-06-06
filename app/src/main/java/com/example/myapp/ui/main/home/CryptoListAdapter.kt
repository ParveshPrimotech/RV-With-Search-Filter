package com.example.myapp.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.databinding.LayoutUsersBinding
import com.example.myapp.model.Cryptocurrency

class CryptoListAdapter :
    ListAdapter<Cryptocurrency, CryptoListAdapter.ViewHolder>(UserDiffCallBack()) {

    class ViewHolder(val binding: LayoutUsersBinding) : RecyclerView.ViewHolder(binding.root)

    private class UserDiffCallBack : DiffUtil.ItemCallback<Cryptocurrency>() {
        override fun areItemsTheSame(oldItem: Cryptocurrency, newItem: Cryptocurrency): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Cryptocurrency, newItem: Cryptocurrency): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutUsersBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_users, parent, false
        )
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvUserName.text = getItem(position).name
    }
}