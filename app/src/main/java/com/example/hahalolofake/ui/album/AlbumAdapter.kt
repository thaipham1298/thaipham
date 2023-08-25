package com.example.hahalolofake.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hahalolofake.data.models.AlbumEntity
import com.example.hahalolofake.databinding.ItemAlbumBinding
import com.facebook.appevents.codeless.internal.EventBinding

class AlbumAdapter(private val albumList: List<Int>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(albumImageResource: Int) {
            binding.imgItems.setImageResource(albumImageResource)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumImageResource = albumList[position]
        holder.bind(albumImageResource)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}
