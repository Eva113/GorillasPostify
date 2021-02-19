package com.eva.postify.feature_details

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * This rule should be used when you want all actions in a stream to be handled in a synchronous manner
 *
 * This should be the default rule we use
 */
internal class TrampolineSchedulerRule : TestRule {

    private val trampolineScheduler = Schedulers.trampoline()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { trampolineScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { trampolineScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { trampolineScheduler }
                RxAndroidPlugins.setMainThreadSchedulerHandler { trampolineScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampolineScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}