package locker.smart.admin.smartlocker.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import locker.smart.admin.smartlocker.R
import locker.smart.admin.smartlocker.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        checkAndStartActivity()
    }

    private fun checkAndStartActivity() {
        startActivity<MainActivity>()
        finish()
    }
}