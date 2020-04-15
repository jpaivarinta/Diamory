package com.example.diamory



class DiaryModel{
    var textID: Int = 0
    var date: String? = null
    var diaryText: String? = null
    var image: Int = 0

    constructor(textID: Int, date: String, diaryText: String, image: Int) {
        this.textID = textID
        this.date = date
        this.diaryText = diaryText
        this.image = image
    }
    constructor(diaryText: String) {
        this.diaryText = diaryText
    }

}