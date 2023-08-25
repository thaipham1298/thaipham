package com.example.hahalolofake.ui.album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.data.models.AlbumEntity
import com.example.hahalolofake.databinding.ActivityAlbumBinding
import com.example.hahalolofake.databinding.ItemAlbumBinding
import com.example.hahalolofake.ui.main.adapter.CharacterAdapter
import javax.inject.Inject

class AlbumAct @Inject constructor() : AbsActivity<ActivityAlbumBinding>() {

   private val list: List<AlbumEntity> = listOf(
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
        AlbumEntity(R.drawable.album1),
    )
    private val albumAdapter by lazy {
        AlbumAdapter(list)
    }

    override fun initView() {

    }

    override fun initAction() {

        binding.rcvAlbum.adapter = albumAdapter
    }

    override fun getContentView(): Int {
     return R.layout.activity_album
    }

    override fun bindViewModel() {

    }
}
