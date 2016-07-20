import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.screens.home.slider.HomeImageVideoContract
import com.socket9.pointube.screens.home.slider.HomeImageVideoPresenter
import kotlinx.android.synthetic.main.fragment_home_image_video.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 * Created by ripzery on 7/20/16.
 */

class HomeImageVideoFragment : Fragment(), HomeImageVideoContract.View, AnkoLogger {
    /** Variable zone **/
    private lateinit var mImagePath: String
    private var mIsVideo: Boolean = false
    private val mHomeImageVideoPresenter: HomeImageVideoContract.Presenter by lazy { HomeImageVideoPresenter(this) }


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(imagePath: String, isVideo: Boolean = false): HomeImageVideoFragment {
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_1, imagePath)
            bundle.putBoolean(ARG_2, isVideo)
            val homeImageVideoFragment: HomeImageVideoFragment = HomeImageVideoFragment()
            homeImageVideoFragment.arguments = bundle
            return homeImageVideoFragment
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mImagePath = arguments.getString(ARG_1)
            mIsVideo = arguments.getBoolean(ARG_2)

            info { mImagePath }
        }else{
            mImagePath = savedInstanceState?.getString("imagePath")
            mIsVideo = savedInstanceState?.getBoolean("isVideo")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_home_image_video, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
        mHomeImageVideoPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHomeImageVideoPresenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("imagePath", mImagePath)
        outState.putBoolean("isVideo", mIsVideo)
    }

    /* Override view interface zone */
    override fun showImage(path: String) {
        Glide.with(this).load(path).into(ivImage)
    }

    override fun showVideo(path: String) {
        info { "load video $path"}
    }

    override fun showTouch(msg: String) {
        toast(msg)
    }


    /** Method zone **/

    private fun initInstance() {
        mHomeImageVideoPresenter.load(mIsVideo, mImagePath)
    }
}