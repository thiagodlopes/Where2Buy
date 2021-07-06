package com.thdlopes.where2buy.data

import com.google.firebase.database.Exclude

data class Market(
    @get:Exclude
    var id: String? = null,
    var name: String? = null
) {
}