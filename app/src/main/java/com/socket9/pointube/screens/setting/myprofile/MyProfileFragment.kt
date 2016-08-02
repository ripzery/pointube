import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding.widget.RxTextView
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.screens.home.LoginModel
import com.socket9.pointube.screens.register.ToggleViewGroup
import com.socket9.pointube.screens.setting.myprofile.MyProfileContract
import com.socket9.pointube.screens.setting.myprofile.MyProfilePresenter
import com.socket9.pointube.utils.SharedPrefUtil
import com.socket9.pointube.utils.ValidatorUtil
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_setting_my_profile.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ripzery on 7/20/16.
 */

class MyProfileFragment : Fragment(), AnkoLogger, MyProfileContract.View, DatePickerDialog.OnDateSetListener {

    /** Variable zone **/
    lateinit var param1: String
    lateinit var mMyProfilePresenter: MyProfileContract.Presenter
    private val mLoginResult: LoginModel.Response.LoginResult by lazy { SharedPrefUtil.loadLoginResult()!! }

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): MyProfileFragment {
            var bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val myProfile: MyProfileFragment = MyProfileFragment()
            myProfile.arguments = bundle
            return myProfile
        }

    }

    /* Override View Interface zone */
    override fun enableSave() {
        btnSave.isEnabled = true
    }

    override fun disableSave() {
        btnSave.isEnabled = false
    }

    override fun showChangePassword() {
        toast("Show change password")
    }

    override fun showUpdateError(msg: String) {
        toast(msg)
    }

    override fun showUpdateSuccess() {
        toast("Update successful")
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Updating profile...")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun initLoginData(loginData: LoginModel.Response.LoginResult) {
        with(loginData) {
            metEmail.setText(email)
            metFirstName.setText(firstNameEN)
            metFirstNameTH.setText(firstName)
            metLastName.setText(lastNameEN)
            metLastNameTH.setText(lastName)
            metCitizenID.setText(citizenID)
            metPassport.setText(passport)
            toggleGender.setEnable(if (loginData.gender.equals("1")) ToggleViewGroup.LEFT else ToggleViewGroup.RIGHT)
            tvDob.text = SimpleDateFormat("dd/MM/yyyy").format(birthday!!)
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        tvDob.text = "$dayOfMonth/$monthOfYear/$year"
        val dob = Calendar.getInstance()
        dob.set(year, monthOfYear, dayOfMonth)
        info { dob.time }
        mMyProfilePresenter.setDob(dob.time)
    }


    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            param1 = arguments.getString(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_setting_my_profile, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMyProfilePresenter = MyProfilePresenter(this)
        mMyProfilePresenter.onCreate()
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMyProfilePresenter.onDestroy()
    }

    /** Method zone **/

    private fun initInstance() {
        mMyProfilePresenter.loadCurrentUser()

        tvDob.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH))

            dpd.show(activity.fragmentManager, "Datepickerdialog")
        }

        initValidator()

        /* Initialize citizen & passport visibility */
        val isShowCitizenID = mLoginResult.citizenID != null && mLoginResult.citizenID!!.length > 0
        metPassport.visibility = if (isShowCitizenID) View.GONE else View.VISIBLE
        metCitizenID.visibility = if (isShowCitizenID) View.VISIBLE else View.GONE

        /* Validate */
        mMyProfilePresenter.validateAll(
                RxTextView.textChanges(metEmail),
                RxTextView.textChanges(metFirstName),
                RxTextView.textChanges(metLastName),
                RxTextView.textChanges(metFirstNameTH),
                RxTextView.textChanges(metLastNameTH),
                RxTextView.textChanges(metCitizenID),
                RxTextView.textChanges(metPassport),
                RxTextView.textChanges(tvDob)
        )

        btnSave.setOnClickListener {
            mMyProfilePresenter.save()
        }

        btnChangePw.setOnClickListener { mMyProfilePresenter.changePassword() }

        toggleGender.setOnToggleListener(object : ToggleViewGroup.OnToggleListener {
            override fun onToggleLeft(isLeft: Boolean) {
                mMyProfilePresenter.setGender(isLeft)
            }
        })
    }

    private fun initValidator() {
        metEmail.addValidator(ValidatorUtil.provideEmailValidator())
        metFirstName.addValidator(ValidatorUtil.provideFirstNameEnValidator())
        metLastName.addValidator(ValidatorUtil.provideLastNameEnValidator())
        metFirstNameTH.addValidator(ValidatorUtil.provideFirstNameThValidator())
        metLastNameTH.addValidator(ValidatorUtil.provideLastNameThValidator())
        metCitizenID.addValidator(ValidatorUtil.provideCitizenIdValidator())
        metPassport.addValidator(ValidatorUtil.providePassportValidator())
    }
}