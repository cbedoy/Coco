package iambedoy.coco

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import kotlin.random.Random


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
typealias completion = () -> Unit

fun AppCompatActivity.showFragment(targetFragment: Fragment){
    if (!supportFragmentManager.fragments.contains(targetFragment)){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, targetFragment)
            .commit()
    }
}

fun View.goneIfTrueOtherwiseVisible(condition: Boolean){
    visibility = if(condition) View.GONE else View.VISIBLE
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

fun <T> MutableList<T>.randomBasedOfSize() : Int{
    return Random(System.currentTimeMillis()).nextInt(this.size)
}

fun <T> MutableList<T>.insertItemInRandom(item: T){
    this[randomBasedOfSize()] = item
}

fun <T : AppCompatActivity> View.startActivityWhenOnClickListener(clazz: Class<T>) {
    setOnClickListener {
        context.startActivity(
            Intent(context, clazz)
        )
    }
}

fun View.setCollapseExpand(collapsibleView: View, onCollapse: completion = {}, onExpand: completion = {}) {
    setOnClickListener {
        when (collapsibleView.visibility) {
            View.GONE -> {
                collapsibleView.visibility = View.VISIBLE
                onExpand()
            }
            View.VISIBLE -> {
                collapsibleView.visibility = View.GONE
                onCollapse()
            }
        }
    }
}

fun CardView.setCardBackgroundColor2(color: Int ){
    context?.let { context ->
        setCardBackgroundColor(context.getColor(color))
    }
}


inline fun <reified T> fromJson(json: String): T {
    return Gson().fromJson(json, object: TypeToken<T>(){}.type)
}

inline fun <reified T> fromJson(json: JsonElement): T {
    return Gson().fromJson(json, object: TypeToken<T>(){}.type)
}