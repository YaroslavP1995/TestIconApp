package play.top20play.testiconapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import play.top20play.testiconapp.R

class FullImageScreen : DialogFragment() {

    private lateinit var ivFullImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.full_image_screen, container)
        ivFullImage = rootView.findViewById(R.id.ivFullImage)
        return rootView
    }

    fun setUrl(url: String) {
        Glide.with(requireContext()).load(url).into(ivFullImage)
    }

    companion object {
        const val TAG = "FullImageScreen"

        fun show(fragmentManager: FragmentManager): FullImageScreen {
            val checkedFragment = fragmentManager.findFragmentByTag(TAG)
            return if (checkedFragment == null) {
                val dialog = FullImageScreen()
                fragmentManager.beginTransaction().apply {
                    add(dialog, TAG)
                    commitNow()
                }
                dialog
            } else {
                checkedFragment as FullImageScreen
            }
        }
    }
}