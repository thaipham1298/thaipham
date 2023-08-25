package com.example.hahalolofake.ui.main_v2.home

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.hahalolofake.R
import com.example.hahalolofake.base.AbsFragment
import com.example.hahalolofake.component.ViewModelFactory
import com.example.hahalolofake.databinding.FragmentHomeBinding
import com.example.hahalolofake.ui.main_v2.MainActivityV2
import com.example.hahalolofake.ui.main_v2.setting.SettingFr
import javax.inject.Inject

class HomeFr @Inject constructor(): AbsFragment<FragmentHomeBinding>() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initAction() {
        binding.menuBtn.setOnClickListener {
            binding.drawerLayout.openDrawer(binding.nav)
            hideKeyboard()
        }
    }

    override fun initView() {
        setOnBackPressed()
        toggle = ActionBarDrawerToggle(
            requireActivity(), binding.drawerLayout,
            R.string.intro_next, R.string.intro_next
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        showMenuLeft()
    }

    private fun showMenuLeft() {
        binding.nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startActivity(Intent(requireContext(),MainActivityV2::class.java))
                }

                R.id.menu_1 -> {
                    Toast.makeText(requireContext(), "menu 2", Toast.LENGTH_SHORT).show()
                }

                R.id.menu_2 -> {
                    Toast.makeText(requireContext(), "menu 3", Toast.LENGTH_SHORT).show()

                }

                R.id.setting -> {
                    replaceFragment(SettingFr())
                }
            }
            binding.drawerLayout.closeDrawer(binding.nav)
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.commit()
        when (fragment) {
            is HomeFr -> binding.title.text = "Home"
            is SettingFr -> binding.title.text = "Setting"
        }
    }
    private fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity?.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
        }
    }
    private fun setOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    isEnabled = false
                    if (binding.drawerLayout.isDrawerOpen(binding.nav)) {
                        binding.drawerLayout.closeDrawer(binding.nav)
                    }
                }
            }
        })
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}