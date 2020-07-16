package iambedoy.coco.pubnub

import iambedoy.coco.models.chat.Message

/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
class PubNubRepository (
    private val service: PubNubService
){
    fun loadHistoryFromChannel(channelId: String, completion: historyCompletion) {
        service.loadHistoryFromChannel(channelId, completion)
    }

    fun subscribeToChannel(channelId: String) = service.subscribeToChannel(channelId)

    fun sendMessageToChannel(chatMessage: Message.ChatMessage, channelId: String) = service.publishMessageToChannel(chatMessage, channelId)

    val receivedPresences = service.receivedPresences
    val receivedMessages = service.receivedMessages
}