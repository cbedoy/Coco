package iambedoy.coco.messages

import iambedoy.coco.models.RandomUserResultResponse
import zlc.season.yasha.YashaDataSource
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class MessagesDataSource(private val results: List<RandomUserResultResponse> = emptyList()) : YashaDataSource(){
    override fun loadInitial(loadCallback: LoadCallback<YashaItem>) {
        val list = mutableListOf<YashaItem>()


        results.forEach {
            list.add(MessageItem(it))
        }


        loadCallback.setResult(
            list
        )
    }
}