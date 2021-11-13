package com.example.carpoolsystem

import android.app.Application
import com.mapmyindia.sdk.maps.MapmyIndia
import com.mmi.services.account.MapmyIndiaAccountManager

class CarPoolApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    fun initSdk(){
        MapmyIndiaAccountManager.getInstance().restAPIKey = getRestAPIKey()
        MapmyIndiaAccountManager.getInstance().mapSDKKey = getMapSDKKey()
        MapmyIndiaAccountManager.getInstance().atlasClientId = getAtlasClientId()
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = getAtlasClientSecret()
        MapmyIndia.getInstance(this)
    }

    fun getAtlasClientId(): String {
        return "33OkryzDZsIq58WlTyLU_ecT4yrO-dtusZeuXk3IosSf7HdLHZ6WanK4Q4p5FPQ7c44v9ekBn1B8O6dNS6BEyg=="
    }

    fun getAtlasClientSecret(): String {
        return "lrFxI-iSEg8VtXAiiz6kKOlnT19gJ4RCKraYMQflgu6scWg5W9TDvwxFrvcg8WWIpl0UnNUhHZ_i8dq5vqYDpZTH9WF4UPzH"
    }

    fun getMapSDKKey(): String {
        return "3e864ec8a4e10505569c0e3fd80a3204"
    }

    fun getRestAPIKey(): String {
        return "3e864ec8a4e10505569c0e3fd80a3204"
    }

}