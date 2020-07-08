package iambedoy.coco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import iambedoy.coco.messages.MessagesFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val messagesFragment : MessagesFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_messages -> {
                    showFragment(messagesFragment)
                }
                R.id.action_explorer -> {

                }
                R.id.action_settings -> {

                }
            }
            true
        }

        showFragment(messagesFragment)
    }

    private fun showFragment(targetFragment: Fragment){
        if (!supportFragmentManager.fragments.contains(messagesFragment)){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, targetFragment)
                .commit()
        }
    }
}
