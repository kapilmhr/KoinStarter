package app.frantic.koinstarter

import android.view.View
import androidx.databinding.BindingAdapter


class BindingAdapters {

    companion object {
        @BindingAdapter("visibleGone")
        @JvmStatic
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

}