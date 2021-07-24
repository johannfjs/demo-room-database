package com.johannfjs.demojuly.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun insert(person: Person)

    @Query("select * from Person where username=:user and password=:pass")
    fun validateUser(user: String, pass: String): Person
}