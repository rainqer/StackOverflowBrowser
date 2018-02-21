package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.infullmobile.android.infullmvp.bindView
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepositoryUser
import org.rhinoonabus.stackoverflowbrowser.domain.SearchResultItem
import org.rhinoonabus.stackoverflowbrowser.presentation.user_details.UserDetailsActivity

open class SearchResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val itemType: TextView by bindView(R.id.itemType)
    val itemName: TextView by bindView(R.id.itemName)
    val itemUrl: TextView by bindView(R.id.itemUrl)

    open fun bind(item: SearchResultItem) {
        itemView.setOnClickListener(getOnClickListenerBasingOnItemType(item))
        itemType.text = itemView.context.getString(item.typeNameRes)
        itemName.text = item.name
        itemUrl.text = item.url
    }

    private fun getOnClickListenerBasingOnItemType(item: SearchResultItem) =
        item.takeIf { it is CodeRepositoryUser }
                ?.let { View.OnClickListener { startUserDetailsScreenForId() } }

    private fun startUserDetailsScreenForId() =
            itemView.context.startActivity(UserDetailsActivity.getIntent(itemView.context))
}
