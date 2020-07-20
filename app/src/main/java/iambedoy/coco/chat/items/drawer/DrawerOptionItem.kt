package iambedoy.coco.chat.items.drawer

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import kotlinx.android.synthetic.main.view_holder_drawer_option.*

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
class DrawerOptionItem (
    private val selected: Boolean,
    private val title: String
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.drawer_option_title.text = title
        viewHolder.drawer_option_selector.isChecked = selected
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_drawer_option
    }

}