import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


private const val PACKAGE = "com.example.project"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"


@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 30)
class ChangeTextBehaviorTest2 {

    private lateinit var device: UiDevice

    private fun click_btnEdit() {
        val btnEditProfile = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnEditProfile"))
        btnEditProfile.click()
    }

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            PACKAGE
        ).apply {
            // Clear out any previous instances
            this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )

    }

    @Test
    fun test_btnHike() {
        val btn = device.findObject(UiSelector().textContains("Hike"))
        if (btn.exists()) {
            btn.click()
            device.wait(
                Until.hasObject(By.pkg(PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
        device.pressBack()
    }

    @Test
    fun test_btnWeather() {
        val btn = device.findObject(UiSelector().textContains("Weather"))
        if (btn.exists()) {
            btn.click()
            device.wait(
                Until.hasObject(By.pkg(PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
        device.pressBack()
    }

    @Test
    fun test_etFirstName() {
        val name = "First"
        //find the edit text by its resource id etFirstName
        val btnEditProfile = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnEditProfile"))
        btnEditProfile.click()

        val etFirstName = device.findObject(UiSelector().resourceId("$PACKAGE:id/etFirstName"))
        etFirstName.click()
        etFirstName.setText(name)
        device.pressBack()
        //scroll to the bottom of the screen
        device.swipe(0, 1000, 0, 0, 10)
        val btnSave = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnSave"))
        btnSave.click()
    }


    @Test
    fun test_etLastName() {
        val name = "Last"
        //find the edit text by its resource id etFirstName
        val btnEditProfile = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnEditProfile"))
        btnEditProfile.click()

        val etLastName = device.findObject(UiSelector().resourceId("$PACKAGE:id/etLastName"))
        etLastName.click()
        etLastName.setText(name)
        device.pressBack()
        //scroll to the bottom of the screen
        device.swipe(0, 1000, 0, 0, 10)
        val btnSave = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnSave"))
        btnSave.click()
    }

    @Test
    fun test_btnSave() {
        val btnEditProfile = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnEditProfile"))
        btnEditProfile.click()

        //scroll to the bottom of the screen
        device.swipe(0, 1000, 0, 0, 10)

        val btnSave = device.findObject(UiSelector().resourceId("$PACKAGE:id/btnSave"))
        btnSave.click()
    }

    @Test
    fun test_btnEditProfile() {
        val btnEdit: UiObject = device.findObject(
            UiSelector().text("btnEditProfile").className("android.widget.Button")
        )
        if (btnEdit.exists() && btnEdit.isEnabled) {
            btnEdit.click()
            device.wait(
                Until.hasObject(By.pkg(PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }

    @Test
    fun test_spActivityLevel() {
        val spActivityLevel: UiObject = device.findObject(
            UiSelector().text("spActivityLevel").className("android.widget.Spinner")
        )
        if (spActivityLevel.exists() && spActivityLevel.isEnabled) {
            spActivityLevel.click()
            device.wait(
                Until.hasObject(By.pkg(PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }

    @Test
    fun test_spActivityLevel_sedentary() {
        val x = "Sedentary"
        val spinner = device.findObject(UiSelector().className("android.widget.Spinner"))
        //from the spinner, select the Sedentary option
        spinner.click()
        device.findObject(UiSelector().textContains(x)).click()

        //check that the text field is set to Sedentary
        val spActivityLevel = device.findObject(
            UiSelector().resourceId("$PACKAGE:id/tvActivityLevel")
                .className("android.widget.TextView")
        )
        assertTrue(spActivityLevel.text.contains(x))
    }

    @Test
    fun test_spActivityLevel_mild() {
        val x = "Mild"
        val spinner = device.findObject(UiSelector().className("android.widget.Spinner"))
        //from the spinner, select the Mild option
        spinner.click()
        device.findObject(UiSelector().textContains(x)).click()

        //check that the text field is set to Mild
        val spActivityLevel = device.findObject(
            UiSelector().resourceId("$PACKAGE:id/tvActivityLevel")
                .className("android.widget.TextView")
        )
        assertTrue(spActivityLevel.text.contains(x))
    }

    @Test
    fun test_spActivityLevel_moderate() {
        val x = "Moderate"
        val spinner = device.findObject(UiSelector().className("android.widget.Spinner"))
        //from the spinner, select the Moderate option
        spinner.click()
        device.findObject(UiSelector().textContains(x)).click()

        //check that the text field is set to Moderate
        val spActivityLevel = device.findObject(
            UiSelector().resourceId("$PACKAGE:id/tvActivityLevel")
                .className("android.widget.TextView")
        )
        assertTrue(spActivityLevel.text.contains(x))
    }

    @Test
    fun test_spActivityLevel_heavy() {
        val x = "Heavy"
        val spinner = device.findObject(UiSelector().className("android.widget.Spinner"))
        //from the spinner, select the Active option
        spinner.click()
        device.findObject(UiSelector().textContains(x)).click()

        //check that the text field is set to Active
        val spActivityLevel = device.findObject(
            UiSelector().resourceId("$PACKAGE:id/tvActivityLevel")
                .className("android.widget.TextView")
        )
        assertTrue(spActivityLevel.text.contains(x))
    }

    @Test
    fun test_spActivityLevel_extreme() {
        val x = "Extreme"
        val spinner = device.findObject(UiSelector().className("android.widget.Spinner"))
        //from the spinner, select the Extreme option
        spinner.click()
        device.findObject(UiSelector().textContains(x)).click()

        //check that the text field is set to Extreme
        val spActivityLevel = device.findObject(
            UiSelector().resourceId("$PACKAGE:id/tvActivityLevel")
                .className("android.widget.TextView")
        )
        assertTrue(spActivityLevel.text.contains(x))
    }

    @Test
    fun test_radio(){
        click_btnEdit()
        val radioM = device.findObject(UiSelector().resourceId("$PACKAGE:id/radio_male"))
        val radioF = device.findObject(UiSelector().resourceId("$PACKAGE:id/radio_female"))

        radioF.click()
        radioM.click()
    }

}