package com.a7acdhmwtext_liverecycleview

class Person(val firstName: String, val secondName: String){
    companion object{
        val listOfPerson : MutableList<Person> = mutableListOf(Person("Rodion", "Gra"))
    }

    override fun toString(): String {
        return "$firstName $secondName"
    }
}