package iambedoy.coco.models.explorer

import iambedoy.coco.models.chat.Message
import iambedoy.coco.models.channel.Channel

/**
 * Coco
 *
 * Created by bedoy on 20/07/20.
 */
data class Feed (
    val message: Message.ChatMessage,
    val channel: Channel
)