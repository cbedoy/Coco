package iambedoy.coco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import iambedoy.coco.chat.ChatFragment
import org.koin.android.ext.android.inject


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatActivity : AppCompatActivity(){
    private val chatFragment : ChatFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        showFragment(chatFragment)
    }

}