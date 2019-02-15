package com.agustibm.javalin.graphql.graphql

import com.expedia.graphql.SchemaGeneratorConfig
import com.expedia.graphql.TopLevelObject
import com.expedia.graphql.toSchema
import com.google.gson.Gson
import com.authzee.kotlinguice4.getInstance
import com.google.inject.Guice

import graphql.GraphQL
import com.agustibm.javalin.graphql.serverless.Query

import java.util.function.Function

import com.agustibm.javalin.graphql.serverless.ServerlessModule
import kotlin.streams.toList


class GraphqlRun : Function<String, String> {

    override fun apply(query: String): String {
        val injector = Guice.createInjector(ServerlessModule())
        val config = SchemaGeneratorConfig(listOf("org.example"))
        val mutations: List<TopLevelObject> = ArrayList()

        val queries: List<TopLevelObject> = injector.getInstance<Set<Query>>().stream().map { TopLevelObject(it) }.toList()

        val graphQLSchema = toSchema(queries, mutations, config)
        val build = GraphQL.newGraphQL(graphQLSchema).build()
        val toSpecification = build.execute(query).toSpecification()
        return Gson().toJson(toSpecification)
    }

}
