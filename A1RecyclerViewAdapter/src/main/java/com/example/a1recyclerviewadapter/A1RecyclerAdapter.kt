package com.example.a1recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val HEADER_VIEW = 0
private const val ITEM_VIEW = 1
private const val FIRST_ITEM = 2
private const val FOOTER_VIEW = 3
private const val SEPARATOR_VIEW = 4

abstract class A1RecyclerAdapter<T>(private val context: Context, private val arrayList: List<T>)
    : RecyclerView.Adapter<ItemViewHolder>(), AdapterConfiguration<T>
{
    val textView = TextView(context)
    lateinit var indexs: MutableList<Int>
    override fun getItemViewType(position: Int): Int
    {
        indexs = getSeparatorIndex()
        indexs.sort()



        if (headerResourceId != 0 || footerResourceId != 0)
        {
            if (position == 0)
            {
                if (headerResourceId != 0)
                {

                    return HEADER_VIEW
                }
                else return FIRST_ITEM
            }

            if (position == 1)
                return FIRST_ITEM
            else if (position >= (arrayList.size + minusHeaderCount))
            {

                if (footerResourceId != 0) return FOOTER_VIEW
                else
                {
                    if (indexs.contains(position))
                        return SEPARATOR_VIEW
                    else
                        return ITEM_VIEW
                }
            }
            else
            {
                if (indexs.contains(position))
                    return SEPARATOR_VIEW
                else
                    return ITEM_VIEW
            }
        }
        else
        {

            if (position == 0)
                return FIRST_ITEM
            else
            {
                if (indexs.contains(position))
                    return SEPARATOR_VIEW
                else
                    return ITEM_VIEW
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {

        if (viewType == HEADER_VIEW)
        {
            val view = LayoutInflater.from(context).inflate(headerResourceId, parent, false)
            return ItemViewHolder(view)
        }
        else
            if (viewType == FOOTER_VIEW)
            {

                //   linearLayout.addView(textView)
                val view = LayoutInflater.from(context).inflate(footerResourceId, parent, false)
                //linearLayout.addView(view)
                return ItemViewHolder(view)
            }
            else
            {


                val view: View
                if (viewType == FIRST_ITEM)
                {
                    if (firstItemLayout != 0)
                        view = LayoutInflater.from(context).inflate(firstItemLayout, parent, false)
                    else
                        view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)
                }
                else
                    view = LayoutInflater.from(context).inflate(layoutResourceId, parent, false)


                if (viewType == SEPARATOR_VIEW && customSeparatorLayoutId != 0)
                {

                    val linearLayout = LayoutInflater.from(context).inflate(R.layout.default_linearlayout, parent, false) as LinearLayout

                    val seperatorView = LayoutInflater.from(context).inflate(customSeparatorLayoutId, parent, false)

                    linearLayout.addView(seperatorView)
                    linearLayout.addView(view)

                    return ItemViewHolder(linearLayout)
                }
                else
                    return ItemViewHolder(view)

            }
    }

    var onItemClickListener: OnItemClickListener? = null
    var tagName: Any? = null

    interface OnItemClickListener
    {

        fun onItemClick(view: View?, position: Int, vararg tagName: Any?)
    }

    fun setOnItemClickListener(tagName: Any?, onItemClickListener: OnItemClickListener?)
    {
        this.onItemClickListener = onItemClickListener
        this.tagName = tagName
    }

    var minusValue = 0
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {

        if(firstItemLayout!=0)
        if (holder.itemViewType == FIRST_ITEM)
        {
            onFirstItemBind(arrayList[position - minusValue],holder)
            return
        }
        if (holder.itemViewType == SEPARATOR_VIEW)
        {
            if (indexs.contains(position))
                onSeparatorBind(indexs.indexOf(position), holder.itemView.findViewById(R.id.parentlinear))

        }
        if (headerResourceId != 0 || footerResourceId != 0)
        {

            if (position > 0)
            {

                if (position >= (arrayList.size + minusHeaderCount))
                {

                    if (footerResourceId != 0) return onFooterViewBind(holder)
                    else return onDataBind(arrayList[position - minusValue], position - minusValue, holder)
                }
                else
                {

                    return onDataBind(arrayList[position - minusValue], position - minusValue, holder)
                }


            }
            else
            {
                if (headerResourceId != 0) onHeaderViewBind(holder)
                else onDataBind(arrayList[position - minusValue], position - minusValue, holder)
            }
        }
        else
            onDataBind(arrayList[position], position, holder)

        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener {
                onItemClickListener!!.onItemClick(holder.itemView, position, arrayList[position])
            }
        }
    }

    var minusHeaderCount = 0
    override fun getItemCount(): Int
    {


        var size: Int
        minusValue = 0
        size = arrayList.size
        if (headerResourceId != 0)
        {
            size = size + 1
            minusValue = 1
            minusHeaderCount = 1
        }
        if (footerResourceId != 0)
            size = size + 1



        return size

    }


}