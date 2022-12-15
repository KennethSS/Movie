# <p align="center">Movie</p>

<p align="center">Movie is sample based on MVI architecture for Android</br>
This design was created based on Naver's SeriesOn.</br>
This data flow is on repository pattern.</p>

<img src="https://user-images.githubusercontent.com/39362939/207137825-eb83696b-0408-4e8a-b2d8-02b11b5a9d4e.gif" align="right" width="30%"></img>

## Tech stack & Open source libraries

- Minimum SDK level 28
- 100% [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines), Flow
- 100% Jetpack Compose
- Hilt for dependency injection.
- JetPack
  - ViewModel - UI related data holder, lifecycle aware.
  - Navigation - Framework for navigating between destinations
  - Lifecycle - Response to a change in the lifecycle status of another component
- Architecture
  - MVI Architecture (Model - View - Intent)
  - Repository pattern
  - Clean Architecture
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Coil](https://coil-kt.github.io/coil/compose/) - loading images.

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
