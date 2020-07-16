package iambedoy.coco.chat

import androidx.lifecycle.*
import iambedoy.coco.models.chat.Message
import iambedoy.coco.pubnub.PubNubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatViewModel (
    private val repository: ChatRepository,
    private val pubNubRepository: PubNubRepository
) : ViewModel(){

    private var _channelId : String = ""
        set(value) {
            field = value

            pubNubRepository.loadHistoryFromChannel(field){
                _history.postValue(it)
            }
        }


    private val _history = MutableLiveData<List<Message.ChatMessage>>()
    val history : LiveData<List<Message.ChatMessage>>
        get() = _history

    val presences = pubNubRepository.receivedPresences

    val messages = pubNubRepository.receivedMessages


    fun subscribeToChannel(channelId: String){
        _channelId = channelId
        pubNubRepository.subscribeToChannel(channelId)
    }

    fun sendMessage(messageText: String){
        if (messageText.isNotEmpty() && !messageText.isBlank()){
            viewModelScope.launch {
                _channelId.let {
                    pubNubRepository.sendMessageToChannel(Message.ChatMessage(
                        text = messageText,
                        uuid = UUID.randomUUID().toString(),
                        metadata = RandomMetadataUtil.randomMessage()
                    ), _channelId)
                }
            }
        }
    }
}