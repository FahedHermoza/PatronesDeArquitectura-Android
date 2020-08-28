package com.fahedhermoza.developer.examplenote01.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "note_table")
class Note(@PrimaryKey @ColumnInfo(name = "title") val title: String,
           @ColumnInfo(name = "description") val description: String,
           @ColumnInfo(name = "date") val date: Date
)