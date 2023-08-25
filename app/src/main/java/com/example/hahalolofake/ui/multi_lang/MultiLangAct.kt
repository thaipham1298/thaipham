package com.example.hahalolofake.ui.multi_lang

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.databinding.ActivityMultilangBinding
import com.example.hahalolofake.ui.intro.IntroAct
import com.example.hahalolofake.ui.multi_lang.adapter.MultiLangAdapter
import com.example.hahalolofake.utils.SystemUtil
import javax.inject.Inject

class MultiLangAct @Inject constructor() : AbsActivity<ActivityMultilangBinding>() {
    private var type: Int? = null

    override fun initView() {
        binding.rcvLangs.adapter = MultiLangAdapter(
            MultiLangAdapter.dummyData,
            this,
            getPosition(),
        ) { position, item ->
            SystemUtil.setPreLanguage(this, item.code)
            SystemUtil.setLocale(this)
        }
    }

    override fun initAction() {
        type = intent.getIntExtra(TYPE_LANG, 0)
        when (type) {
            1 -> {
                binding.typeLang1.visibility = View.VISIBLE
                binding.typeLang2.visibility = View.GONE
                binding.btnChooseLang.setOnClickListener {
                    startActivity(Intent(this, IntroAct::class.java))
                    finish()
                }
            }

            2 -> {
                binding.typeLang1.visibility = View.GONE
                binding.typeLang2.visibility = View.VISIBLE
                binding.imgBack.setOnClickListener {
//                    val intent = Intent(this, MainActivityV2::class.java)
//                    intent.putExtra("back_setting", true)
//                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun getContentView(): Int {
        return R.layout.activity_multilang
    }

    override fun bindViewModel() {
    }

    companion object {
        private const val TYPE_LANG = "MultiLangAct_Lang"
        fun getIntent(context: Context, type: Int): Intent {
            val intent = Intent(context, MultiLangAct::class.java)
            intent.putExtra(TYPE_LANG, type)
            return intent
        }
    }

    private fun getPosition(): Int {
        val pref = baseContext.getSharedPreferences("myPref", MODE_PRIVATE)
        return pref.getInt("positionLang", 0)
    }
}