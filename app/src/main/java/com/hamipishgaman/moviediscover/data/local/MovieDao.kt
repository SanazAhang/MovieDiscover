package com.hamipishgaman.moviediscover.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Table.MovieEntity>)

    @Query("SELECT * FROM MovieEntity")
    fun select(): Flow<List<Table.MovieEntity>>
}