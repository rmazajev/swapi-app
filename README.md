# Swapi app

Application is written in Kotlin.

### Implemented features
- Load characters list in homepage screen (used first page from people endpoint)
- Character details page
- Characters list filtering by name
- Basic exception handling
- Loading state handling
- Configuration change handling (screen rotation)
- Unit test for ViewModels

### Used technologies
- MVVM with android architecture components (LiveData, Data binding)
- Retrofit - for handling REST API calls
- Kotlin coroutines - for handling async tasks
- Dagger - DI solution for code decoupling and increase testability

**NOTE:**  To test application I wrote some Unit tests as well tested how app behaves with mocking API responses using Charles Proxy (e.g. bad response code, bad json, timeout). 