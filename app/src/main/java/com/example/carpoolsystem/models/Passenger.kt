package com.example.carpoolsystem.models

class Passenger(name: String, emailId: String,password:String,phonenumber:String):User(name,emailId,password,phonenumber){
    constructor(phonenumber: String):this("", "","",phonenumber)
    constructor(name: String,emailId: String,password: String):this(name,emailId,password,"")

}