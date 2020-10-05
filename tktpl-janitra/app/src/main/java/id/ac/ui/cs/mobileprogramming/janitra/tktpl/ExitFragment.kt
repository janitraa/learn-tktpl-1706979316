package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ExitFragment: Fragment() {
    private var exitButton: Button? = null

    override fun onCreateView( inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_exit, container, false)

        exitButton = rootView.findViewById(R.id.exit_button)

        exitButton!!.setOnClickListener{
            getActivity()!!.finish()
        }
        return rootView
    }
}