package com.example.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM user_table")
    fun getNumUserRows(): Flow<Int>

    @Query("SELECT * FROM user_table ORDER BY id DESC limit 1")
    fun getUser(): Flow<User>
}