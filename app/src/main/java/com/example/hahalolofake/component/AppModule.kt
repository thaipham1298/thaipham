package com.example.hahalolofake.component

import androidx.paging.ExperimentalPagingApi
import com.example.hahalolofake.data.api.ApiModule
import com.example.hahalolofake.data.repository.RepositoryModule
import dagger.Module

@OptIn(ExperimentalPagingApi::class)
@Module(
    includes = [
        // module of Activity && ViewModel
        ActivityModule::class,
        ViewModelModule::class,

        // module of data
        ApiModule::class,
        RepositoryModule::class,
    ]
)
class AppModule