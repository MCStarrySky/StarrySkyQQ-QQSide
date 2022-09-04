package com.mcstarrysky.starryskyqq

import com.mcstarrysky.starryskyqq.database.DatabaseCache
import com.mcstarrysky.starryskyqq.database.DatabasePlayer
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.content
import taboolib.module.configuration.Configuration
import java.io.File
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.Date

object StarrySkyQQ : KotlinPlugin(
    JvmPluginDescription("com.mcstarrysky.starryskyqq", "1.0.0") {
        name("StarrySkyQQ-QQSide")
    }
) {

    lateinit var conf: Configuration

    private val file: File by lazy {
        val file = File(configFolder, "config.yml")
        if (!file.exists()) {
            file.createNewFile()
            val content = getResource("config.yml")
            if (content != null) {
                val config = Configuration.loadFromString(content)
                config.saveToFile(file)
            }
        }
        file
    }

    override fun onEnable() {
        conf = Configuration.loadFromFile(file)
        if (conf.getBoolean("first")) {
            logger.warning("检测到第一次运行，请前往配置文件修改数据库配置信息！")
            return
        }
        globalEventChannel().subscribeAlways<GroupMessageEvent> {
            if (group.id == 818114237L) {
                val message = message.content
                try {
                    if (!DatabasePlayer.INSTANCE.has(sender.id) && DatabaseCache.INSTANCE.has(message.toInt())) {
                        val user = DatabaseCache.INSTANCE.get(message.toInt())!!
                        val time = System.currentTimeMillis()
                        DatabasePlayer.INSTANCE.insert(user, sender.id, time)
                        DatabaseCache.INSTANCE.delete(message.toInt())
                        group.sendMessage(
                            "验证成功!\n" +
                                    "玩家UUID: ${user.first}\n" +
                                    "玩家名称: ${user.second}\n" +
                                    "验证时间: ${SimpleDateFormat().format(Date(time))}\n" +
                                    "您现在可以进入服务器了!"
                        )
                        group[sender.id]!!.nameCard = user.second
                    }
                } catch (ignored: NumberFormatException) {
                }
            }
        }
        logger.info("Running StarrySkyQQ(QQ Side) Version 1.0.0")
    }
}