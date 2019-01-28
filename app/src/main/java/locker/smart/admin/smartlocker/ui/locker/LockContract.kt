package locker.smart.admin.smartlocker.ui.locker

import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.ui.base.BasePresenter

interface LockContract {

    interface View {

        fun lockerUnReserve()

        fun showError(text: String)
    }

    interface Presenter : BasePresenter {

        fun updateLock(locker: LockerModel?)

        fun unReserveLock(locker: LockerModel?)
    }
}