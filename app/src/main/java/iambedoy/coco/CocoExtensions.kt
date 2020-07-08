package iambedoy.coco

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
fun AppCompatActivity.showFragment(targetFragment: Fragment){
    if (!supportFragmentManager.fragments.contains(targetFragment)){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, targetFragment)
            .commit()
    }
}

fun View.visibleIfTrueOtherwiseGone(condition: Boolean?){
    visibility = if(condition == true) View.VISIBLE else View.GONE
}

fun View.visibleIfTrueOtherwiseInvisible(condition: Boolean?){
    visibility = if(condition == true) View.VISIBLE else View.INVISIBLE
}

fun TextView.reloadTextIfEmptyThenGone(newText: String){
    text = newText
    visibility = if(text.isEmpty()) View.GONE else View.VISIBLE
}