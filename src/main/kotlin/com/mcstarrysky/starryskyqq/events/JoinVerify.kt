package com.mcstarrysky.starryskyqq.events

import com.mcstarrysky.starryskyqq.database.DatabaseCache
import com.mcstarrysky.starryskyqq.database.DatabasePlayer
import net.mamoe.mirai.event.ListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import taboolib.common.platform.event.SubscribeEvent
import java.text.SimpleDateFormat
import java.util.*

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.events.JoinVerify
 *
 * @author xiaomu
 * @since 2022/9/21 11:45 AM
 */
// @TListener
object JoinVerify : ListenerHost {

    @SubscribeEvent
    suspend fun GroupMessageEvent.e() {
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
}