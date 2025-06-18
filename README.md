**Starz Play - Movie DB Demo**


A modern Android demo application for browsing and viewing movies, built with best practices and a modular, scalable architecture.


**Features**
Browse a list of movies fetched from The Movie Database (TMDb) API
View detailed information about each movie
Watch movie trailers using an integrated video player
Search for movies
Modern, responsive UI with Jetpack Compose
Navigation with Compose Destinations
Dependency injection with Hilt
Modularized codebase with a clean architecture approach
Grdale with Version Catalog


**Tech Stack**
Language: Kotlin
Minimum SDK: 24
Target/Compile SDK: 34
UI: Jetpack Compose, Material3
Navigation: Compose Destinations
Dependency Injection: Hilt
Networking: Retrofit 2 with Gson converter
Image Loading: Coil
Video Playback: ExoPlayer Compose
Coroutines: For asynchronous programming
Architecture: Modular, Clean Architecture (with core and app modules)
Testing: JUnit, MockK, Turbine, AndroidX Test
Other: SplashScreen API, ViewModel, Lifecycle, Parcelize, KSP/KAPT


**Project Structure**
Starz-Play/
├── app/        # Main application module (UI, navigation, DI entry point)
├── core/       # Core module (network, data, domain, DI, models)
├── build.gradle.kts, settings.gradle.kts, etc.


Getting Started
1- Clone the repository:
   git clone https://github.com/aliahmad3937/starz-play.git
   cd starz-play


2- Open in Android Studio.
3- Add your TMDb API key and token:
    Place them in the appropriate location in the core module (see Constants in the code).

4- Build and run the app on an emulator or device.



# Pictures
<p float="left">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/login.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/signup.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/homePage.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/googleMap.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/activityTimer.jpeg" width="150" height="280">
</p>

<p float="left">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/mapBoxDirection.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/placeSearch.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/autoPlaceSuggestions.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/googleMap.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/leftDrawer.jpeg" width="150" height="280">
</p>

<p float="left">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/incentives.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/gift.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/cryptoWallets.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/earnPoints.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/focus.jpeg" width="150" height="280">
</p>

<p float="left">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/myPoints.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/myReward.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/profile.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/buyPoints.jpeg" width="150" height="280">
<img src="https://github.com/aliahmad3937/Centrum/blob/master/asset/buySubscription.jpeg" width="150" height="280">
</p>


# License
This project is for demo purposes only.
