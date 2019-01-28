package locker.smart.admin.smartlocker.ui.search

import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.ui.base.BasePresenter

interface SearchContract {

    interface View {

        fun handleLock(lock: LockerModel)

        fun lockReserved()

        fun showError(text: String)
    }

    interface Presenter : BasePresenter {

        fun getRandomReservedLock()

        fun getRandomUnReservedLock()

        fun reserveLock(lock: LockerModel)
    }
}