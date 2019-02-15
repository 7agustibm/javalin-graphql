package com.agustibm.javalin.graphql.serverless

import com.authzee.kotlinguice4.KotlinModule
import com.authzee.kotlinguice4.multibindings.KotlinMultibinder

class ServerlessModule: KotlinModule() {
    override fun configure() {
        val multibinder = KotlinMultibinder.newSetBinder<Query>(kotlinBinder)
        multibinder.addBinding().to<HelloQuery>()
    }
}