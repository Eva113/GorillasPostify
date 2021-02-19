package com.eva.postify.data.di

import com.apollographql.apollo.ApolloClient
import com.eva.postify.data.mapper.Mapper
import com.eva.postify.data.mapper.MapperImpl
import com.eva.postify.data.repository.DetailRepository
import com.eva.postify.data.repository.DetailRepositoryImpl
import com.eva.postify.data.repository.PostsRepository
import com.eva.postify.data.repository.PostsRepositoryImpl
import com.eva.postify.data.service.QueryProvider
import com.eva.postify.data.service.QueryProviderImpl
import org.koin.dsl.module

val dataModule = module {
    single { ApolloClient.builder().serverUrl("https://graphqlzero.almansi.me/api").build() }

    single<QueryProvider> { QueryProviderImpl(get()) }

    single<Mapper> { MapperImpl() }

    single<PostsRepository> { PostsRepositoryImpl(get(), get()) }

    single<DetailRepository> { DetailRepositoryImpl(get(), get()) }
}