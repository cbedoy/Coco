package iambedoy.coco.chat.items.drawer

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import kotlinx.android.synthetic.main.view_holder_drawer_action.*
import kotlinx.android.synthetic.main.view_holder_drawer_action.view.*

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
class DrawerActionItem (
    private val imageResource: Int,
    private val title: String
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.drawer_action_title.text = title
        viewHolder.drawer_action_icon.setImageResource(imageResource)
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_drawer_action
    }

}