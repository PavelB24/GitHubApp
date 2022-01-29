package ru.barinov.githubclient

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.githubclient.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter: RecyclerView.Adapter<ItemViewHolder>() {

   private var itemList = emptyList<GitHubUsersSerializedName>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolderBinding: RecyclerViewItemBinding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.nameTextView.text= item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(list: List<GitHubUsersSerializedName>){
        itemList = list
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): GitHubUsersSerializedName{
        return itemList[position]
    }
}