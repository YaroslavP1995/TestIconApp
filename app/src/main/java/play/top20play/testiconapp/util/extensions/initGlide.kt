package play.top20play.testiconapp.util.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlin.math.roundToInt

fun initGlide(
    context: Context,
    photo: String?,
    iconSize: Float,
    @DrawableRes placeHolder: Int,
    fillImageView: (drawable: Drawable?) -> Unit
) {
    Glide
        .with(context)
        .load(photo)
        .error(getScaledIconPlaceHolder(context, iconSize, placeHolder))
        .centerCrop()
        .override(getIconSize(context, iconSize), getIconSize(context, iconSize))
        .into(object : CustomTarget<Drawable>() {

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) = fillImageView(resource)

            override fun onLoadCleared(placeholder: Drawable?) {
                // NOOP
            }

            override fun onLoadStarted(placeholder: Drawable?) {
                // NOOP
            }

            override fun onLoadFailed(errorDrawable: Drawable?) =
                fillImageView(errorDrawable)
        })
}

private fun getScaledIconPlaceHolder(
    context: Context,
    iconSize: Float,
    @DrawableRes placeHolder: Int
): Drawable? {
    val drawable = ContextCompat.getDrawable(context, placeHolder)
    val size = getIconSize(context, iconSize)
    return drawable?.resize(context.resources, size)
}

private fun getIconSize(context: Context, iconSize: Float): Int =
    convertDpToPixel(iconSize, context).roundToInt()

fun Drawable.resize(
    resources: Resources,
    @Px width: Int = intrinsicWidth,
    @Px height: Int = intrinsicHeight
): Drawable = this.toBitmap(width, height).toDrawable(resources)

fun Drawable.resize(resources: Resources, @Px size: Int): Drawable =
    this.resize(resources, size, size)

fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}