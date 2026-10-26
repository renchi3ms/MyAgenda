package com.mgonzalez.myapplication

class Contact (
    var name: String,
    var telNum: String,
    var email: String? = null,
)
{
    val id: Int = ++countId
    // Incremental para crear los IDs

    companion object {

        private var countId = 0;
    }


}