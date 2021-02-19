package com.eva.postify

import com.eva.postify.navigator.Navigator
import org.koin.dsl.module

val appModule = module {

    single<Navigator> { NavigatorImpl() }
}