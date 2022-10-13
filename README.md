# The Movie Database API
A simple Android app that displays movie and their details
from <https://www.themoviedb.org/>
 
## Architecture used 
Developers always prefer a clean and structured code for the projects. 
By organizing the codes according to a design pattern helps in the maintenance of the software.
By having knowledge of all crucial logic parts of the android application, it is easier to add and remove app features. 
Further, design patterns also assure that all the codes get covered in Unit Testing without the interference of other classes.
Model — View — ViewModel (MVVM) is the industry-recognized software architecture pattern that overcomes all drawbacks of MVP and MVC design patterns.
MVVM suggests separating the data presentation logic(Views or UI) from the core business logic part of the application.

### Frameworks and Libraries Used

### 1. Android Jetpack

ViewModel, Pagination, Room, Navigation,Remote Mediator

### 2. MVVM 
   - MVVM guides us how to distribute responsibilities between classes in a GUI application (or between layers - more about this later), with the goal of having a small number of classes, while keeping the number of responsibilities per class small and well defined.

### 3. Dagger-Hilt
   - Hilt is a compile-time framework for dependency injection

### 4. Retrofit2
   - A type-safe HTTP client for Android. Retrofit 2 is great networking library for modern Android apps

### 5. Coroutines
   - To be ab;le to scroll endless data we run threads that can also provide parallelism but there is blocking and context switching courtesy of coroutines.

### 6. Single Activity Pattern
   - This is the architecture that has only one Activity or a relatively small number of Activities. Instead of having one Activity represent one screen, we view an Activity as a big container with the fragments inside the Activity representing the screen. As you'll see in this project.
   
### 7 Picasso
   - A powerful image downloading and caching library for Android for loading our movie images and storing them locally.

### 8.  Room Database
  - Best alternative to avoid boilerplate code to create and manage databases and the Best persisting android database that saves data locally.

#Assumptions made based on the requirements
- Because the themoviedb is an extensive API with a lot of media resources i only used the movies section of the api as mention in the deliverables 

### Any Features i did not complete
i was looking at implementing similar movies section but due to time constraints i was unable
  
