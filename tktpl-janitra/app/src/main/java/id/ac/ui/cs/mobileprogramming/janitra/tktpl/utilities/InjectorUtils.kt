package id.ac.ui.cs.mobileprogramming.janitra.tktpl.utilities

import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.DummyDatabase
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationRepository
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationViewModelFactory

// Finally a singleton which doesn't need anything passed to the constructor
object InjectorUtils {

    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory():  MotivationViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val quoteRepository = MotivationRepository.getInstance(DummyDatabase.getInstance().motivationDao)
        return MotivationViewModelFactory(quoteRepository)
    }
}