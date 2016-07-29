package com.socket9.pointube.screens.promotion.main.program_type_item

import com.socket9.pointube.manager.DataManager
import com.socket9.pointube.screens.promotion.main.PromotionPagerAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * Created by ripzery on 7/26/16.
 */
class PromotionProgramTypePresenter(var view: PromotionProgramTypeContract.View?) : AnkoLogger, PromotionProgramTypeContract.Presenter {
    override fun loadAllProgramByType(programType: Int) {
        DataManager.getPublishedProgramListByProgramType(programType)
                .subscribe({
                    if(it.size > 0){
                        view?.showAllProgram(it)
                    }else{
                        view?.showProgramEmpty()
                    }
                }, {
                    warn(it)
                })
    }

    override fun clickPromotion(id: Int) {
        view?.showProgramDetail(id)
    }

    override fun setBackgroundColor(programType: Int) {
        val index = PromotionPagerAdapter.PAIR_POSITION_TO_TYPE.find { programType == it.second }!!.first
        view?.showBackgroundColor(PromotionPagerAdapter.TAB_ITEM_COLORS[index])
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }

}