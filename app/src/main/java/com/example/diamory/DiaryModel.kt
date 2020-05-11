package com.example.diamory



class DiaryModel{
    var date: String? = null
    var diaryText: String? = null
    var image: ByteArray? = null //MUUTOKSIA

    constructor(date: String, diaryText: String, image: ByteArray) {
        this.date = date
        this.diaryText = diaryText
        this.image = image //MUUTOKSIA
    }
    constructor(date: String, diaryText: String) {
        this.date = date
        this.diaryText = diaryText
    }


}


