package locker.smart.admin.smartlocker

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

//        val accountId: String? = SPUtil.getUserUUID()
//        if (accountId != null && accountId.isEmpty()) {
//            SPUtil.saveUserUUID(UUIDUtil.getRandomUUID())
//        }
    }

    companion object {
        lateinit var instance: App private set
    }
}