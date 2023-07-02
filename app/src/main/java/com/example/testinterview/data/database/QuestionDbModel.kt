package com.example.testinterview.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testinterview.domain.model.Category
import com.example.testinterview.domain.model.Topic

@Entity(tableName = "questions")
data class QuestionDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: Category,
    val topic: Topic,
    val title: String,
    val answer: String
)