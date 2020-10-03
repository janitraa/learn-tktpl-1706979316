package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    private var activity: StopwatchActivity? = null

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("test") as CoroutineDispatcher

    @ObsoleteCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_question, container, false)

        val imageView: ImageView = rootView.findViewById(R.id.question_image)
        val yesButton: Button = rootView.findViewById(R.id.yes_button)
        val noButton: Button = rootView.findViewById(R.id.no_button)

        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar!!.setDisplayShowHomeEnabled(true)

        Picasso.get().setLoggingEnabled(true)
        yesButton.setOnClickListener {
            triggerANR()
            Picasso.get()
                .load("https://www.pngkey.com/png/full/352-3525258_dont-symbol-clipart-best-casino.png")
                .placeholder(R.drawable.ic_noun_loading)
                .resize(64, 64)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .centerCrop()
                .into(imageView)
        }

        noButton.setOnClickListener {
            triggerANR()
            Picasso.get()
                .load("https://img.pngio.com/free-great-job-png-free-great-jobpng-transparent-images-15006-good-job-png-1400_1163.png")
                .placeholder(R.drawable.ic_noun_loading)
                .resize(64, 64)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .centerCrop()
                .into(imageView)
        }

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            getActivity()!!.finish()
        }

        return super.onOptionsItemSelected(item)
    }

    /* Implementasi GlobalScope untuk Mengatasi Blocking UI */
    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun triggerANR() = GlobalScope.launch(thread) {
        while (true) {
            Thread.sleep(1000000)
            break
        }
    }

    interface OnBackPressed {
        fun onBackPressed()
    }
}