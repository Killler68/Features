package com.example.features.notes.domain.entities

data class NoteTaskItem(
    val id: Int,
    val type: TypeItem,
    val title: String? = null,
    val description: String? = null,
    val userId: Int,
    val isComplete: Boolean,
    val createTime: Long,
    val backgroundColor: Int
)