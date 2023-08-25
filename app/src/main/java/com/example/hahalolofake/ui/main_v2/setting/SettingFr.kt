package com.example.hahalolofake.ui.main_v2.setting

import android.content.Intent
import android.net.Uri
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsFragment
import com.example.hahalolofake.component.ViewModelFactory
import com.example.hahalolofake.databinding.FragmentSettingBinding
import com.example.hahalolofake.ui.main.MainActivity
import com.example.hahalolofake.ui.multi_lang.MultiLangAct
import com.example.hahalolofake.ui.main_v2.setting.dialog_rate.DialogRating
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import javax.inject.Inject

class SettingFr @Inject constructor(): AbsFragment<FragmentSettingBinding>() {

    private var manager: ReviewManager? = null
    private var reviewInfo: ReviewInfo? = null
    override fun getLayoutRes(): Int {
        return R.layout.fragment_setting
    }

    override fun initAction() {
        binding.imgBack.setOnClickListener {
        }
        binding.langSetting.setOnClickListener {
            startMultiLanguage()
        }
        binding.rateSetting.setOnClickListener {
            if (restorePrefData() == true) {
                Toast.makeText(requireContext(), "Thank you", Toast.LENGTH_SHORT).show()
            } else {
                showDialogRate()
            }
        }
        binding.privacySetting.setOnClickListener {
            startPolicy()
        }
    }

    override fun initView() {
    }
    private fun startMultiLanguage() {
        startActivity(MultiLangAct.getIntent(requireContext(), 2))
        (activity as? MainActivity)?.finish()
    }

    private fun startPolicy() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(this.getString(R.string.privacy_policy_link)))
        startActivity(intent)
    }

    private fun showDialogRate() {
        val ratingDialog = DialogRating(requireContext())
        ratingDialog.init(
            requireContext(),
            object : DialogRating.OnPress {
                override fun sendThank() {
                    Toast.makeText(requireContext(), "Thank you", Toast.LENGTH_SHORT)
                        .show()
                    ratingDialog.dismiss()
                }

                override fun rating() {
                    manager = ReviewManagerFactory.create(requireContext())
                    val request: Task<ReviewInfo> = manager!!.requestReviewFlow()
                    request.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            reviewInfo = task.result
                            val flow: Task<Void> =
                                manager!!.launchReviewFlow(activity!!, reviewInfo!!)
                            flow.addOnSuccessListener {
                                ratingDialog.dismiss()

                            }
                        } else {
                            ratingDialog.dismiss()
                        }
                    }
                }

                override fun cancel() {}
                override fun later() {
                    ratingDialog.dismiss()
                }
            }
        )
        try {
            ratingDialog.show()
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }
    }

    private fun restorePrefData(): Boolean? {
        val pref = activity?.getSharedPreferences("myPref", AppCompatActivity.MODE_PRIVATE)
        return pref?.getBoolean("isRate", false)
    }
}