package org.mfedorivapps.recyclerview

import android.app.Application
import org.mfedorivapps.recyclerview.model.UsersService

class App : Application() {
    val usersService = UsersService()
}