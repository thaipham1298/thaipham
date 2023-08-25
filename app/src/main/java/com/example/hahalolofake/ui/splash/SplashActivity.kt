package com.example.hahalolofake.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.databinding.ActivitySplashBinding
import com.example.hahalolofake.ui.main.MainActivity
import com.example.hahalolofake.ui.multi_lang.MultiLangAct
import com.example.hahalolofake.ui.permission.PermissionAct
import com.example.hahalolofake.utils.SystemUtil
import com.google.android.gms.ads.MobileAds
import com.officetool.pdfreader.pdfviewer.utils.DeviceUtil

class SplashActivity : AbsActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUtil.setPreLanguage(this,SystemUtil.getPreLanguage(this))
        SystemUtil.setLocale(this)
        MobileAds.initialize(this) {}
    }

    override fun initView() {
        binding.imgLaunch.postDelayed({
            SystemUtil.setPreLanguage(this,SystemUtil.getPreLanguage(this))
            SystemUtil.setLocale(this)
            openMainActivity()
        }, 3000)
    }

    override fun initAction() {

    }

    override fun getContentView(): Int {
        return R.layout.activity_splash
    }

    override fun bindViewModel() {

    }

    private fun restorePrefData(): Boolean {
        val pref = baseContext.getSharedPreferences("myPref", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpened",false)
    }

    private fun openMainActivity() {
        if (DeviceUtil.hasCameraPermission(this)){
            if(restorePrefData()){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(MultiLangAct.getIntent(this, 1))
                finish()
            }
        }else{
            if (restorePrefData()){
                startActivity(Intent(this, PermissionAct::class.java))
                finish()
            }else{
                startActivity(MultiLangAct.getIntent(this, 1))
                finish()
            }
        }
    }

}