package com.example.hahalolofake.ui.permission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsActivity
import com.example.hahalolofake.component.ViewModelFactory
import com.example.hahalolofake.databinding.ActivityPermissionBinding
import com.example.hahalolofake.ui.main.MainActivity
import com.example.hahalolofake.ui.main_v2.MainActivityV2
import com.officetool.pdfreader.pdfviewer.utils.DeviceUtil
import javax.inject.Inject

class PermissionAct @Inject constructor() : AbsActivity<ActivityPermissionBinding>() {

    override fun initView() {
    }

    override fun initAction() {
        binding.btnGo.setOnClickListener {
            if (binding.switch1.isChecked && binding.switch2.isChecked) {
                startActivity(Intent(this, MainActivityV2::class.java))
                finish()
            }
        }
        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            checkStorePermission()
        }
        binding.switch2.setOnCheckedChangeListener { buttonView, isChecked ->
            checkCameraPermission()
        }
        maxPermission()
    }


    override fun getContentView(): Int {
        return R.layout.activity_permission
    }
    override fun bindViewModel() {
    }

    private fun checkStorePermission() {
        try {
            if (DeviceUtil.hasStoragePermission(this)) {
                binding.switch1.isChecked = true
                binding.switch1.isEnabled = false
            } else {
                binding.switch1.isEnabled = true
                binding.switch1.isChecked = false
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), REQUEST_CODE_STORAGE_PERMISSION)
                else
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_STORAGE_PERMISSION)
            }
        } catch (e: Exception) {
            Log.i("Tuananh", e.toString())
        }
    }
    private fun maxPermission(){
        if(!binding.switch1.isChecked){
            binding.switch1.setOnClickListener {
                indexClickPermissionStore++
                if (indexClickPermissionStore > 2) {
                    startSettingApp()
                }
            }
        }

        if(!binding.switch2.isChecked){
            binding.switch2.setOnClickListener {
                indexClickPermissionCamera++
                if (indexClickPermissionCamera > 2) {
                    startSettingApp()
                }
            }
        }
    }

    private fun startSettingApp() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        requestAppSettingsLauncher.launch(intent)
    }

    private val requestAppSettingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (indexClickPermissionStore > 2) {
                    checkStorePermission()
                }
                if (indexClickPermissionCamera > 2) {
                    checkCameraPermission()
                }
            }
        }

    private fun checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            binding.switch2.isChecked = true
            binding.switch2.isEnabled = false
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
            binding.switch2.isEnabled = true
            binding.switch2.isChecked = false
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (binding.switch1.isChecked&& binding.switch2.isChecked){
            binding.btnGo.visibility= View.VISIBLE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_STORAGE_PERMISSION -> {
                binding.switch1.isChecked =
                    grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            }

            CAMERA_PERMISSION_CODE -> {
                binding.switch2.isChecked =
                    grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
            }
        }
    }
    override fun onResume() {
        super.onResume()
        binding.switch1.isChecked = DeviceUtil.hasStoragePermission(this)
    }

    companion object {
        const val REQUEST_CODE_STORAGE_PERMISSION = 101
        const val CAMERA_PERMISSION_CODE = 1001
        var indexClickPermissionStore = 0
        var indexClickPermissionCamera = 0
    }
}