package iambedoy.coco.chat.items.drawer

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
class DrawerDividerItem : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.view_holder_drawer_divider
    }

}
