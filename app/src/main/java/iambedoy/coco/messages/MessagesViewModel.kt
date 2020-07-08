package iambedoy.coco.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iambedoy.coco.models.RandomUserResultResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
class MessagesViewModel(
    private val repository: MessagesRepository
) : ViewModel(){

    private val serviceScope = CoroutineScope(Job() + Dispatchers.IO)

    private val _userListState = MutableLiveData<List<RandomUserResultResponse>>()

    val userListState : LiveData<List<RandomUserResultResponse>>
        get() = _userListState

    fun loadUserList(){
        serviceScope.launch {
            _userListState.postValue(repository.requestUserList())
        }
    }
}