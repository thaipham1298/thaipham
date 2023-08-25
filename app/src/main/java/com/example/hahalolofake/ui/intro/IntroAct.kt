package com.example.hahalolofake.ui.intro

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.data.models.IntroEntity
import com.example.hahalolofake.databinding.ActivityIntroBinding
import com.example.hahalolofake.ui.intro.adaper.IntroAdapter
import com.example.hahalolofake.ui.permission.PermissionAct
import javax.inject.Inject

class IntroAct @Inject constructor() : AbsActivity<ActivityIntroBinding>() {
    private lateinit var introAdapter: IntroAdapter
    private var position = 0

    override fun getContentView(): Int {
        return R.layout.activity_intro
    }

    override fun bindViewModel() {
    }

    override fun initView() {
        val intro = listOf(
            IntroEntity(
                getString(R.string.title_intro_1),
                getString(R.string.content_intro_1),
                R.drawable.intro_1
            ), IntroEntity(
                getString(R.string.title_intro_2),
                getString(R.string.content_intro_2),
                R.drawable.intro_2
            ), IntroEntity(
                getString(R.string.title_intro_3),
                getString(R.string.content_intro_3),
                R.drawable.intro_3
            )
        )
        introAdapter = IntroAdapter(intro)
        binding.viewPager.adapter = introAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.buttonBackIntro.alpha = 0F
                } else {
                    binding.buttonBackIntro.alpha = 1F
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.buttonStartIntro.setOnClickListener {
            position = binding.viewPager.currentItem
            if (position < intro.size) {
                position++
                binding.viewPager.currentItem = position
            }

            if (position == intro.size) {
                startActivity(Intent(this, PermissionAct::class.java))
                finish()
            }
        }
        binding.buttonBackIntro.setOnClickListener {
            position = binding.viewPager.currentItem
            if (position < intro.size) {
                position--
                binding.viewPager.currentItem = position
            }

        }
    }

    override fun initAction() {

    }
}