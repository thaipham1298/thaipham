package com.example.hahalolofake.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.component.ViewModelFactory
import com.example.hahalolofake.databinding.ActivityMainBinding
import com.example.hahalolofake.ui.album.AlbumAct
import com.example.hahalolofake.ui.main.adapter.CharacterAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity @Inject constructor() : AbsActivity<ActivityMainBinding>() {

    private val viewModel: MainActivityViewModel by viewModels {
        viewModelFactory
    }

    private val characterAdapter by lazy {
        CharacterAdapter()
    }

     override fun initView() {
//        binding.recycleView.layoutManager =
//            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//        binding.recycleView.adapter = characterAdapter
//         viewModel.page.value = 1
//         lifecycleScope.launch {
//             viewModel.results.collectLatest {
//                 characterAdapter.submitData(it)
//             }
//         }
    }

    override fun initAction() {
        binding.albumBtn.setOnClickListener{
            val intent = Intent(this, AlbumAct::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun bindViewModel() {

    }

    private fun initData() {

    }

}