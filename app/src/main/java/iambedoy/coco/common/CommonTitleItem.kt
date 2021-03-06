package iambedoy.coco.common

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import kotlinx.android.synthetic.main.view_holder_common_title.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class CommonTitleItem(val text: String) : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.common_title_view.text = text
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_common_title
    }

}