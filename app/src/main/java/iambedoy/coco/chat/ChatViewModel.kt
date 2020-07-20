package iambedoy.coco.chat

import android.webkit.URLUtil
import androidx.lifecycle.*
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.chat.items.ChatInMessageItem
import iambedoy.coco.chat.items.ChatOutMessageItem
import iambedoy.coco.models.chat.Message
import iambedoy.coco.models.chat.MetadataMessage
import iambedoy.coco.providers.BackgroundProvider
import iambedoy.coco.pubnub.PubNubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatViewModel (
    private val repository: ChatRepository,
    private val pubNubRepository: PubNubRepository,
    private val backgroundProvider: BackgroundProvider
) : ViewModel(){

    private var _userId = RandomMetadataUtil.randomUsers.shuffled()[0]

    private var _currentMessages = mutableListOf<Item>()

    private var _channelId : String = ""
        set(value) {
            field = value

            pubNubRepository.loadHistoryFromChannel(field){
                _history.postValue(it.map { message ->
                    prepareMessage(message)
                })
            }
        }

    private fun prepareMessage(message: Message.ChatMessage): Item {
        val item = if (message.user.uuid == _userId.uuid) {
            ChatOutMessageItem(message)
        } else {
            ChatInMessageItem(
                message = message,
                hideNickname = handleHideNickname(message))
        }
        handleHideAvatar(message)
        _currentMessages.add(item)
        return item
    }

    private fun handleHideNickname(message: Message.ChatMessage): Boolean {
        val lastOrNull = _currentMessages.lastOrNull() ?: return false
        return if(lastOrNull::class.java == ChatInMessageItem::class.java){
            (lastOrNull as ChatInMessageItem).message.user.uuid == message.user.uuid
        }else{
            false
        }
    }

    private fun handleHideAvatar(message: Message.ChatMessage): Boolean {
        val lastOrNull = _currentMessages.lastOrNull() ?: return false
        return if(lastOrNull::class.java == ChatInMessageItem::class.java){
            val result = (lastOrNull as ChatInMessageItem).message.user.uuid == message.user.uuid
            if(result){
                lastOrNull.hideAvatar = result
            }
            result
        }else{
            true
        }
    }


    private val _history = MutableLiveData<List<Item>>()
    val history : LiveData<List<Item>>
        get() = _history

    val presences = pubNubRepository.receivedPresences

    val messages = pubNubRepository.receivedMessages.map {
        prepareMessage(it)
    }


    fun subscribeToChannel(channelId: String){
        _channelId = channelId
        pubNubRepository.registerUser(_userId.uuid)
        pubNubRepository.subscribeToChannel(channelId)
    }

    fun sendMessage(messageText: String){
        if (messageText.isNotEmpty() && !messageText.isBlank()){
            viewModelScope.launch(Dispatchers.IO) {

                var metadataMessage = MetadataMessage()

                if(URLUtil.isHttpUrl(messageText) || URLUtil.isHttpsUrl(messageText)){
                    metadataMessage = repository.getLinkMetadata(messageText)
                }

                pubNubRepository.sendMessageToChannel(Message.ChatMessage(
                    user = RandomMetadataUtil.randomUsers.shuffled()[0],
                    text = messageText,
                    uuid = UUID.randomUUID().toString(),
                    metadata = metadataMessage
                ), _channelId)
            }
        }
    }
}