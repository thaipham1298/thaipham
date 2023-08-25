package com.example.hahalolofake.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hahalolofake.data.models.AlbumEntity
import com.example.hahalolofake.data.models.ResultEntity
import com.example.hahalolofake.databinding.ItemAlbumBinding
import com.example.hahalolofake.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class AlbumViewHolder(
    private val binding: ItemAlbumBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entity: AlbumEntity) {
        binding.imgItems.setImageResource(entity.img?:0)
    }

    companion object {
        fun create(
            viewGroup: ViewGroup,
        ): AlbumViewHolder {
            return AlbumViewHolder(
                binding = ItemAlbumBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }
    }
}