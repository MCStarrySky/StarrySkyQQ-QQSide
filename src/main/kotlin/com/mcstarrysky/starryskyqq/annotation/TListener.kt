package com.mcstarrysky.starryskyqq.annotation

import com.mcstarrysky.starryskyqq.platform.Plugin
import net.mamoe.mirai.event.ListenerHost
import net.mamoe.mirai.event.globalEventChannel
import taboolib.common.io.getInstance
import taboolib.common.io.runningClassesWithoutLibrary

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.annotation.TListener
 *
 * @author xiaomu
 * @since 2022/9/21 10:55 AM
 */
@Target(AnnotationTarget.CLASS)
annotation class TListener {

    companion object {

        fun inject() {
            runningClassesWithoutLibrary.forEach {
                if (it.isAnnotationPresent(TListener::class.java) && ListenerHost::class.java.isAssignableFrom(it)) {
                    Plugin.INSTANCE.globalEventChannel().registerListenerHost(it.getInstance()!!.get()!! as ListenerHost)
                }
            }
        }
    }
}