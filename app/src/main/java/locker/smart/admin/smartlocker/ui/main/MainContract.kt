package locker.smart.admin.smartlocker.ui.main

import android.content.ContentResolver
import locker.smart.admin.smartlocker.model.LockerModel
import locker.smart.admin.smartlocker.model.UserModel
import locker.smart.admin.smartlocker.ui.base.BasePresenter

interface MainContract {

    interface View {

        fun updateUserModel(model: UserModel)

        fun updateAllLockers(lockers: ArrayList<LockerModel>)

        fun setRefreshing(boolean: Boolean)

        fun showError(text: String)
    }

    interface Presenter : BasePresenter {

        fun getOrCreateUser(resolver: ContentResolver)

        fun getAllUserLockersRest()

        fun getAllUserLockersBD()

        fun unReserveAllUserLockers()
    }
}