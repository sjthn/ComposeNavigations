package dev.srijith.sample.composenavigations

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.srijith.composenavigations.NavigationViewModel
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

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
                NavigatorPresenter("login", navigatorVM)
            }
            MainComponent(navigatorPresenter)
        }
    }
}