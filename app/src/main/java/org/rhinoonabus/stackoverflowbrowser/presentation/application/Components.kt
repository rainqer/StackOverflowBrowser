package org.rhinoonabus.stackoverflowbrowser.presentation.application

import android.app.Application

object Components {

    fun from(application: Application) = (application as StackOverflowBrowserApplication).component
}
