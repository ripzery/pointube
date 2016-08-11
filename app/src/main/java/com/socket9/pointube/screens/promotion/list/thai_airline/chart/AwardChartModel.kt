package com.socket9.pointube.screens.promotion.list.thai_airline.chart

/**
 * Created by ripzery on 8/10/16.
 */
data class AwardChartModel(val title: String, val subtitle: String, val description: String, val priceRound: AwardPriceTrip, val priceOneWay: AwardPriceTrip)

data class AwardPriceTrip(val economy: String, val premiumEconomy: String, val silkClass: String, val firstClass: String)