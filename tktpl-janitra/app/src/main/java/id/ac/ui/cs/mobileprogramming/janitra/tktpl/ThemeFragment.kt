package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_theme.*

//import com.dicoding.stopwatch.databinding.FragmentToggleBinding

class ThemeFragment: Fragment() {
//    private lateinit var mView: View
    private var theme_button: Button? = null

    override fun onCreateView( inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

//        val binding: FragmentToggleBinding = FragmentToggleBinding.inflate(inflater, container, false)
//        this.mView = binding.getRoot()

        val rootView = inflater.inflate(R.layout.fragment_theme, container, false)

        theme_button = rootView.findViewById(R.id.theme_button)

        theme_button!!.setOnClickListener {
            val fm: FragmentManager? = getActivity()?.getSupportFragmentManager()

            fm?.let {
                val stopwatchFragment: StopwatchFragment = fm.findFragmentById(R.id.fragment_stopwatch) as StopwatchFragment
                stopwatchFragment.changeTheme(clicked = true)
            }
        }
        return rootView
    }

    companion object {
        fun newInstance(): ThemeFragment {
            return ThemeFragment()
        }
    }
}