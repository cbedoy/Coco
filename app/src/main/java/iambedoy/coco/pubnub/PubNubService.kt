package iambedoy.coco.pubnub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import iambedoy.coco.fromJson
import iambedoy.coco.models.chat.Message.ChatMessage
import iambedoy.coco.models.chat.Presence
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */

typealias historyCompletion = (messages: List<ChatMessage>) -> Unit

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
                try {
                    val message = fromJson<ChatMessage>(pnMessageResult.message)
                    _receivedMessages.postValue(message)
                }catch (e: java.lang.Exception){
                    e.printStackTrace()
                }
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

    fun loadHistoryFromChannel(channel: String, completion: historyCompletion) {
        pubNub.history().channel(channel).count(100).async { result, status ->
            if(!status.isError){
                GlobalScope.launch{
                    val messages = mutableListOf<ChatMessage>()
                    result?.messages?.forEach { resultMessage ->
                        resultMessage?.entry?.let {
                            messages.add(fromJson(it))
                        }
                    }
                    completion(messages)
                }
            }else{
                completion(emptyList())
            }
        }
    }

    fun loadExplorerFromChannels(channels: List<String>, completion: historyCompletion){
        pubNub.fetchMessages().channels(channels).maximumPerChannel(100).async { result, status ->
            if(!status.isError){
                GlobalScope.launch{
                    val preparedMessages = mutableListOf<ChatMessage>()
                    result?.channels?.forEach{ (_, messages) ->
                        messages.forEach { rawMessage ->
                            try {
                                val chatMessage : ChatMessage = fromJson(rawMessage.message)
                                if (chatMessage.hasMetadata()){
                                    preparedMessages.add(chatMessage)
                                }
                            }catch (e: Exception){
                                e.printStackTrace()
                            }
                        }
                    }
                    completion(preparedMessages)
                }
            }else{
                completion(emptyList())
            }
        }
    }

    fun publishMessageToChannel(message: ChatMessage, channel: String){
        pubNub.publish().channel(channel).message(message).shouldStore(true).async { result, status ->

        }
    }

    fun subscribeToChannel(channelId: String){
        pubNub.subscribe().channels(listOf(channelId)).withPresence().execute()
    }

    fun registerUser(userId: String) {
        pubNub.configuration.uuid = userId
    }
}



