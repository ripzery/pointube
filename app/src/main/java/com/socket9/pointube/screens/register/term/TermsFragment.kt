import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.extensions.setupToolbar
import com.socket9.pointube.screens.register.term.TermsContract
import com.socket9.pointube.screens.register.term.TermsPresenter
import kotlinx.android.synthetic.main.fragment_terms.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class TermsFragment : Fragment(), AnkoLogger, TermsContract.View {
    /** Variable zone **/
    private var memberId: Int = 0
    lateinit private var mTermsPresenter: TermsContract.Presenter


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(memberId: Int): TermsFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, memberId)
            val termsFragment: TermsFragment = TermsFragment()
            termsFragment.arguments = bundle
            return termsFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            memberId = arguments.getInt(ARG_1, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_terms, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initInstance()
        mTermsPresenter.onCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mTermsPresenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                activity.supportFragmentManager.popBackStack()
            }
        }

        return true
    }
    /* Override View Interface */
    override fun enableNext() {
        btnNext.isEnabled = true
    }

    override fun disableNext() {
        btnNext.isEnabled = false
    }

    override fun goNext() {
        (activity as TermsFragment.TermsListener).goPhoneFragment()
    }

    /** Method zone **/

    private fun initInstance() {
        mTermsPresenter = TermsPresenter(this)

        cbAccept.setOnCheckedChangeListener { compoundButton, isChecked -> mTermsPresenter.checkTerms(isChecked) }
        btnNext.setOnClickListener { mTermsPresenter.next() }

        (activity as AppCompatActivity).setupToolbar("Step 2 of 4 - Register")
    }

    interface TermsListener {
        fun goPhoneFragment()
    }
}