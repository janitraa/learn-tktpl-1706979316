package id.ac.ui.cs.mobileprogramming.janitra.tktpl.utilities

import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.DummyDatabase
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationRepository
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationViewModelFactory

object InjectorUtils {

    fun provideQuotesViewModelFactory():  MotivationViewModelFactory {
        val quoteRepository = MotivationRepository.getInstance(DummyDatabase.getInstance().motivationDao)
        return MotivationViewModelFactory(quoteRepository)
    }
}