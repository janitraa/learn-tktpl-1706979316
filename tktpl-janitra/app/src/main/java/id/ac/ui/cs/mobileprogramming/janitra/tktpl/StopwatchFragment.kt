package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
//import com.dicoding.stopwatch.databinding.FragmentStopwatchBinding
import kotlinx.android.synthetic.main.fragment_stopwatch.*

class StopwatchFragment: Fragment(), View.OnClickListener {

    private lateinit var chronometer: Chronometer
    private var startStopButton: Button? = null
    private var resetButton: Button? = null
    private lateinit var mView: View
    var running: Boolean = false
    var pause: Long = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        val binding: FragmentStopwatchBinding  = FragmentStopwatchBinding.inflate(inflater, container, false)
//        this.mView = binding.getRoot()

        val rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false)

        chronometer = rootView.findViewById(R.id.chronometer)
        startStopButton = rootView.findViewById(R.id.start_stop_button)
        resetButton = rootView.findViewById(R.id.reset_button)

        startStopButton!!.setOnClickListener(this)
        resetButton!!.setOnClickListener(this)

        return rootView
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_stop_button -> {
                var button: Button = v.findViewById(R.id.start_stop_button)
                if (!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pause)
                    chronometer.start()
                    running = true
                    button.setText("stop")
                } else {
                    chronometer.stop()
                    pause = SystemClock.elapsedRealtime() - chronometer.getBase()
                    running = false
                    button.setText("start")
                }
            }
            R.id.reset_button -> {
                chronometer.setBase(SystemClock.elapsedRealtime())
                pause = 0
            }
        }
    }

    fun changeTheme(clicked: Boolean) {
        val layout: LinearLayout = mView.findViewById(R.id.fragment_stopwatch)
        layout.setBackgroundColor(Color.parseColor("#FFFFFF"))

        if (clicked) {
            layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        } else {
            layout.setBackgroundColor(Color.parseColor("#FFC71B"))
        }
    }

    companion object {
        fun newInstance(): StopwatchFragment {
            return StopwatchFragment()
        }
    }
}