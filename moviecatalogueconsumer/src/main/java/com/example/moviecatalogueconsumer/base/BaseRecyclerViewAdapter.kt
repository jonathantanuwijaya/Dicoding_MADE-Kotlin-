package com.example.moviecatalogueconsumer.base

import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView

interface FilterResultListener<T> {
    fun onFilterResult(query: String?): MutableList<T>
}

abstract class BaseRecyclerViewAdapter<VH : RecyclerView.ViewHolder, T>
    : RecyclerView.Adapter<VH>(), Filterable, FilterResultListener<T> {

    private var items: MutableList<T>
    private var tempItems: MutableList<T>
    var isSearching: Boolean = false

    abstract fun onBindViewHolder(holder: VH, item: T, position: Int)

    init {
        items = mutableListOf()
        tempItems = mutableListOf()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                isSearching = true
                val charString = constraint.toString()
                val filterResults = FilterResults()
                val filterRes = onFilterResult(charString)

                filterResults.values = filterRes
                filterResults.count = filterRes.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                items = results.values as MutableList<T>
                notifyDataSetChanged()
                isSearching = false
            }
        }
    }

    override fun onFilterResult(query: String?): MutableList<T> {
        return this.tempItems
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, this.items[position], position)
    }

    fun getItem(position: Int): T? = items[position]

    private fun addItem(items: MutableList<T>) {
        this.items.addAll(items)
        this.tempItems.addAll(items)
        this.notifyDataSetChanged()
    }

    private fun clear() {
        this.items.clear()
    }

    fun setItem(item: MutableList<T>) {
        clear()
        addItem(item)
    }
}