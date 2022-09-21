package com.mcstarrysky.starryskyqq

import com.mcstarrysky.starryskyqq.platform.Plugin
import com.mcstarrysky.starryskyqq.util.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription

object StarrySkyQQ : Plugin(
    JvmPluginDescription("com.mcstarrysky.starryskyqq", "1.0.0") {
        name("StarrySkyQQ-QQSide")
    }
) {

    override fun load() {
        ConfigReader.reloadConfig()
        if (ConfigReader.first) {
            logger.warning("检测到第一次运行，请前往配置文件修改数据库配置信息！")
            return
        }
        info("Running StarrySkyQQ(QQ Side) Version 1.0.0")
    }
}