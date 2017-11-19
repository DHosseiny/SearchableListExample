package com.izigzag.messenger.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import ir.parsdroidgroup.searchlistexample.SearchListener
import ir.parsdroidgroup.searchlistexample.adapter.Searchable
import java.io.Serializable
import java.util.*

/**
 * Zigzag Project
 * Created by Seyyed Davud Hosseiny on 7/4/2017.
 */

abstract class BaseSearchAdapter<T : Searchable<*>, VH : RecyclerView.ViewHolder>(items: List<T>) : RecyclerView.Adapter<VH>(), SearchListener {

    //all of item given to adapter added to this list
    private val allItems: MutableList<T>

    //when search happened appropriate items remains in this list and others clears
    val filteredItems: MutableList<T>

    //searched keyword
    private var keyword: String? = null

    init {
        this.allItems = ArrayList()
        this.allItems.addAll(items)
        this.filteredItems = ArrayList()
        this.filteredItems.addAll(items)
    }

//    override fun getItemForPosition(position: Int) = filteredItems[position]

    override fun getItemCount() = filteredItems.size


    abstract override fun onBindViewHolder(holder: VH, position: Int)

    /**
     * [.toString] that used here for classes like [Integer], [Long]
     * and many other classes works normally

     * @param keyword to search.
     */
    override fun onSearch(keyword: String) {
        this.keyword = keyword

        filteredItems.clear()
        for (item in allItems) {
            onSearch(item, false)
        }

        notifyDataSetChanged()
    }

    override fun onDismiss() {

        filteredItems.clear()
        filteredItems.addAll(allItems)
        notifyDataSetChanged()
    }

    fun notifyDataSetChanged(allItems: List<T>) {
        this.allItems.clear()
        this.allItems.addAll(allItems)
        this.filteredItems.clear()
        this.filteredItems.addAll(allItems)
        notifyDataSetChanged()
    }

    fun notifyItemInserted(item: T) {
        var position = RecyclerView.NO_POSITION
        for (i in allItems.indices) {
            val existItem = allItems[i]
            if (existItem.itemID.toString() == item.itemID.toString()) {
                position = i
                break
            }
        }

        if (position == RecyclerView.NO_POSITION) {
            allItems.add(item)
            if (!TextUtils.isEmpty(keyword))
                position = onSearch(item, false)
            else {
                filteredItems.add(item)
                position = filteredItems.size - 1
            }
            if (position != RecyclerView.NO_POSITION)
                notifyItemInserted(position)
        } else {
            allItems.removeAt(position)
            allItems.add(position, item)
            if (!TextUtils.isEmpty(keyword))
                position = onSearch(item, true)
            else {
                filteredItems.removeAt(position)
                filteredItems.add(position, item)
            }
            if (position != RecyclerView.NO_POSITION)
                notifyItemChanged(position)
        }
    }

    fun notifyItemChangedInternal(id: Any, payload: Any?) {

        allItems.forEachIndexed { _, it ->
            if (it.itemID == id) {
                notifyItemChanged(it, payload)
                return@forEachIndexed
            }
        }

    }

    abstract fun notifyItemChanged(item: T, payload: Any?)

    private fun onSearch(item: T, exist: Boolean): Int {

        val itemString = item.searchArgument.toString().toLowerCase(Locale.getDefault())

        if (itemString.contains(keyword!!.toLowerCase(Locale.getDefault()))) {
            if (exist) {
                for (i in filteredItems.indices) {
                    val filteredItem = filteredItems[i]
                    if (filteredItem.itemID.toString() == item.itemID.toString()) {
                        filteredItems.removeAt(i)
                        filteredItems.add(i, item)
                        return i
                    }
                }

            } else {
                filteredItems.add(item)
                return filteredItems.size - 1
            }
        }
        return -1
    }
}

interface OnItemClickListener<in E> : Serializable {
    fun onItemClick(item: E)
}

