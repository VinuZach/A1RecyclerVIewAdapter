package com.example.a1recyclerviewadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface AdapterConfiguration<T>
{
    val layoutResourceId: Int
    val headerResourceId: Int
        get() = 0
    val footerResourceId: Int
        get() = 0

    val firstItemLayout: Int
        get() = 0
    val customSeparatorLayoutId: Int
        get() = 0

    fun getSeparatorIndex(): MutableList<Int>
    {
        return mutableListOf<Int>()
    }

    fun onDataBind(model: T, position: Int, holder: RecyclerView.ViewHolder?)
    fun onHeaderViewBind(holder: RecyclerView.ViewHolder?)
    {
    }

    fun onFooterViewBind(holder: RecyclerView.ViewHolder?)
    {
    }

    fun onFirstItemBind(model: T,holder: RecyclerView.ViewHolder?)
    {
    }

    fun onSeparatorBind(index: Int, holder: View?)
    {
    }
}
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)