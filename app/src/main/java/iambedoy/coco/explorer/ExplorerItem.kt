package iambedoy.coco.explorer

import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import iambedoy.coco.models.explorer.Feed
import kotlinx.android.synthetic.main.view_holder_explorer_item.*

/**
 * Coco
 *
 * Created by bedoy on 20/07/20.
 */
class ExplorerItem(private val feed: Feed): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.explorer_item_channel.text = feed.channel.uuid
        viewHolder.explorer_item_message.text = feed.message.text
        viewHolder.explorer_item_title.text = feed.message.metadata.title
        viewHolder.explorer_item_description.text = feed.message.metadata.description
        viewHolder.explorer_item_thumbnail.load(feed.message.metadata.thumbnail){
            crossfade(true)
        }
        viewHolder.explorer_item_avatar.load(feed.channel.avatar){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_explorer_item
    }

}