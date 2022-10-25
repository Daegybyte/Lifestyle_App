package com.example.project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    // @ColumnInfo(name = "firstName")   <<--- We can explicitly set column names, but if
    // they are not explicitly set, the val name is assigned to the column name
    val firstName: String,
    val lastName: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    var activityLevel: Int,
    val isMale: Boolean,
    val location: String,
    val imagePath: String,
    var steps: Int
){
    @PrimaryKey (autoGenerate = true) var id: Int = 0
}