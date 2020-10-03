//package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProviders
//import id.ac.ui.cs.mobileprogramming.janitra.tktpl.R
//import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Motivation
//import id.ac.ui.cs.mobileprogramming.janitra.tktpl.utilities.InjectorUtils
//import kotlinx.android.synthetic.main.activity_motivation.*
//
//class MotivationActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_motivation)
//        val factory = InjectorUtils.provideQuotesViewModelFactory()
//
//        val viewModel = ViewModelProviders.of(this, factory)
//            .get(MotivationViewModel::class.java)
//
//        initializeUi(viewModel)
//        setOnClickButtonAddMotivation(viewModel)
//    }
//
//    fun initializeUi(viewModel: MotivationViewModel) {
//        viewModel.getMotivation().observe(this, androidx.lifecycle.Observer { motivations ->
//            val stringBuilder = StringBuilder()
//            motivations.forEach { motivation ->
//                stringBuilder.append("$motivation\n\n")
//            }
//            result.text = stringBuilder.toString()
//        })
//    }
//
//    fun setOnClickButtonAddMotivation(viewModel: MotivationViewModel){
//        button_add_letter.setOnClickListener {
//            val letter = Motivation(motivation.text.toString(), name.text.toString())
//            viewModel.addMotivation(letter)
//            motivation.setText("")
//            name.setText("")
//        }
//    }
//}