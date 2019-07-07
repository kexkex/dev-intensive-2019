package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        var firstName:String? = null
        var lastName:String? = null
        if (fullName != null) {
            if (!fullName.trim().isBlank()) {
                firstName = fullName.split(" ").getOrNull(0)
                lastName = fullName.split(" ").getOrNull(1)
            }
        }
        return Pair(firstName,lastName)

    }
}