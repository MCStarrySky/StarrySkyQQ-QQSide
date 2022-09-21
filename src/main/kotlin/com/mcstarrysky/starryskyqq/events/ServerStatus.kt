package com.mcstarrysky.starryskyqq.events

import com.mcstarrysky.starryskyqq.annotation.TListener
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.ListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.events.ServerStatus
 *
 * @author xiaomu
 * @since 2022/9/21 10:53 AM
 */
@TListener
object ServerStatus : ListenerHost {

    @EventHandler
    suspend fun GroupMessageEvent.onMessage() {
        if (group.id == 818114237L) {
            if (message.content == "服务器状态") {
                group.sendMessage("我不知道")
            }
        }
    }
}