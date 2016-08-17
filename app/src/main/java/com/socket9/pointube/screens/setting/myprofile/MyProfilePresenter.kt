package com.socket9.pointube.screens.setting.myprofile

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.setting.SettingModel
import com.socket9.pointube.utils.SharedPrefUtil
import com.socket9.pointube.utils.ValidatorUtil
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import rx.Observable
import rx.Subscription
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ripzery on 8/2/16.
 */
class MyProfilePresenter(var view: MyProfileContract.View?) : MyProfileContract.Presenter, AnkoLogger {
    private val mLoginResult: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }
    private var mValidateAllSubscription: Subscription? = null
    private var mUpdateProfileRequest: SettingModel.Request.UpdateProfile? = null
    private var mIsThai: Boolean = true
    private var mIsMale: Boolean = true
    private var mDob: Date? = null

    override fun loadCurrentUser() {
        view?.initLoginData(mLoginResult!!)
    }

    override fun validateAll(emailObs: Observable<CharSequence>, fnEnObs: Observable<CharSequence>, lnEnObs: Observable<CharSequence>, fnThObs: Observable<CharSequence>, lnThObs: Observable<CharSequence>, citiObs: Observable<CharSequence>, ppObs: Observable<CharSequence>, dobObs: Observable<CharSequence>) {
        /* Check all combination */
        val listObservable: MutableList<Observable<CharSequence>> = mutableListOf(emailObs, fnEnObs, lnEnObs, fnThObs, lnThObs, citiObs, ppObs, dobObs)

        mValidateAllSubscription = Observable.combineLatest(listObservable, {
            val list: MutableList<CharSequence> = mutableListOf()
            it.forEach {
                list.add(it as CharSequence)
            }

            mIsThai = mLoginResult!!.citizenID != null && mLoginResult!!.citizenID!!.length > 0

            /* build register model on-the-fly */
            val mIsCitizenIdValid = ValidatorUtil.provideCitizenIdValidator().isValid(list[5], list[5].isEmpty())
            val mIsPassportValid = ValidatorUtil.providePassportValidator().isValid(list[6], list[6].isEmpty())
            val isNationalityRequiredValid = if (mIsThai) mIsCitizenIdValid else mIsPassportValid

            val isValid = ValidatorUtil.provideEmailValidator().isValid(list[0], list[0].isEmpty()) &&
                    ValidatorUtil.provideFirstNameEnValidator().isValid(list[1], list[1].isEmpty()) &&
                    ValidatorUtil.provideLastNameEnValidator().isValid(list[2], list[2].isEmpty()) &&
                    ValidatorUtil.provideFirstNameThValidator().isValid(list[3], list[3].isEmpty()) &&
                    ValidatorUtil.provideLastNameThValidator().isValid(list[4], list[4].isEmpty()) &&
                    !list[7].isEmpty() &&
                    isNationalityRequiredValid

            if (isValid) {
                buildProfileModel(list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7])
            }

            isValid
        }).subscribe {
            if (it) view?.enableSave() else view?.disableSave()
        }
    }

    private fun buildProfileModel(email: CharSequence, firstNameEn: CharSequence, lastNameEn: CharSequence, firstNameTh: CharSequence, lastNameTh: CharSequence, citizen: CharSequence, passport: CharSequence, dob: CharSequence) {
        val date = Calendar.getInstance()
        val split = dob.split("/")
        date.set(split[2].toInt(), split[1].toInt(), split[0].toInt())
        val dateOfBirth = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date.time)
        info { dateOfBirth }
        mUpdateProfileRequest = SettingModel.Request.UpdateProfile(
                mLoginResult.id,
                email.toString(),
                firstNameTh.toString(),
                lastNameTh.toString(),
                firstNameEn.toString(),
                lastNameEn.toString(),
                if (mIsThai) citizen.toString() else null,
                if (mIsThai) null else passport.toString(),
                dateOfBirth.toString(),
                mLoginResult.code.toString(),
                if (mIsMale) "1" else "2"
        )
    }

    override fun setGender(isMale: Boolean) {
        info { isMale }
        mIsMale = isMale
    }

    override fun setDob(dob: Date) {
        mDob = dob
    }

    override fun next() {

    }

    override fun save() {
        view?.showLoading()
        /* update gender */
        mUpdateProfileRequest!!.Gender = if (mIsMale) "1" else "2"
        DataManager.updateProfile(mUpdateProfileRequest!!)
                .subscribe({
                    view?.hideLoading()
                    if (it.IsSuccess) {
                        view?.showUpdateSuccess()
                        SharedPrefUtil.saveLoginResult(buildLoginResult()!!)
                    } else {
                        view?.showUpdateError(it.Message)
                    }
                }, {
                    view?.hideLoading()
                    view?.showUpdateError("Internet connection problem")
                })

    }

    private fun buildLoginResult(): LoginModel.Response.LoginResult? {
        var model: LoginModel.Response.LoginResult? = null

        with(mUpdateProfileRequest!!) {
            model = LoginModel.Response.LoginResult(true,
                    "",
                    mLoginResult.id,
                    mLoginResult.code,
                    FirstName,
                    LastName,
                    FirstNameEN,
                    LastNameEN,
                    CitizenID,
                    Passport,
                    mLoginResult.mobile,
                    Email,
                    Gender,
                    mLoginResult.address,
                    mDob ?: mLoginResult.birthday,
                    mLoginResult.token)
        }

        return model
    }


    override fun clickChangePassword() {
        view?.showChangePassword()

    }

    override fun changePassword(oldPassword: String, newPassword: String, ConfirmPassword: String) {
        val id = SharedPrefUtil.loadLoginResult()!!.id
        val token = SharedPrefUtil.loadLoginResult()!!.token
        val changePwModel: LoginModel.Request.ChangePassword = LoginModel.Request.ChangePassword("$id", token!!, oldPassword, newPassword, ConfirmPassword)

        view?.showLoading()
        DataManager.changePassword(changePwModel)
                .subscribe({
                    if (it.IsSuccess) {
                        view?.showChangePasswordSuccess()

                    } else {
                        view?.showChangePasswordError(it.Message!!)

                    }
                }, {
                    warn { it }
                    view?.showChangePasswordError(it.message!!)
                })
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        mValidateAllSubscription?.unsubscribe()
        view = null
    }

}