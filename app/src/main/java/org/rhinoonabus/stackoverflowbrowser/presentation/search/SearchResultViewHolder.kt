package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.infullmobile.android.infullmvp.bindView
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.SearchResultItem

open class SearchResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemType: TextView by bindView(R.id.itemType)
    val itemName: TextView by bindView(R.id.itemName)
    val itemUrl: TextView by bindView(R.id.itemUrl)

    open fun bind(item: SearchResultItem) {
        itemType.text = itemView.context.getString(item.typeNameRes)
        itemName.text = item.name
        itemUrl.text = item.url
    }
}
