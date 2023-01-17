package dev.srijith.sample.composenavigations

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter
import dev.srijith.sample.composenavigations.presenterlib.presenter
import dev.srijith.composenavigations.NavigationViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigatorVM: NavigationViewModel by viewModels {
                viewModelFactory {
                    addInitializer(NavigationViewModel::class) {
                        NavigationViewModel()
                    }
                }
            }
            navigatorVM.enableOnBackPressedCallback(onBackPressedDispatcher)
            val navigatorPresenter by presenter {
                NavigatorPresenter("dashboard", navigatorVM)
            }
            MainComponent(navigatorPresenter)

//            val context = LocalContext.current
//            DisposableEffect(context) {
//                onDispose {
//                    scopedViewModelContainer.setChangingConfigurationState(context.findActivity().isChangingConfigurations)
//                }
//            }
//
//            // Observe general lifecycle events (resume, pause, destroy, etc.)
//            val lifecycle = LocalLifecycleOwner.current.lifecycle
//            // Use LaunchedEffect to make sure we have a coroutine scope to run on main-thread
//            // and to add the observer again every time the lifecycle or the ScopedViewModelContainer change
//            LaunchedEffect(lifecycle, scopedViewModelContainer) {
//                launch(Dispatchers.Main) {
//                    lifecycle.addObserver(scopedViewModelContainer)
//                }
//            }
        }
    }
}