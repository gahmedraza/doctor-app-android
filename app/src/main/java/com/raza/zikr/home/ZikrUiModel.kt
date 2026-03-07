package com.raza.zikr.home

data class ZikrUiModel(
    var id: Long? = null,
    var name: String? = null,
    var todayCount: Int? = null,
    var dailyTarget: Int? = null,
    var streak: Int? = null
)