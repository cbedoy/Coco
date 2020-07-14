package iambedoy.coco.chat

import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import iambedoy.coco.insertItemInRandom
import iambedoy.coco.models.Message
import iambedoy.coco.randomBasedOfSize
import zlc.season.yasha.YashaDataSource
import zlc.season.yasha.YashaItem
import java.util.*


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatDataSource(private val randomMessages: List<String> = emptyList()): YashaDataSource(){
    override fun loadInitial(loadCallback: LoadCallback<YashaItem>) {

        val messages = randomMessages.map {
            if (it.isNotEmpty() && it.length % 2 == 0) {
                ChatOutMessageItem(
                    Message(
                        text = it,
                        metadata = RandomMetadataUtil.randomMessage()
                    )
                )
            } else {
                ChatInMessageItem(
                    Message(
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

        loadCallback.setResult(
            messages
        )
    }
}