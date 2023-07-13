package ru.netology

fun main() {
    val service = ChatService()
    println(service.chats)

    repeat(10) {
        service.addMessage(1, Message(1, "Message $it"))
    }
    println(service.chats)

    println(service.getLastMessagesWithUserById(1, 5))
}