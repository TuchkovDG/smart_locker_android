package locker.smart.admin.smartlocker.ui.locker

import locker.smart.admin.smartlocker.bd.HelperLocker
import locker.smart.admin.smartlocker.model.LockerModel

class LockPresenterImpl(var view: LockContract.View?) : LockContract.Presenter {

    private val helper = HelperLocker()

    override fun unReserveLocker(locker: LockerModel?) {
        helper.deleteLockers(locker?.uid)
        view?.lockerUnReserve()
    }

    override fun onDestroy() {
        view = null
    }
}