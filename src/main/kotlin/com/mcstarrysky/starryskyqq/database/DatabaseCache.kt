package com.mcstarrysky.starryskyqq.database

import com.mcstarrysky.starryskyqq.StarrySkyQQ
import taboolib.common.platform.ProxyPlayer
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.HostSQL
import taboolib.module.database.Table
import java.util.UUID
import kotlin.random.Random

/**
 * @author xiaomu
 * @since 2022/9/4 09:55
 */
class DatabaseCache {

    private val host = HostSQL(StarrySkyQQ.conf.getConfigurationSection("data.mysql")!!)

    private val table = Table("starryskyqq_cache", host) {
        add("code") {
            type(ColumnTypeSQL.INT) {
                options(ColumnOptionSQL.PRIMARY_KEY)
            }
        }
        add("user") {
            type(ColumnTypeSQL.VARCHAR, 255)
        }
        add("name") {
            type(ColumnTypeSQL.TEXT)
        }
    }

    private val dataSource = host.createDataSource(withoutConfig = true)

    init {
        table.workspace(dataSource) { createTable(true) }.run()
    }


    fun delete(code: Int) {
        table.delete(dataSource) { where { "code" eq code } }
    }

    fun has(code: Int): Boolean {
        return table.find(dataSource) { where { "code" eq code } }
    }

    fun get(code: Int): Pair<UUID, String>? {
        return table.select(dataSource) {
            where { "code" eq code }
        }.firstOrNull { Pair(UUID.fromString(getString("user")), getString("name")) }
    }

    companion object {

        val INSTANCE = DatabaseCache()
    }
}