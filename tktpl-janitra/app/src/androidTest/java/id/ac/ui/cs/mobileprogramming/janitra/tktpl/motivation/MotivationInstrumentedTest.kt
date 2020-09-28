package id.ac.ui.cs.mobileprogramming.janitra.tktpl.motivation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import id.ac.ui.cs.mobileprogramming.janitra.tktpl.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MotivationInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MotivationActivity::class.java)

    @Test
    fun motivationInstrumentedTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.name),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Jangan mengeluh"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.motivation),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("Mengeluh hanya membuat hidup semakin tertekan"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.button_add_letter), withText("Motivate"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.motivation),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(pressImeActionButton())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    @Test
    fun testEditTextName() {
        onView(withId(R.id.name)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.name)).check(ViewAssertions.matches(Matchers.notNullValue()))
        onView(withId(R.id.name)).check(ViewAssertions.matches(withHint("Title")))
    }

    @Test
    fun testEditTextMotivation() {
        onView(withId(R.id.motivation)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.motivation)).check(ViewAssertions.matches(Matchers.notNullValue()))
        onView(withId(R.id.motivation)).check(ViewAssertions.matches(withHint("Your Motivation")))
    }

    @Test
    fun testButton() {
        onView(withId(R.id.button_add_letter)).check(ViewAssertions.matches(Matchers.notNullValue()))
        onView(withId(R.id.button_add_letter)).check(ViewAssertions.matches(withText("MOTIVATE")))
    }

    @Test
    fun showQuotesAfterMotivate(){
        onView(withId(R.id.name)).perform(ViewActions.typeText("Jangan mengeluh"))
        onView(withId(R.id.motivation)).perform(ViewActions.typeText("Mengeluh hanya membuat hidup semakin tertekan"))
        onView(withId(R.id.button_add_letter)).perform(click())

        onView(withId(R.id.result)).check(matches(
            withText(containsString("Jangan mengeluh - Mengeluh hanya membuat hidup semakin tertekan"))
        ))

        onView(withId(R.id.name)).check(matches(
            withText(containsString(""))
        ))
        onView(withId(R.id.motivation)).check(matches(
            withText(containsString(""))
        ))
    }
}
