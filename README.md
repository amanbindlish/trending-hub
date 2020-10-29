# Trending Hub - A Github Trending Repositories App (Kotlin)

A basic single screen android application which shows most trending repositories from github using its public api.

<div align="center">
  <a href="https://amanbindlish.com">
  <img alt="Logo" src="https://user-images.githubusercontent.com/50623363/82434256-2fd5d880-9ac5-11ea-9d2f-8d2db5d18340.png" width="100" />
  </a>
</div>

<h2 align="center">
  Github Trending Repositories - Kotlin
</h2>

#### App Screens
<p>
<img src="/media/trending_hub_one.gif" width="300" height="634">   <img src="/media/trending_hub_two.gif" width="300" height="634">
<img src="/media/trending_hub_three.gif" width="300" height="634">    <img src="/media/mvvm_data_flow.png" width="550" height="500">
</p>

#### App Features
* App fetches github trending repositories and displays in RecyclerView.
* App shows shimmer effect while data is being fetched from server.
* App has expanded recycler view where the items expand on single tap and collapse if expanded.
* 100% offline support. If data has been fetched once, then it will be served from db (Room).
* The cached data is only valid for 2 hours, after which data will be fetched again from server.
* Repository class where database serves as a single source of truth for data.
* Pull to refresh functionality to fetch updated data forcefully.
* Error cases handled and an error screen with retry button is shown in case of error.
* Sort by Stars and Sort by Names functionality for data sorting.
* App includes UI and unit testing using Junit4 and Espresso.

#### App Architecture 
Based on MVVM architecture and repository pattern, where database serves as a single source of truth for data.
Refer media/mvvm_data_flow.png for diagram explanation.

#### App Specs
- Minimum SDK 19
- [Java8](https://java.com/en/download/faq/java8.xml)
- [Kotlin](https://kotlinlang.org/)
- MVVM Architecture
- Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
- [Lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Dagger 2](https://google.github.io/dagger/) for dependency injection.
- [Retrofit 2](https://square.github.io/retrofit/) for API integration.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for data observation.
- [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) for data container.
- [Room](https://developer.android.com/topic/libraries/architecture/room) for data persistence.
- [Gson](https://github.com/google/gson) for serialisation.
- [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
- [Mockito](https://site.mockito.org/) for implementing unit test cases.
- [Glide](https://github.com/bumptech/glide) for image loading.

#### App includes following main components:
* A local database with Room that servers as a single source of truth for data to display on UI. 
* A public github api for fetching data from server (https://github-trending-api.now.sh/repositories).
* A repository that works with the database and the api service, providing a unified data interface.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
* UI testing using Espresso and mock server for activity and fragment.
* Unit Test cases for API service, Api Response, Database, Repository and ViewModel.

#### App Packages
* <b>data</b> package containing classes related to api, response and repository.
* <b>db</b> package containing classes for local room database and dao.
* <b>di</b> package containing classes for dependency injection (modules, components).
* <b>ui</b> package containing classes for activity, fragments and adapter.
* <b>viewmodel</b> package for viewmodel classes.


You can reach out to me for any updates and explanation and can also create pull requests for your updates here.

Follow me on [<img src="https://img.icons8.com/nolan/64/twitter-squared.png" width="28"/>](https://twitter.com/bindlishaman) [<img src="https://img.icons8.com/nolan/64/linkedin.png" width="28"/>](https://www.linkedin.com/in/amanbindlish/)