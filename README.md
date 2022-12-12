# <p align="center">Movie</p>

<p align="center">Movie is sample based on MVVM architecture for Android</br>
This data flow is on repository pattern.</p>



<img src="https://github.com/KennethSS/Movie/blob/master/Preview/demo.gif" align="right" width="30%"></img>

## Tech stack & Open source libraries

- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- Hilt (alpha) for dependency injection.
- JetPack
  - LiveData - Bbservable data holder class to notify to views.
  - Lifecycle - Response to a change in the lifecycle status of another component
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - Construct a database abstraction layer over SQLite 
  - Navigation - Framework for navigating between destinations
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - Clean Architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [SmoothBottomBar](https://github.com/ibrahimsn98/SmoothBottomBar) - A lightweight material bottom navigation bar

- Solar libraries
  - [SolarBinding](https://github.com/KennethSS/SolarBinding) - A SolarBinding with databinding for andorid
  - [SolarListView](https://github.com/KennethSS/SolarListView) - A SimpleRecyclerView with databinding for andorid list

## MAD Score
![summary](https://github.com/KennethSS/Movie/blob/master/Preview/summary.png)
![kotlin](https://github.com/KennethSS/Movie/blob/master/Preview/kotlin.png)

## Architecture
![CleanArchitecture](https://github.com/KennethSS/Movie/blob/master/Preview/CleanArchitecture.jpg)
![MVVM Architecture](https://github.com/KennethSS/Movie/blob/master/Preview/mvvm.png)

## License

```
Copyright 2020 KennethSS

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
```
