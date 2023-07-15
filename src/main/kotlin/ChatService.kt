package ru.netology

class ChatService {
    val chats = mutableMapOf<Int, Chat>()

    //создание сообщения и/или чата
    fun addMessage(userId: Int, message: Message) {
        chats
            .getOrPut(userId) { Chat(mutableListOf()) }
            .messages += message
    }

    //удаление сообщения
    fun deleteMessage(userId: Int, messageId: Int) {
        chats[userId]?.messages?.removeAt(messageId)
        println("Message is erased")
    }

    //редактирование сообщения
    fun editMessage(userId: Int, messageId: Int, newMessage: String) {
        chats[userId]?.messages?.get(messageId)?.content = newMessage
        println("Message is edited")
    }

    //удаление чата
    fun deleteChat(userId: Int) {
        chats.remove(userId)
        println("Chat with user $userId is erased")
    }

//    //получение последних amount сообщений
//    fun getLastMessagesWithUserById(userId: Int, amount: Int): List<Message>? {
//
//        val messageList = chats[userId]?.messages?.takeLast(amount)?.onEach { it.isRead = true }
//        when {
//            messageList == null -> println("No chat with the user $userId")
//            messageList.isEmpty() -> println("No messages yet")
//        }
//        return messageList
//    }

    //получение сообщений из чата по id
    fun getMessagesById(userId: Int): MutableList<Message>? {
        return chats[userId]?.messages
    }

//    //получение сообщений из чата по id начиная с lastMessageId
//    fun getMessagesByIdOfLastMessage(userId: Int, lastMessageId: Int): MutableList<Message>? {
//        val messages = chats[userId]?.messages
//        return messages?.subList(lastMessageId, messages.lastIndex)
//    }

    //получение всех чатов
    fun getAllChats(): MutableMap<Int, Chat> {
        return chats
    }

//    //получение количества непрочитанных чатов
//    fun getUnreadChatsCount(): Int {
//        return chats.filterValues { chat: Chat ->
//            chat.messages.any { message: Message ->
//                !message.isRead
//            }
//        }.size
//    }

    //переведённое на Sequence
    fun getUnreadChats(): Int {
        return chats
            .asSequence()
            .map { it.value }
            .map { it.messages }
            .filter { it ->
                it
                    .any { !it.isRead }
            }
            .toList()
            .size
    }

    fun getLastMessagesWithUser(userId: Int, amount: Int, startCount: Int): List<Message> =

        chats
            .getValue(userId)
            .messages
            .asSequence()
            .drop(startCount)
            .take(amount)
            .toList()
}