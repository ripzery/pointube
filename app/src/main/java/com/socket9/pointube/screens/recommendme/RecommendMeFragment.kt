import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.socket9.pointube.R
import com.socket9.pointube.extensions.hideLoadingDialog
import com.socket9.pointube.extensions.showLoadingDialog
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.promotion.list.PromotionListViewGroup
import com.socket9.pointube.screens.recommendme.RecommendMeContract
import com.socket9.pointube.screens.recommendme.RecommendMePresenter
import kotlinx.android.synthetic.main.fragment_recommend_me.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by ripzery on 7/20/16.
 */

class RecommendMeFragment : Fragment(),AnkoLogger, RecommendMeContract.View {
    /** Variable zone **/
    lateinit var param1: String
    lateinit var mRecommendMePresenter : RecommendMeContract.Presenter
    lateinit var mActivity: AppCompatActivity
    lateinit var mRecommendMeAdapter: RecommendMeAdapter
//    lateinit var mAdapter


    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"

        fun newInstance(param1: String): RecommendMeFragment {
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_1, param1)
            val recommendMe: RecommendMeFragment = RecommendMeFragment()
            recommendMe.arguments = bundle
            return recommendMe
        }

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
        val rootView: View = inflater!!.inflate(R.layout.fragment_recommend_me, container, false)

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecommendMePresenter = RecommendMePresenter(this)
        initInstance()
    }

    /* Override View Interface */
    override fun showRecommendMe(list: MutableList<PublishedProgramItemRepo>) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBrandInfo(model: BrandRepo) {
        with(model){
            tvBrandName.text = this.Name
            tvPoint.text = String.format("%,d", Points.toInt());
            Glide.with(this@RecommendMeFragment).load(LogoPath).into(ivLogo)
        }
    }

    override fun showLoading() {
        showLoadingDialog("Please wait", "Loading recommend promotion...")
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showEmptyView() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Method zone **/

    private fun initInstance() {
        /* Init recycler view */
        mRecommendMeAdapter = RecommendMeAdapter(mutableListOf(), object: RecommendMeListener{
            override fun onItemClick(programId: Int) {
                /* TODO: show program detail*/
            }
        })

    }

    inner class RecommendMeAdapter(var list: MutableList<PublishedProgramItemRepo>, val listener: RecommendMeListener ) : RecyclerView.Adapter<RecommendMeAdapter.RecommendMeViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecommendMeViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_promotion_list, parent, false)
            return RecommendMeViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: RecommendMeViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        fun updateProgramList(newList: MutableList<PublishedProgramItemRepo>) {
            this.list = newList
            notifyDataSetChanged()
        }

        inner class RecommendMeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val mPromotionListViewGroup: PromotionListViewGroup by lazy { itemView.findViewById(R.id.promotionList) as PromotionListViewGroup }

            init {
                mPromotionListViewGroup.setOnClickListener { listener.onItemClick(list[adapterPosition].Id) }
            }

            fun setModel(model: PublishedProgramItemRepo) {
                mPromotionListViewGroup.setModel(model)
            }
        }
    }

    interface RecommendMeListener{
        fun onItemClick(programId: Int)
    }
}