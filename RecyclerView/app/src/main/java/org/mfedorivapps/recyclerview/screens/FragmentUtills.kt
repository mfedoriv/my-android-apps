package org.mfedorivapps.recyclerview.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.mfedorivapps.recyclerview.App
import org.mfedorivapps.recyclerview.Navigator

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            UsersListViewModel::class.java -> {
                UsersListViewModel(app.usersService)
            }
            // add here new ViewModel if it have custom constructor -> else don't add
            UserDetailsViewModel::class.java -> {
                UserDetailsViewModel(app.usersService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator