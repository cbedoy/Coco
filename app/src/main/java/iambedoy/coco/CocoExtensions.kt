package iambedoy.coco

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlin.random.Random

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

inline fun <reified T> JsonElement.toObject(): T {
    return Gson().fromJson<T>(this, T::class.java)
}