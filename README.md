# AndroidParking
Android app written in Kotlin, consumes an API via Retrofit2 and making use of Coroutines (it also uses LiveData, but it's expendable); it also features Picasso by Square. Uses MVVM pattern.

This app is an exam I did, I'm still learning, so it may have common mistakes.

The purpose of this app is that in your first activity you have a drawer where you can register, login or logout;it also displays a 
recyclerview which shows the autonomous communities of Spain, if you click one of they you will go to an Activity with all the provinces 
of the given community. If you click on any province, another activity will show up with all the cities/towns of that province, 
if any province is pressed other activity will display with near parking sites. If you are logged in you will be able to click on this 
places and it will show you the details of the clicked place on a new activity; it includes images, some description and the user rating 
this place has (you can submit your rating using the different faces, once done it will return the user to the previous activity)

P.S: I hope my English doesn't make your eyes bleed
