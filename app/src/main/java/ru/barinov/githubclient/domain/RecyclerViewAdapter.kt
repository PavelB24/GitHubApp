package ru.barinov.githubclient.domain

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.githubclient.data.GitHubUser
import ru.barinov.githubclient.databinding.RecyclerViewItemBinding
import ru.barinov.githubclient.domain.OnItemClickListener
import ru.barinov.githubclient.ui.ItemViewHolder

class RecyclerViewAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = emptyList<GitHubUser>()

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val viewHolderBinding: RecyclerViewItemBinding =
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)

        return ItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.nameTextView.text = item.name
        holder.itemView.setOnClickListener { listener }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(list: List<GitHubUser>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun getItem(position: Int): GitHubUser {
        return itemList[position]
    }
}