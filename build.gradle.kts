import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.3.20"
    id("com.github.johnrengelman.shadow") version "2.0.4"
    application
}

group = "com.agustibm.javalin.graphql"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compile("io.javalin:javalin:2.6.0")
    compile("com.authzee.kotlinguice4:kotlin-guice:1.2.0")
    compile("com.authzee.kotlinguice4:kotlin-guice-multibindings:1.2.0")
    compile("com.expedia:graphql-kotlin:0.1.0")
    compile( "com.graphql-java:graphql-java:11.0")
    compile( "org.slf4j:slf4j-simple:1.7.25")
    compile("com.google.code.gson:gson:2.8.5")
}

tasks.withType<ShadowJar> {

    manifest.attributes.apply {
        put("Implementation-Title", "Hoop Backend")
        put("Implementation-Version", version)
        put("Main-Class", "com.agustibm.javalin.graphql.MainKt")
    }

}

application {
    mainClassName = "com.agustibm.javalin.graphql.MainKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}