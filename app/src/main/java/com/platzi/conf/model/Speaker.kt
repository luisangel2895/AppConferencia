package com.platzi.conf.model

import java.io.Serializable

class Speaker: Serializable {// con esta herencia de serializable podemos pasar este objeto entre activities
    var name = ""
    var jobtitle = ""
    var workplace = ""
    var biography = ""
    var twitter = ""
    var image = ""
    var category = 0
}