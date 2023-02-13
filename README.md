# ComposeNavigations

Navigation library for screens written in Android's Jetpack Compose,
that survives configuration changes and process death.

# Snapshot release
Snapshot artifacts available in the [Snapshot repository](https://s01.oss.sonatype.org/content/repositories/snapshots/dev/srijith/composenavigations/composenavigations/1.0.0-SNAPSHOT/).

# Usage
## 1. Define the `NavigationViewModel`
```val navigatorVM: NavigationViewModel = navigator()```

Then initialise a listener which will get called whenever a navigation happens:
```
    val navigateTo: MutableState<Destination?> = mutableStateOf(null)
    navigatorVM.observeDestination {
        navigateTo.value = it
    }
```
## 2. Define the navigation graph
Then define a navigation graph using `NavDestinations` with all the possible screens that user can navigate to.

It takes `Destination` as a parameter. This object is used to identify which screen to navigate to from the navigation graph.
```
    NavDestinations(destination = navigateTo.value) {
        scopedComposable("login") {
            val loginDependencyComponent = //...
            Login(loginDependencyComponent)
        }
        scopedComposable("dashboard") {
            val dashboardDependencyComponent = //...
            Dashboard(dashboardDependencyComponent)
        }
        scopedComposable("details") {
            val detailsDependencyComponent = //...
            Details(passwordEntryDependencyComponent)
        }
    }
```

## 3. Navigate forth and back
To navigate to a new screen:
```
    navigatorVM.navigate("login")
```

To navigate back in the stack
```
    navigatorVM.navigateBack()
```

## Pass data between screens
To pass data between screens,
```
    navigatorVM.navigate("dashboard") {
        setData(bundleOf("username" to username))
    }

```
```
    navigatorVM.navigateBack(bundleOf("username" to username))
```

## Pop screen(s) from stack
```
    popUntil("login") {
        inclusive = true
    }
```
`popUntil` clears the stack just before the mentioned screen is reached. To also remove the mentioned screen, set `inclusive` to `true`.

## Configuration Changes
The navigation stack is maintained by default after configuration changes.

To maintain the screen state on configuration changes, use the `presenter` extension function to define a presenter for the screen, something like this:
```
    val loginPresenter: LoginPresenter by viewModelStoreOwner.presenter {
        LoginPresenter(//...)
    }
```
This takes care of maintaining the current state even after configuration change.

## Process Death
The navigation stack withstands process death by default.

Checkout the sample `app` module for complete usage.

# License
       Copyright 2023 Srijith

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

              http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.