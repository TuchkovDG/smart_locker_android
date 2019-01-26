package locker.smart.admin.smartlocker.ui.main

import android.content.ContentResolver
import android.provider.Settings
import locker.smart.admin.smartlocker.bd.HelperLocker
import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.model.UserModel
import locker.smart.admin.smartlocker.util.SPUtil
import java.util.*

class MainPresenterImpl(var view: MainContract.View?) : MainContract.Presenter {

    private val helper = HelperLocker()
    private var statusLocker: Boolean = true

    override fun getOrCreateUser(resolver: ContentResolver) {
        //TODO rest api
        val userModel = UserModel(Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID))
        SPUtil.saveUserModel(userModel)
        view?.updateUserModel(userModel)
        getAllUserLockersRest()
    }

    override fun getAllUserLockersRest() {
        val lockers: ArrayList<LockerModel> = ArrayList()
        if (statusLocker) {
            lockers.add(LockerModel("uid" + System.currentTimeMillis(), 1))
            statusLocker = false
        } else {
            lockers.add(LockerModel("uid" + System.currentTimeMillis(), 0))
            statusLocker = true
        }
        helper.addOrUpdateLockers(lockers)
        getAllUserLockersBD()
        view?.setRefreshing(false)
    }

    override fun getAllUserLockersBD() {
        view?.updateAllLockers(helper.getAllLockers())
    }

    override fun unReserveAllUserLockers() {
        helper.deleteAllLockers()
        getAllUserLockersBD()
    }

    override fun onDestroy() {
        view = null
    }
}