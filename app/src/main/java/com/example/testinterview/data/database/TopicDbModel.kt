package com.example.testinterview.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Question

@Entity(tableName = "topics")
data class TopicDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "category")
    val category: Category,

    @ColumnInfo(name = "questions")
    val questions: List<Question>
)