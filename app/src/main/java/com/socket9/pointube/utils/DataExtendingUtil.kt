package com.socket9.pointube.utils

import com.socket9.pointube.manager.DiskProviderManager
import com.socket9.pointube.repository.brands.BrandRepo
import com.socket9.pointube.repository.programs.PublishedProgramItemRepo
import com.socket9.pointube.screens.home.HomeModel

/**
 * Created by ripzery on 7/20/16.
 */
object DataExtendingUtil {
    fun countProviderProgram() {
        val allProviders: MutableList<BrandRepo>? = RealmUtil.readAll(BrandRepo::class.java)
        val allPrograms: MutableList<PublishedProgramItemRepo>? = RealmUtil.readAll(PublishedProgramItemRepo::class.java)

        if (allPrograms != null && allProviders != null) {
            allProviders.forEach {
                val providerId = it.Id
                val provider = it
                RealmUtil.write {
                    provider.TotalPrograms = allPrograms.filter { it.ProviderId == providerId }.size
                }
            }
        }
    }
}