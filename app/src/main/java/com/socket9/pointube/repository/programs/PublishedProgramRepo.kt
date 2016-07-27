package com.socket9.pointube.repository.programs

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by ripzery on 7/20/16.
 */

open class PublishedProgramItemRepo(
        @PrimaryKey open var Id: Int = 0,
        open var Code: String = "",
        open var Title: String = "",
        open var IsHot: Boolean = false,
        open var Point: Int = 0,
        open var SpecialPoint: Int = 0,
        open var ProviderId: Int = 0,
        open var Description: String = "",
        open var MasterPath: String = "",
        open var PeriodStr: String = "",
        open var UnitOfPoint: String = "",
        open var TermAndCondition: String = "",
        open var ProgramType: Int = 9,
        open var Place: String = "",
        open var HowTo: String = "",
        open var LogoPath: String = "",
        open var Channels: RealmList<PublishedChannel> = RealmList(),
        open var ValidPeriod: PublishedPeriod? = null,
        open var PublishPeriod: PublishedPeriod? = null
) : RealmObject() {

}

open class PublishedChannel(
        open var Id: Int = 0,
        open var Name: String = ""
) : RealmObject() {

}

open class PublishedPeriod(
        open var StartDate: Date = Date(),
        open var EndDate: Date = Date()
) : RealmObject() {

}