package com.example.hahalolofake.ui.album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hahalolofake.R
import com.example.hahalolofake.databinding.ItemAlbumBinding

class AlbumAct : AppCompatActivity() {
    private lateinit var binding:ItemAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        val recyclerView: RecyclerView = findViewById(R.id.rcv_album)

        val imgs = listOf(
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
            R.drawable.album1,
        )

        val albumAdapter = AlbumAdapter(imgs)
        recyclerView.adapter = albumAdapter
    }
}
