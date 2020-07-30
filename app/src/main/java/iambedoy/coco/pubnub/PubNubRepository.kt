package iambedoy.coco.pubnub

import iambedoy.coco.chat.RandomMetadataUtil
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

    fun loadExplorer(channels: List<String>, completion: historyCompletion){
        service.loadExplorerFromChannels(channels, completion)
    }

    fun subscribeToChannel(channelId: String) = service.subscribeToChannel(channelId)

    fun sendMessageToChannel(chatMessage: Message.ChatMessage, channelId: String) = service.publishMessageToChannel(chatMessage, channelId)
    fun registerUser(userId: String) = service.registerUser(userId)

    val receivedPresences = service.receivedPresences
    val receivedMessages = service.receivedMessages
}