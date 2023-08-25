package com.example.hahalolofake.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hahalolofake.data.models.AlbumEntity
import com.example.hahalolofake.databinding.ItemAlbumBinding
import com.facebook.appevents.codeless.internal.EventBinding

class AlbumAdapter(private val albumlist:List<AlbumEntity>):RecyclerView.Adapter<AlbumViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return albumlist.size

    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumList = albumlist[position]
        holder.bind(albumList)
    }

}