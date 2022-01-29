package ru.barinov.githubclient.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.githubclient.databinding.RecyclerViewItemBinding

class ItemViewHolder(binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root) {

    val nameTextView = binding.userNameTextView

}