package com.sametb.androidaccessibilityservicedemo.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent


/*
* Android Accessibility Service Demo.com.sametb.androidaccessibilityservicedemo.service
* Created by SAMET BAYAT 
* on 14.02.2024 at 7:16 PM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

class MyAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        val packageName = event?.packageName
        val packageManager = this.packageManager

        try {
            val appInfo = packageManager.getApplicationInfo(packageName.toString(), 0)
            val appName = packageManager.getApplicationLabel(appInfo).toString()
            Log.i("MyAccessibilityService", "onAccessibilityEvent: $appName")
        }catch (e: Exception){
            Log.e("MyAccessibilityService", "onAccessibilityEvent: ${e.message}")
        }
    }

    override fun onInterrupt() {
        Log.e("MyAccessibilityService", "onInterrupt: Service is interrupted")
    }


    override fun onServiceConnected() {

        val info = AccessibilityServiceInfo()

        info.apply {
            // Set the type of events that this service wants to listen to. Others
            // aren't passed to this service.
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED

            // If you only want this service to work with specific apps, set their
            // package names here. Otherwise, when the service is activated, it
            // listens to events from all apps.
//            packageNames = arrayOf("com.example.android.myFirstApp", "com.example.android.mySecondApp")

            // Set the type of feedback your service provides.
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            // Default services are invoked only if no package-specific services are
            // present for the type of AccessibilityEvent generated. This service is
            // app-specific, so the flag isn't necessary. For a general-purpose
            // service, consider setting the DEFAULT flag.

             flags = AccessibilityServiceInfo.DEFAULT;

            notificationTimeout = 100
        }

        this.serviceInfo = info
        Log.i("MyAccessibilityService", "onServiceConnected: Service is connected")

    }

}

