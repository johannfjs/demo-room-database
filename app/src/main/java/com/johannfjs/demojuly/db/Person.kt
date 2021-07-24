package com.johannfjs.demojuly.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Person {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    var lastName: String = ""
    var username: String = ""
    var email: String = ""
    var password: String = ""
}