package com.mcstarrysky.starryskyqq.platform

import com.mcstarrysky.starryskyqq.annotation.TListener
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.platform.Plugin
 *
 * @author xiaomu
 * @since 2022/9/21 10:56 AM
 */
abstract class Plugin(description: JvmPluginDescription) : KotlinPlugin(description) {

    override fun onEnable() {
        INSTANCE = this
        TListener.inject()
        load()
    }

    open fun load() {
    }

    companion object {

        lateinit var INSTANCE: KotlinPlugin
    }
}