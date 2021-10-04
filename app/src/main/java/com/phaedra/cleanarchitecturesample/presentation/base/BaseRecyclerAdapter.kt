package com.phaedra.cleanarchitecturesample.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<T : Any, VB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyclerAdapter.Companion.BaseViewHolder<VB>>() {

    open var items = mutableListOf<T>()

    var listener: ((view: View, item: T, position: Int) -> Unit)? = null
    fun clearItems(){
        this.items.clear()
        notifyDataSetChanged()
    }
    fun addItems(items: List<T>) {
        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    companion object {
        class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<VB>(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        )
    )

    override fun getItemCount() = this.items.size

    abstract fun getLayout(): Int

}