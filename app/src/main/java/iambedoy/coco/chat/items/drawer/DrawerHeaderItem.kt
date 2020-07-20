package iambedoy.coco.chat.items.drawer

import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import iambedoy.coco.models.chat.User
import kotlinx.android.synthetic.main.view_holder_drawer_action.*
import kotlinx.android.synthetic.main.view_holder_drawer_header.*

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
class DrawerHeaderItem (
    private val user: User
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.drawer_header_avatar.load(user.avatar){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        viewHolder.drawer_header_nickname.text = user.nickname
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_drawer_header
    }

}