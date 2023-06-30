package com.example.testinterview.data.database

import com.example.testinterview.domain.model.Question

class QuestionMapper {
    fun mapDbModelToEntity(dbModel: QuestionDbModel): Question {
        return Question(
            id = dbModel.id,
            category = dbModel.category,
            topic = dbModel.topic,
            title = dbModel.title,
            answer = dbModel.answer
        )
    }

    fun mapEntityToDbModel(entity: Question): QuestionDbModel {
        return QuestionDbModel(
            id = entity.id,
            category = entity.category,
            topic = entity.topic,
            title = entity.title,
            answer = entity.answer
        )
    }

    fun mapListDbModelToListEntity(listDbModel: List<QuestionDbModel>) = listDbModel.map {
        mapDbModelToEntity(it)
    }
}