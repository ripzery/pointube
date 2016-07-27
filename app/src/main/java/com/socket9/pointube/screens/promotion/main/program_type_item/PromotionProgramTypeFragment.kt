import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socket9.pointube.R
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.promotion.main.program_type_item.PromotionProgramTypeContract
import com.socket9.pointube.screens.promotion.main.program_type_item.PromotionProgramTypePresenter
import com.socket9.pointube.screens.promotion.viewgroups.PromotionItemViewGroup
import kotlinx.android.synthetic.main.fragment_promotion_program_type.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast

/**
 * Created by ripzery on 7/20/16.
 */

class PromotionProgramTypeFragment : Fragment(), AnkoLogger, PromotionProgramTypeContract.View {


    /** Variable zone **/
    private var mPosition: Int = 0
    private var mTitle: String = ""
    lateinit var mPromotionProgramTypePresenter: PromotionProgramTypeContract.Presenter
    lateinit var mPromotionProgramTypeAdapter: PromotionProgramTypeAdapter

    /** Static method zone **/
    companion object {
        val ARG_1 = "ARG_1"
        val ARG_2 = "ARG_2"

        fun newInstance(position: Int, title: String): PromotionProgramTypeFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(ARG_1, position)
            bundle.putString(ARG_2, title)
            val promotionProgramType: PromotionProgramTypeFragment = PromotionProgramTypeFragment()
            promotionProgramType.arguments = bundle
            return promotionProgramType
        }

    }

    /** Activity method zone  **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            /* if newly created */
            mPosition = arguments.getInt(ARG_1, 0)
            mTitle = arguments.getString(ARG_2)
        }else{
            mPosition = savedInstanceState.getInt("position")
            mTitle = savedInstanceState.getString("title", "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_promotion_program_type, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPromotionProgramTypePresenter = PromotionProgramTypePresenter(this)
        if(savedInstanceState != null){
            mPosition = savedInstanceState.getInt("position")
            mTitle = savedInstanceState.getString("title", "")
        }
        initInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPromotionProgramTypePresenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt("position", mPosition)
        outState!!.putString("title", mTitle)
        super.onSaveInstanceState(outState)
    }

    /* Override View Interface */

    override fun showAllProgram(list: MutableList<PublishedProgramItemRepo>) {
        toast("${list.size}")
        mPromotionProgramTypeAdapter.updateList(list)
    }

    override fun showProgramDetail(id: Int) {

    }

    override fun showProgramEmpty() {
        toast("Empty")
    }

    /** Method zone **/

    private fun initInstance() {
        mPromotionProgramTypePresenter.onCreate()

        mPromotionProgramTypeAdapter = PromotionProgramTypeAdapter(mutableListOf(), object : PromotionItemClickListener {
            override fun onItemClick(promotionId: Int) {
                toast("$promotionId")
            }
        })

//        recyclerView.layoutManager = LinearLayoutManager(context)
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if(position == 0) 2 else 1
            }
        }

        recyclerView.adapter = mPromotionProgramTypeAdapter
        tvTypeTitle.background.level = mPosition
        tvTypeTitle.text = mTitle

        mPromotionProgramTypePresenter.loadAllProgramByType(mPosition)
    }

    /* Inner class */

    class PromotionProgramTypeAdapter(var list: MutableList<PublishedProgramItemRepo>, val listener: PromotionItemClickListener) : RecyclerView.Adapter<PromotionProgramTypeAdapter.PromotionProgramTypeViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PromotionProgramTypeViewHolder {
            val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.itemview_promotion_item, parent, false)
            return PromotionProgramTypeViewHolder(view)
        }

        override fun onBindViewHolder(holder: PromotionProgramTypeViewHolder?, position: Int) {
            holder!!.setModel(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun updateList(list: MutableList<PublishedProgramItemRepo>) {
            this.list = list
            notifyDataSetChanged()
        }

        inner class PromotionProgramTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val mPromotionItemViewGroup: PromotionItemViewGroup by lazy { itemView.find<PromotionItemViewGroup>(R.id.promotionItem) }

            init {
                mPromotionItemViewGroup.setOnClickListener {
                    listener.onItemClick(list[adapterPosition].Id)
                }
            }

            fun setModel(model: PublishedProgramItemRepo) {
                mPromotionItemViewGroup.setModel(model)
            }

        }


    }

    interface PromotionItemClickListener {
        fun onItemClick(promotionId: Int)
    }

}