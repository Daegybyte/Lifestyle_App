import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith


private const val BASIC_SAMPLE_PACKAGE = "com.example.project"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"

//@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 30)
class ChangeTextBehaviorTest2 {

    private lateinit var device: UiDevice

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
            BASIC_SAMPLE_PACKAGE
        ).apply{
            // Clear out any previous instances
            this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )

    }


    @Test
    fun test_btnHike() {
        val btnHike: UiObject = device.findObject(
            UiSelector().text("btnHike").className("android.widget.Button")
        )
        if (btnHike.exists() && btnHike.isEnabled) {
            btnHike.click()
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }
    @Test
    fun test_btnWeather(){
        val btnWeather: UiObject = device.findObject(
            UiSelector().text("btnWeather").className("android.widget.Button")
        )
        if (btnWeather.exists() && btnWeather.isEnabled) {
            btnWeather.click()
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }

    @Test
    fun test_firstName(){
        val usernameInput: UiObject = device.findObject(
            UiSelector().text("etFirstName").className("android.widget.EditText")
        )
        if (usernameInput.exists() && usernameInput.isEnabled) {
            usernameInput.text = "first"
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }

    @Test
    fun test_lastName(){
        val usernameInput: UiObject = device.findObject(
            UiSelector().text("etLastName").className("android.widget.EditText")
        )
        if (usernameInput.exists() && usernameInput.isEnabled) {
            usernameInput.text = "last"
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }
    @Test
    fun test_save(){
        val btnSave: UiObject = device.findObject(
            UiSelector().text("btnSave").className("android.widget.Button")
        )
        if (btnSave.exists() && btnSave.isEnabled) {
            btnSave.click()
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }
}