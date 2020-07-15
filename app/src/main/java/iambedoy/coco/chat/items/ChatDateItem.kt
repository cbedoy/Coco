package iambedoy.coco.chat.items

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import kotlinx.android.synthetic.main.view_holder_date_message.*

/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
class ChatDateItem(val text: String) : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.chat_message_time_ago.text = text
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_date_message
    }

}