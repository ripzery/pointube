package com.socket9.pointube.screens.promotion.list.thai_airline.chart

/**
 * Created by ripzery on 8/11/16.
 */
class AwardChartPresenter(var view: AwardChartContract.View?) : AwardChartContract.Presenter {
    private val mAwardChartList = mutableListOf(
            AwardChartModel("Zone 1", "Within Thailand", "", AwardPriceTrip("15000", "17500", "20000", "N/A"), AwardPriceTrip("9000", "11000", "12000", "N/A")),
            AwardChartModel("Zone 1", "Between Phuket and Chiang Mai", "", AwardPriceTrip("25000", "30000", "40000", "N/A"), AwardPriceTrip("17500", "21000", "28000", "N/A")),
            AwardChartModel("Zone 1", "Destinations up to 1,000 miles:", "Dhaka, Hanoi, Ho Chi Minh City, Kuala Lumpur ,Kunming, Luang Prabang, Mandalay, Penang, Phnom Penh, Singapore, Vientiane, Yangon", AwardPriceTrip("15000", "17500", "20000", "N/A"), AwardPriceTrip("9000", "11000", "12000", "N/A")),
            AwardChartModel("Zone 2", "Destinations from 1,001-2,000 miles:", "Bengalura, Changsha, Chengdu, Chongqing, Chennai, Colombo,  Denpasar, Guangzhou, Hong Kong, Hyderaila, Mumbai, New Delhi, Shanghai, Taipei, Xiamenbad, Jakarta, Kathmandu, Kolkata, Manila, Mumbai, New Delhi, Shanghai, Taipei, Xiamen", AwardPriceTrip("35000", "45000", "55000", "70000"), AwardPriceTrip("24500", "31500", "38500", "49000")),
            AwardChartModel("Zone 3", "Destinations from 2,001-3,600 miles: ", "Beijing, Busan, Dubai, Fukuoka, Islamabad, Karachi, Kuwait, Lahore, Muscat, Nagoya, Osaka, Perth, Seoul, Sapporo,Tokyo(Haneda, Narita)", AwardPriceTrip("45000", "60000", "75000", "110000"), AwardPriceTrip("31500", "42000", "52500", "77000")),
            AwardChartModel("Zone 4", "Destinations from 3,601-4,800 miles:", "Brisbane, Melbourne, Sydney", AwardPriceTrip("55000", "70000", "98000", "150000"), AwardPriceTrip("38500", "49000", "68500", "105000")),
            AwardChartModel("Zone 5", "Destinations from 4,801-8,000 miles:", "Auckland, Brussels, Copenhagen, Frankfurt, London, Madrid, Milan, Oslo, Paris, Rome, Stockholm, Zurich", AwardPriceTrip("70000", "110000", "130000", "185000"), AwardPriceTrip("49000", "77000", "89000", "129000")),
            AwardChartModel("Zone 6", "Destinations from 8,001-12,500 miles:", "Between Europe and Australia or New Zealand via Bangkok", AwardPriceTrip("90000", "130000", "170000", "230000"), AwardPriceTrip("63000", "89000", "118000", "160000"))
    )

    override fun loadCharts() {
        view?.showChartList(mAwardChartList)
    }

    override fun clickChart(position: Int) {
        view?.showChartDetail(position)
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        view = null
    }
}
