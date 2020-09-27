package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Motivation

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class MotivationViewModel(private val motivationRepository: MotivationRepository)
    : ViewModel() {

    fun getMotivation() = motivationRepository.getMotivation()

    fun addMotivation(motivation: Motivation) = motivationRepository.addMotivation(motivation)
}