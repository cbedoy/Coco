package iambedoy.coco.messages

import iambedoy.coco.common.CommonTitleItem
import iambedoy.coco.models.RandomUserResultResponse
import zlc.season.yasha.YashaDataSource
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class MessagesDataSource(
    private val title: String = "",
    private val source: List<RandomUserResultResponse> = emptyList()
) : YashaDataSource(){
    override fun loadInitial(loadCallback: LoadCallback<YashaItem>) {
        val list = mutableListOf<YashaItem>()

        list.add(CommonTitleItem(text = title))

        source.forEach {
            list.add(MessageItem(user = it))
        }


        loadCallback.setResult(
            list
        )
    }
}