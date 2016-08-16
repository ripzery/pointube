package com.socket9.pointube.manager

import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.brands.BrandUnitRepo
import com.socket9.pointube.repository.brands.GetMemberBrand
import com.socket9.pointube.repository.brands.GetMemberSelectBrand
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.home.HomeModel
import com.socket9.pointube.screens.promotion.list.thai_airline.chart.AwardChartModel
import com.socket9.pointube.screens.promotion.list.thai_airline.chart.AwardPriceTrip
import com.socket9.pointube.utils.RealmUtil
import org.jetbrains.anko.AnkoLogger
import rx.Observable

/**
 * Created by Euro (ripzery@gmail.com) on 7/16/2016 AD.
 */
object DiskProviderManager : AnkoLogger {
    /* Brands */
    fun getAllProvider(): Observable<HomeModel.AllBrands> {
        val allBrands = RealmUtil.readAll(BrandRepo::class.java)
        return Observable.just(HomeModel.AllBrands(true, allBrands.toMutableList(), true))
    }

    fun getAllBrandMember(): Observable<GetMemberBrand> {
        val allBrands = RealmUtil.readAll(GetMemberBrand::class.java)
        return Observable.just(allBrands.firstOrNull())
    }

    fun getAllBrandSelectMember(): Observable<GetMemberSelectBrand> {
        val allBrands = RealmUtil.readAll(GetMemberSelectBrand::class.java)
        return Observable.just(allBrands.firstOrNull())
    }

    /* Programs */
    fun getPublishedProgramList(): Observable<HomeModel.PublishedProgramListRepo> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(HomeModel.PublishedProgramListRepo(true, null, allPublishedProgramList.toMutableList(), true))
    }

    fun getPublishedProgramListByProgramType(programType: Int = 0): Observable<MutableList<PublishedProgramItemRepo>> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        if (programType < 11)
            return Observable.just(allPublishedProgramList.filter { it.ProgramType == programType }.toMutableList())
        else {
            return Observable.just(allPublishedProgramList.filter { it.IsHot }.toMutableList())
        }
    }

    fun getPublishedProgramById(programId: Int): Observable<PublishedProgramItemRepo> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(allPublishedProgramList.find { it.Id == programId })
    }

    fun getProviderById(id: Int = 0): Observable<BrandRepo> {
        val brand = RealmUtil.readAll(BrandRepo::class.java).find { it.Id == id }
        return Observable.just(brand)
    }

    fun getProviderByName(name: String): Observable<BrandRepo> {
        val brand = RealmUtil.readAll(BrandRepo::class.java).find { it.Name.equals(name) }
        return Observable.just(brand)
    }

    fun getBrandUnitByName(name: String): Observable<BrandUnitRepo> {
        val brand = RealmUtil.readAll(BrandUnitRepo::class.java).find { it.Name.equals(name) }
        return Observable.just(brand)
    }

    fun getPublishedProgramByProviderId(providerId: Int): Observable<MutableList<PublishedProgramItemRepo>> {
        val allPublishedProgramList = RealmUtil.readAll(PublishedProgramItemRepo::class.java)
        return Observable.just(allPublishedProgramList.filter { it.ProviderId == providerId }.toMutableList())
    }

    fun getAirlineAwardChartList(): Observable<MutableList<AwardChartModel>> {
        val mAwardChartList = mutableListOf(
                AwardChartModel("Zone 1", "Within Thailand", "", AwardPriceTrip("15000", "17500", "20000", "N/A"), AwardPriceTrip("9000", "11000", "12000", "N/A")),
                AwardChartModel("Zone 1", "Between Phuket and Chiang Mai", "", AwardPriceTrip("25000", "30000", "40000", "N/A"), AwardPriceTrip("17500", "21000", "28000", "N/A")),
                AwardChartModel("Zone 1", "Destinations up to 1,000 miles:", "Dhaka, Hanoi, Ho Chi Minh City, Kuala Lumpur ,Kunming, Luang Prabang, Mandalay, Penang, Phnom Penh, Singapore, Vientiane, Yangon", AwardPriceTrip("15000", "17500", "20000", "N/A"), AwardPriceTrip("9000", "11000", "12000", "N/A")),
                AwardChartModel("Zone 2", "Destinations from 1,001-2,000 miles:", "Bengalura, Changsha, Chengdu, Chongqing, Chennai, Colombo,  Denpasar, Guangzhou, Hong Kong, Hyderaila, Mumbai, New Delhi, Shanghai, Taipei, Xiamenbad, Jakarta, Kathmandu, Kolkata, Manila, Mumbai, New Delhi, Shanghai, Taipei, Xiamen", AwardPriceTrip("35000", "45000", "55000", "70000"), AwardPriceTrip("24500", "31500", "38500", "49000")),
                AwardChartModel("Zone 3", "Destinations from 2,001-3,600 miles: ", "Beijing, Busan, Dubai, Fukuoka, Islamabad, Karachi, Kuwait, Lahore, Muscat, Nagoya, Osaka, Perth, Seoul, Sapporo,Tokyo(Haneda, Narita)", AwardPriceTrip("45000", "60000", "75000", "110000"), AwardPriceTrip("31500", "42000", "52500", "77000")),
                AwardChartModel("Zone 4", "Destinations from 3,601-4,800 miles:", "Brisbane, Melbourne, Sydney", AwardPriceTrip("55000", "70000", "98000", "150000"), AwardPriceTrip("38500", "49000", "68500", "105000")),
                AwardChartModel("Zone 5", "Destinations from 4,801-8,000 miles:", "Auckland, Brussels, Copenhagen, Frankfurt, London, Madrid, Milan, Oslo, Paris, Rome, Stockholm, Zurich", AwardPriceTrip("70000", "110000", "130000", "185000"), AwardPriceTrip("49000", "77000", "89000", "129000")),
                AwardChartModel("Zone 6", "Destinations from 8,001-12,500 miles:", "Between Europe and Australia or New Zealand via Bangkok", AwardPriceTrip("90000", "130000", "170000", "230000"), AwardPriceTrip("63000", "89000", "118000", "160000"))
        )

        return Observable.just(mAwardChartList)
    }
}