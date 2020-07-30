package iambedoy.coco.explorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iambedoy.coco.chat.RandomMetadataUtil
import iambedoy.coco.models.explorer.Feed
import iambedoy.coco.pubnub.PubNubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Coco
 *
 * Created by bedoy on 20/07/20.
 */
class ExplorerViewModel (
    private val repository: PubNubRepository
): ViewModel(){
    private val _feeds = MutableLiveData<List<ExplorerItem>>()
    val feeds : LiveData<List<ExplorerItem>>
        get() = _feeds

    private var _channels  = RandomMetadataUtil.randomChannels

    fun loadExplorer() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadExplorer(_channels.map {
                it.uuid
            }){ messages ->
                _feeds.postValue(
                    messages.map {
                        ExplorerItem(
                            Feed(
                                channel = _channels.shuffled()[0],
                                message = it
                            )
                        )
                    }.toList()
                )
            }
        }
    }
}