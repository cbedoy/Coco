package iambedoy.coco.chat

import iambedoy.coco.models.Message
import iambedoy.coco.models.MetadataMessage
import zlc.season.yasha.YashaDataSource
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatDataSource(private val randomMessages: List<String> = emptyList()): YashaDataSource(){
    override fun loadInitial(loadCallback: LoadCallback<YashaItem>) {
        loadCallback.setResult(
            randomMessages.map {
                if (it.isNotEmpty() && it.length % 2 == 0){
                    ChatOutMessageItem(Message(
                        text =  it,
                        metadata = RandomMetadataUtil.randomMessage()
                    ))
                }else{
                    ChatInMessageItem(Message(
                        text =  it,
                        metadata = RandomMetadataUtil.randomMessage()
                    ))
                }
            }
        )
    }
}