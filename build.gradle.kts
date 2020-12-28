plugins {
    java
    `java-library`
    `maven-publish`
    signing
    id("com.github.johnrengelman.shadow") version "6.1.0"

}

group = "me.kingtux"
version = "1.0-SNAPSHOT"
val artifactName = "JDATuxORM"

java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8

}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifactId = artifactName
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(artifactName)
            }
        }
    }
    repositories {
        maven {

            val releasesRepoUrl = uri("https://repo.kingtux.me/storages/maven/tuxjsql")
            val snapshotsRepoUrl = uri("https://repo.kingtux.me/storages/maven/tuxjsql")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)
        }
        mavenLocal()
    }
}


tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
repositories {
    maven("https://repo.maven.apache.org/maven2/")
    mavenLocal()
    jcenter()
    maven("https://repo.kingtux.me/storages/maven/kingtux-repo")
    maven("https://repo.kingtux.me/storages/maven/tuxjsql")

}

dependencies {
    compileOnly("me.kingtux:tuxorm:1.5-SNAPSHOT")
    compileOnly("me.kingtux:tuxjsql:2.2.0-SNAPSHOT")
    compileOnly("net.dv8tion:JDA:4.2.0_224")

}