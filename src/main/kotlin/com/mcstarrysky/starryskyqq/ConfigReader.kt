package com.mcstarrysky.starryskyqq

import taboolib.module.configuration.Configuration
import java.io.File

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.ConfigReader
 *
 * @author xiaomu
 * @since 2022/9/21 10:49 AM
 */
object ConfigReader {

    private val file: File by lazy {
        val file = File(StarrySkyQQ.configFolder, "config.yml")
        if (!file.exists()) {
            file.createNewFile()
            val content = StarrySkyQQ.getResource("config.yml")
            if (content != null) {
                val config = Configuration.loadFromString(content)
                config.saveToFile(file)
            }
        }
        file
    }

    lateinit var conf: Configuration

    fun reloadConfig() {
        conf = Configuration.loadFromFile(file)
    }

    val first: Boolean
        get() = conf.getBoolean("first")
}