package dev.srijith.sample.composenavigations

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dev.srijith.composenavigations.NavigationViewModel
import dev.srijith.composenavigations.navigator
import dev.srijith.composenavigations.scopedpresenter.presenter
import dev.srijith.sample.composenavigations.navigation.NavigatorPresenter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigatorVM: NavigationViewModel = navigator()
            val navigatorPresenter by presenter {
                NavigatorPresenter("login", navigatorVM)
            }
            // TODO: Remove this check so that this could be handled from the presenter.
            if (savedInstanceState == null) {
                navigatorPresenter.onAppStart()
            }
            MainComponent(navigatorPresenter)
        }
    }
}