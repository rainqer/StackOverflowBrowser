package org.rhinoonabus.stackoverflowbrowser.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.infullmobile.android.infullmvp.bindView
import org.rhinoonabus.stackoverflowbrowser.R
import org.rhinoonabus.stackoverflowbrowser.domain.CodeRepository

open class SearchResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val repositoryName: TextView by bindView(R.id.repoName)
    val repositoryDescription: TextView by bindView(R.id.repoDescription)
    val repositoryUrl: TextView by bindView(R.id.repoUrl)

    open fun bind(codeRepository: CodeRepository) {
        repositoryName.text = codeRepository.name
        repositoryDescription.text = codeRepository.description
        repositoryUrl.text = codeRepository.url
    }
}
