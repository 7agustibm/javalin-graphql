package com.agustibm.javalin.graphql

import io.javalin.Javalin
import com.agustibm.javalin.graphql.graphql.GraphqlRun

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)

    app.get("/") { it.result("Hello World") }
        .get("/graphql") { ctx -> ctx.contentType("text/html; charset=UTF-8").result(getGraphQLi()) }
        .post("/graphql") { ctx ->
            val query = ctx.bodyAsClass(Map::class.java).get("query").toString()
            ctx.result(GraphqlRun().apply(query))
        }
}

private fun getGraphQLi() = object {}.javaClass.classLoader.getResourceAsStream("index.html")
