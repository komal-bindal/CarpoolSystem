package com.example.carpoolsystem

import android.app.Application
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mmi.services.account.MapmyIndiaAccountManager

class CarPoolApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MapmyIndiaAccountManager.getInstance().restAPIKey = getRestAPIKey()
        MapmyIndiaAccountManager.getInstance().mapSDKKey = getMapSDKKey()
        MapmyIndiaAccountManager.getInstance().atlasClientId = getAtlasClientId()
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = getAtlasClientSecret()
        MapmyIndia.getInstance(this)
    }

    fun getAtlasClientId(): String? {
        return ""
    }

    fun getAtlasClientSecret(): String? {
        return ""
    }

    fun getMapSDKKey(): String? {
        return ""
    }

    fun getRestAPIKey(): String? {
        return ""
    }

}