package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
){
    abstract fun formatMessage():String

    companion object AbstractFactory{
        var lastId = -1

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), messageType: String, payLoad:Any):BaseMessage{
            lastId++

            return when (messageType){
                "text" -> TextMessage("$lastId", from, chat, date = date, text = payLoad.toString())
                "image" -> ImageMessage("$lastId", from, chat, date = date, image = payLoad.toString())
                else -> TextMessage("$lastId", from, chat, date = date, text = payLoad.toString())
            }
        }
    }


}