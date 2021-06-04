package com.hamipishgaman.moviediscover.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Table.MovieEntity::class], version = 1 , exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}