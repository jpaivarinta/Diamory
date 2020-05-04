package com.example.diamory



class DiaryModel{
    var date: String? = null
    var diaryText: String? = null
    //var image: String? = null

    constructor(date: String, diaryText: String) {
        this.date = date
        this.diaryText = diaryText
        //this.image = image
    }
    constructor(diaryText: String) {
        this.diaryText = diaryText
    }

}


