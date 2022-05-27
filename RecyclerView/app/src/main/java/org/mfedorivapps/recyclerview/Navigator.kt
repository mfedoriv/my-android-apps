package org.mfedorivapps.recyclerview

import org.mfedorivapps.recyclerview.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)
}