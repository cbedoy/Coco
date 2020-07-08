package iambedoy.coco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import iambedoy.coco.explorer.ExplorerFragment
import iambedoy.coco.messages.MessagesFragment
import iambedoy.coco.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val messagesFragment : MessagesFragment by inject()
    private val explorerFragment: ExplorerFragment by inject()
    private val settingsFragment: SettingsFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_messages -> {
                    showFragment(messagesFragment)
                }
                R.id.action_explorer -> {
                    showFragment(explorerFragment)
                }
                R.id.action_settings -> {
                    showFragment(settingsFragment)
                }
            }
            true
        }

        showFragment(messagesFragment)
    }

    private fun showFragment(targetFragment: Fragment){
        if (!supportFragmentManager.fragments.contains(targetFragment)){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, targetFragment)
                .commit()
        }
    }
}
