package com.example.testinterview.data.database

import com.example.testinterview.domain.model.Topic

class TopicMapper {
    fun mapDbModelToEntity(dbModel: TopicDbModel): Topic {
        return Topic(
            id = dbModel.id,
            category = dbModel.category,
            questions = dbModel.questions
        )
    }

    fun mapEntityToDbModel(entity: Topic): TopicDbModel {
        return TopicDbModel(
            id = entity.id,
            category = entity.category,
            questions = entity.questions
        )
    }

    fun mapListDbModelToListEntity(listDbModel: List<TopicDbModel>) = listDbModel.map {
        mapDbModelToEntity(it)
    }
}