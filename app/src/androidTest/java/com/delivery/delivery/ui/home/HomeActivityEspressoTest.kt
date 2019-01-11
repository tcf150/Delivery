package com.delivery.delivery.ui.home


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.delivery.delivery.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityEspressoTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun onDeliveryClicked() {
        Thread.sleep(5000)
        onView(withId(R.id.rvDeliveries))
            .perform(RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.ViewHolder>(0, click()))
        onView(withText("Delivery Details")).check(matches(isDisplayed()))

    }

    @Test
    fun onListScroll() {
        Thread.sleep(5000)
        onView(withId(R.id.rvDeliveries))
            .perform(RecyclerViewActions.scrollToPosition<HomeAdapter.ViewHolder>(19))
        Thread.sleep(1000)
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }
}
