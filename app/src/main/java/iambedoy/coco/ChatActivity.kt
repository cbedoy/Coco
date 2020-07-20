package iambedoy.coco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import iambedoy.coco.chat.ChatFragment
import iambedoy.coco.chat.RandomMetadataUtil
import iambedoy.coco.chat.items.drawer.DrawerActionItem
import iambedoy.coco.chat.items.drawer.DrawerDividerItem
import iambedoy.coco.chat.items.drawer.DrawerHeaderItem
import iambedoy.coco.chat.items.drawer.DrawerMemberItem
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatActivity : AppCompatActivity(){
    private val chatFragment : ChatFragment by inject()

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        recycler_drawer_list.layoutManager = LinearLayoutManager(this)
        recycler_drawer_list.adapter = adapter

        RandomMetadataUtil.randomUsers.first().let {
            adapter.add(DrawerHeaderItem(it))
        }

        RandomMetadataUtil.randomUsers.map {
            adapter.add(DrawerMemberItem(it))
        }
        adapter.add(DrawerDividerItem())
        adapter.add(DrawerActionItem(R.drawable.action_settings, "Settings"))
        adapter.add(DrawerActionItem(R.drawable.action_saved, "Saved"))
        adapter.add(DrawerActionItem(R.drawable.action_info, "About"))

        showFragment(chatFragment)
    }

}