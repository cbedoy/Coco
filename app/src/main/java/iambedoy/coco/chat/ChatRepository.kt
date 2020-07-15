package iambedoy.coco.chat

import androidx.lifecycle.liveData
import com.github.marlonlom.utilities.timeago.TimeAgo
import iambedoy.coco.chat.items.ChatDateItem
import iambedoy.coco.chat.items.ChatEventItem
import iambedoy.coco.chat.items.ChatInMessageItem
import iambedoy.coco.chat.items.ChatOutMessageItem
import iambedoy.coco.insertItemInRandom
import iambedoy.coco.models.chat.Message
import java.util.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatRepository {

    val randomMessages = liveData {
        val messages = RandomMetadataUtil.randomMessages.map {
            if (it.isNotEmpty() && it.length % 2 == 0) {
                ChatOutMessageItem(
                    Message.ChatMessage(
                        text = it,
                        metadata = RandomMetadataUtil.randomMessage()
                    )
                )
            } else {
                ChatInMessageItem(
                    Message.ChatMessage(
                        text = it,
                        metadata = RandomMetadataUtil.randomMessage()
                    )
                )
            }
        }.toMutableList()

        messages.insertItemInRandom(ChatDateItem(TimeAgo.using(System.currentTimeMillis() - 999999)))
        messages.insertItemInRandom(ChatDateItem(TimeAgo.using(System.currentTimeMillis() - (Random().nextInt(99999) - 999999))))

        messages.insertItemInRandom(ChatEventItem("Someone have joined to this room"))
        messages.insertItemInRandom(ChatEventItem("New content have been added to explorer"))
        messages.insertItemInRandom(ChatEventItem("Oops it seem some else get infected by covid19"))

        emit(messages)
    }



}