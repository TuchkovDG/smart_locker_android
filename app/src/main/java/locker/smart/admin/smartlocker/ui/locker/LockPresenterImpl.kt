package locker.smart.admin.smartlocker.ui.locker

import locker.smart.admin.smartlocker.bd.HelperLocker
import locker.smart.admin.smartlocker.model.LockerModel

class LockPresenterImpl(var view: LockContract.View?) : LockContract.Presenter {

    private val helper = HelperLocker()

    override fun updateLock(locker: LockerModel?) {
        if (locker?.status == 0) {
            locker.status = 1
        } else if (locker?.status == 1) {
            locker.status = 0
        }
    }

    override fun unReserveLock(locker: LockerModel?) {
        helper.deleteLockers(locker?.uid)
        view?.lockerUnReserve()
    }

    override fun onDestroy() {
        view = null
    }
}