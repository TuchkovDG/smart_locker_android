package locker.smart.admin.smartlocker

import android.app.Application
import locker.smart.admin.smartlocker.util.SPUtil
import locker.smart.admin.smartlocker.util.UUIDUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        val accountId: String? = SPUtil.getUserUUID()
        if (accountId != null && accountId.isEmpty()) {
            SPUtil.saveUserUUID(UUIDUtil.getRandomUUID())
        }
    }

    companion object {
        lateinit var instance: App private set
    }
}