package com.example.hahalolofake.ui.main_v2

import androidx.paging.ExperimentalPagingApi
import com.example.hahalolofake.ui.main_v2.home.HomeFr
import com.example.hahalolofake.ui.main_v2.setting.SettingFr
import dagger.Module
import dagger.android.ContributesAndroidInjector

@ExperimentalPagingApi
@Module
abstract class MainV2Module {
    @ContributesAndroidInjector
    abstract fun contributeHomeFr(): HomeFr

    @ContributesAndroidInjector
    abstract fun contributeSettingFr(): SettingFr



}