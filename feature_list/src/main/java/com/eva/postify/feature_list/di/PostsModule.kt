package com.eva.postify.feature_list.di

import com.eva.postify.feature_list.domain.PostsFetcherUseCase
import com.eva.postify.feature_list.domain.PostsFetcherUseCaseImpl
import com.eva.postify.feature_list.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postsModule = module {
    single<PostsFetcherUseCase> { PostsFetcherUseCaseImpl(get()) }
    viewModel { PostsViewModel(get()) }
}