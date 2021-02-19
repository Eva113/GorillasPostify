package com.eva.postify.feature_details.di

import com.eva.postify.feature_details.domain.DetailsFetcherUseCase
import com.eva.postify.feature_details.domain.DetailsFetcherUseCaseImpl
import com.eva.postify.feature_details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single<DetailsFetcherUseCase> { DetailsFetcherUseCaseImpl(get()) }
    viewModel { DetailsViewModel(get()) }
}