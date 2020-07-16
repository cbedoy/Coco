package iambedoy.coco.chat.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import iambedoy.coco.chat.components.ChatContentView
import iambedoy.coco.models.chat.Message
import iambedoy.coco.setCollapseExpand
import kotlinx.android.synthetic.main.view_holder_out_plain_message.*

/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
class ChatOutMessageItem(val message: Message.ChatMessage) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.plain_message_text.text = message.text
        viewHolder.chat_content_view.theme = ChatContentView.THEME.White
        viewHolder.chat_content_view.metadata = message.metadata
        viewHolder.itemView.setCollapseExpand(viewHolder.plain_message_text_time_ago)
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_out_plain_message
    }

}