package iambedoy.coco.chat.items

import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import iambedoy.coco.chat.components.ChatContentView
import iambedoy.coco.models.chat.Message
import iambedoy.coco.setCollapseExpand
import kotlinx.android.synthetic.main.view_holder_in_plain_message.*

/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
class ChatInMessageItem(private val message: Message.ChatMessage) : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.plain_message_text.text = message.text
        viewHolder.chat_content_view.theme = ChatContentView.THEME.Black
        viewHolder.chat_content_view.metadata = message.metadata
        viewHolder.itemView.setCollapseExpand(viewHolder.plain_message_text_time_ago)
        viewHolder.plain_message_avatar.setCollapseExpand(viewHolder.plain_message_nickname)
        viewHolder.plain_message_avatar.load(message.metadata.thumbnail){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_in_plain_message
    }
}