package com.example.diamory



class DiaryModel{
    var textID: Int = 0
    var date: String? = null
    var diaryText: String? = null

    constructor(textID: Int, date: String, diaryText: String) {
        this.textID = textID
        this.date = date
        this.diaryText = diaryText
    }
    constructor(diaryText: String) {
        this.diaryText = diaryText
    }

}