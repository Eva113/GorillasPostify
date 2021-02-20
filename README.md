## GorillasPostify - an interview task.

This is a simple posts fetching application, showing a list of posts with ability to navigate to the post details. This is my first project with GraphQL.

### Architecture overview
* Written with clean architecture in mind. 
* Multi modular (each feature in a separate module). 
* MVVM is used for the presentation layer
* The data layer is in a separate module for keeping Apollo dependencies and generated classes out of the other modules.
	* GraphQL for network operations 
	* Made GraphQL generated classes internal to not expose them to other modules
* Unit tests for all layers
* Koin used dependency injection.
* Navigator module that simplifies the navigation between modules. Is easily extendable 
