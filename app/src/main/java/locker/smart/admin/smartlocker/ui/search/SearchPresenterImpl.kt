package locker.smart.admin.smartlocker.ui.search

import locker.smart.admin.smartlocker.bd.HelperLocker
import locker.smart.admin.smartlocker.model.LockerModel

class SearchPresenterImpl(var view: SearchContract.View?) : SearchContract.Presenter {

    private val helper = HelperLocker()

    override fun getRandomReservedLock() {
        view?.handleLock(LockerModel("uid" + System.currentTimeMillis(), 1))
    }

    override fun getRandomUnReservedLock() {
        view?.handleLock(LockerModel("uid" + System.currentTimeMillis(), 0))
    }

    override fun reserveLock(lock: LockerModel) {
        helper.addOrUpdateLockers(lock)
        view?.lockReserved()
    }

    override fun onDestroy() {
        view = null
    }
}