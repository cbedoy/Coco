package iambedoy.coco.pubnub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.SubscribeCallback
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult
import com.pubnub.api.models.consumer.pubsub.PNSignalResult
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult
import com.pubnub.api.models.consumer.pubsub.objects.PNMembershipResult
import com.pubnub.api.models.consumer.pubsub.objects.PNSpaceResult
import com.pubnub.api.models.consumer.pubsub.objects.PNUserResult
import iambedoy.coco.models.chat.Message
import iambedoy.coco.models.chat.Message.ChatMessage
import iambedoy.coco.models.chat.Presence
import iambedoy.coco.toObject


/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
class PubNubService {

    private val pubNub: PubNub = PubNub(PNConfiguration().apply {
        publishKey = "pub-c-ea2dfa6a-d934-4d27-8d63-2bd5e7e8cd8e"
        subscribeKey = "sub-c-e6ad35c0-9d20-11e8-a2b8-bebe9493d04b"
        uuid = "theClientUUID"
    }).apply {
        addListener(object : SubscribeCallback() {
            override fun signal(pubnub: PubNub, pnSignalResult: PNSignalResult) {

            }

            override fun status(pubnub: PubNub, pnStatus: PNStatus) {
                _receivedPresences.postValue(
                    Presence.StatusPresence(
                        name = pnStatus.category?.name?:""
                    )
                )
            }

            override fun user(pubnub: PubNub, pnUserResult: PNUserResult) {

            }

            override fun messageAction(pubnub: PubNub, pnMessageActionResult: PNMessageActionResult) {

            }

            override fun presence(pubnub: PubNub, event: PNPresenceEventResult) {
                _receivedPresences.postValue(
                    Presence.EventPresence(
                        event = event.event,
                        channel = event.channel,
                        uuid = event.uuid
                    )
                )
            }

            override fun membership(pubnub: PubNub, pnMembershipResult: PNMembershipResult) {

            }

            override fun message(pubnub: PubNub, pnMessageResult: PNMessageResult) {
                _receivedMessages.value = pnMessageResult.message.toObject<ChatMessage>()
            }

            override fun space(pubnub: PubNub, pnSpaceResult: PNSpaceResult) {

            }
        })
    }

    private val _receivedMessages = MutableLiveData<ChatMessage>()
    val receivedMessages : LiveData<ChatMessage>
        get() = _receivedMessages

    private val _receivedPresences = MutableLiveData<Presence>()
    val receivedPresences : LiveData<Presence>
        get() = _receivedPresences

    suspend fun loadHistoryFromChannel(channel: String) : LiveData<List<ChatMessage>> {
        return liveData {
            try {
                pubNub.history().channel(channel).count(10).sync()?.let {
                    emit(it.messages.map { historyResult ->
                        historyResult.entry.toObject<ChatMessage>()
                    })
                }
            }catch (e: Exception){
                emit(emptyList())
            }
        }
    }

    suspend fun publishMessageToChannel(message: ChatMessage, channel: String) : LiveData<Message>{
        return liveData{
            try {
                pubNub.publish().channel(channel).message(message).sync()
                emit(message)
            }catch (e: Exception){
                emit(Message.ErrorMessage(e.localizedMessage?:""))
            }
        }

    }
}



