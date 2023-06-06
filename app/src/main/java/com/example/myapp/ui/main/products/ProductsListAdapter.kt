package com.example.myapp.ui.main.products

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapp.R
import com.example.myapp.databinding.LayoutProductsBinding
import com.example.myapp.model.ProductModelItem
import com.example.myapp.ui.main.capitalize

class ProductsListAdapter(var context: Context,val itemClick :(Int) -> Unit) :
    ListAdapter<ProductModelItem, ProductsListAdapter.ViewHolder>(UserDiffCallBack()){

    var glide : RequestManager = Glide.with(context)

    class ViewHolder(val binding: LayoutProductsBinding) : RecyclerView.ViewHolder(binding.root)

    private class UserDiffCallBack : DiffUtil.ItemCallback<ProductModelItem>() {
        override fun areItemsTheSame(oldItem: ProductModelItem, newItem: ProductModelItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ProductModelItem, newItem: ProductModelItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutProductsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_products, parent, false
        )
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply {
            holder.binding.tvTitle.text = this.title.capitalize()
            holder.binding.tvCategory.text =this.category
            holder.binding.tvDesc.text = this.description.capitalize()
            holder.binding.tvPrice.text = this.price.toString()
            glide.load(this.image).into(holder.binding.ivProduct)

            holder.itemView.setOnClickListener {
                itemClick(this.id)
            }
        }
    }
}

