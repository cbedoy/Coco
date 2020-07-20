package iambedoy.coco.chat.items.drawer

import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.R
import iambedoy.coco.models.chat.User
import kotlinx.android.synthetic.main.view_holder_drawer_header.*
import kotlinx.android.synthetic.main.view_holder_drawer_user.*

/**
 * Coco
 *
 * Created by bedoy on 19/07/20.
 */
class DrawerMemberItem (
    private val user: User
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.drawer_user_avatar.load(user.avatar){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        viewHolder.drawer_user_nickname.text = user.nickname
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_drawer_user
    }

}