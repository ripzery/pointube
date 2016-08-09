import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import kotlinx.android.synthetic.main.fragment_thai_airline_award.*

/**
 * Created by ripzery on 7/20/16.
 */

class ThaiAirlineAwardFragment : Fragment() {

    /** Variable zone **/
    private var mBrandId: Int = 0


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(brandId: Int): ThaiAirlineAwardFragment {
            var bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, brandId)
            val templateFragment: ThaiAirlineAwardFragment = ThaiAirlineAwardFragment()
            templateFragment.arguments = bundle
            return templateFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mBrandId = arguments.getInt(ARG_1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_thai_airline_award, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    /** Method zone **/

    private fun initInstance() {
        airAwardChart.setOnClickListener {
//            startActivity()
        }
    }
}