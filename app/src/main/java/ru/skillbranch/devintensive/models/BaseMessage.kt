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

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), messageType: MessageType, payLoad:Any):BaseMessage{
            lastId++

            return when (messageType){
                MessageType.TEXT -> TextMessage("$lastId", from, chat, date = date, text = payLoad.toString())
                MessageType.IMAGE -> ImageMessage("$lastId", from, chat, date = date, image = payLoad.toString())
            }
        }
    }

    enum class MessageType{
        TEXT, IMAGE
    }
}