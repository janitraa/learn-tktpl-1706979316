package id.ac.ui.cs.mobileprogramming.janitra.tktpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.data.Motivation
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationActivity
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationRepository
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation.MotivationViewModel
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MotivationUnitTest {
    lateinit var repository: MotivationRepository

    @SpyK
    lateinit var viewModel: MotivationViewModel

    @Before
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun fetchRepositoryWhenLoad(){
        val mActivity = MotivationActivity()
        viewModel = spyk(MotivationViewModel(repository))

        var liveData: LiveData<List<Motivation>> = MutableLiveData()

        every {
            repository.getMotivation()
        } returns liveData

        every {
            repository.getMotivation().observe(
                mActivity, any()
            )
        } returns Unit

        mActivity.initializeUi(viewModel)

        verify {
            viewModel.getMotivation()
        }
    }
}