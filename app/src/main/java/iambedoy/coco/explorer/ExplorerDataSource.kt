package iambedoy.coco.explorer

import iambedoy.coco.common.CommonTitleItem
import zlc.season.yasha.YashaDataSource
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ExplorerDataSource(
    private val title: String = ""
) : YashaDataSource(){
    override fun loadInitial(loadCallback: LoadCallback<YashaItem>) {
        val list = mutableListOf<YashaItem>()

        list.add(CommonTitleItem(text = title))



        loadCallback.setResult(
            list
        )
    }
}