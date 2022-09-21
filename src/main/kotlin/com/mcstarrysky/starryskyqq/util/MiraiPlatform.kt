package com.mcstarrysky.starryskyqq.util

import com.mcstarrysky.starryskyqq.StarrySkyQQ
import taboolib.common.util.replaceWithOrder

/**
 * StarrySkyQQ
 * com.mcstarrysky.starryskyqq.util.MiraiPlatform
 *
 * @author xiaomu
 * @since 2022/9/21 10:42 AM
 */
fun info(message: String, vararg args: Any) {
    StarrySkyQQ.logger.info(message.replaceWithOrder(*args))
}

fun warning(message: String, vararg args: Any) {
    StarrySkyQQ.logger.warning(message.replaceWithOrder(*args))
}

fun error(message: String, vararg args: Any) {
    StarrySkyQQ.logger.error(message.replaceWithOrder(*args))
}