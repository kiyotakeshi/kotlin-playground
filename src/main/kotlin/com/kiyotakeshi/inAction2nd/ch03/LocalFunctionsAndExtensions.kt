package com.kiyotakeshi.inAction2nd.ch03

class User(val id: Int, val name: String, val address: String) {
    override fun toString(): String {
        return "User(id=$id, name='$name', address='$address')"
    }
}

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException(
                "can't save user $id: empty $fieldName"
            )
        }
    }

    validate(name, "name")
    validate(address, "address")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
    println("save user: $user")
}