package com.example.carpoolsystem.models

class Driver(name: String, emailId: String, phonenumber: String) :
    User(name, emailId, phonenumber) {
    constructor(phonenumber: String) : this("", "", phonenumber)
    constructor(name: String, emailId: String) : this(name, emailId, "")
}