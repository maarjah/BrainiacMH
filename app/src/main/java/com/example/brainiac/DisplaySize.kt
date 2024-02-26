package com.example.brainiac

//calculating the display size of brain image for Progress Composable
class DisplaySize {
    fun calculateDisplaySize(countYes:Int): Float {
        return (countYes*0.07).toFloat()
    }
}