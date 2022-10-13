# The Movie Database API
 Android app displaying movies from <https://www.themoviedb.org/>
 
## Patterns and Third party libraries used

### 1. Android Jetpack  
   - Livedata, ViewModel, Pagination, Room, Navigation, Preference

### 2. MVVM 
   - MVVM guides us how to distribute responsibilities between classes in a GUI application (or between layers - more about this later), with the goal of having a small number of classes, while keeping the number of responsibilities per class small and well defined.

### 3. Dagger2
   - Dagger is a compile-time framework for dependency injection. ( Big fan oh Dagger and Hilt)

### 4. Retrofit2
   - A type-safe HTTP client for Android. Retrofit 2 is great networking library for modern Android apps

### 5. Coroutines
   - To be ab;le to scroll endless data we run threads that can also provide parallelism but there is blocking and context switching courtesy of coroutines.

### 6. Single Activity Pattern
   - This is the architecture that has only one Activity or a relatively small number of Activities. Instead of having one Activity represent one screen, we view an Activity as a big container with the fragments inside the Activity representing the screen. As you'll see in this project.

### 7. Material Design
   - Recommended design language by Google.

### 8. Picasso
   - A powerful image downloading and caching library for Android for loading our movie images and storing them locally.

### 9.  Room Database
  - Best alternative to avoid boilerplate code to create and manage databases and the Best persisting android database that saves data locally.

## Features
This project is divided into 4 main categories:

1. Trending movies  
2. Popular movies 
3. Popular movies for Children
4. Favorite movies

## Extra features include:   
1. Movie details 
2. Change label and visibility of navigation drawer menu

## Note!
 - Written in Kotlin 1.4 but upgraded the plugin to 1.6.10. There are a tonne of warnings, basically syntax.
 - Upgraded to JDK11
 - Android Studio Bumblebee | 2021.1.1 Patch 1
 
  
