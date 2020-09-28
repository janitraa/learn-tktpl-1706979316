package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MotivationViewModelFactory (private val motivationRepository: MotivationRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MotivationViewModel(motivationRepository) as T
    }
}
