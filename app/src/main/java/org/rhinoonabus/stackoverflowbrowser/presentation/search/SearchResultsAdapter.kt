package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepository
import java.util.ArrayList

open class SearchResultsAdapter: RecyclerView.Adapter<SearchResultViewHolder>() {

    private val data: ArrayList<CodeRepository> = ArrayList()

    open fun setData(newData: List<CodeRepository>) {
        if (data != newData) {
            data.clear()
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchResultViewHolder(inflateView(parent))

    private fun inflateView(parent: ViewGroup) =
            LayoutInflater.from(parent.context).inflate(R.layout.search_result_view_holder, parent, false)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.bind(data[position])
}
