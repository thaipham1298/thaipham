package com.example.hahalolofake.ui.main_v2

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.databinding.ActivityMainV2Binding
import com.example.hahalolofake.ui.main_v2.home.HomeFr
import com.example.hahalolofake.ui.main_v2.setting.SettingFr
import javax.inject.Inject

class MainActivityV2 @Inject constructor() : AbsActivity<ActivityMainV2Binding>() {

    override fun initView() {
        replaceFragment(HomeFr())
    }

    override fun initAction() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFr())
                }

                R.id.tool -> {
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                }

                R.id.file -> {
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
                }

                R.id.setting -> {
                    replaceFragment(SettingFr())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun getContentView(): Int {
        return R.layout.activity_main_v2
    }

    override fun bindViewModel() {
    }
}