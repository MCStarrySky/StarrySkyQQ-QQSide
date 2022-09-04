import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    `maven-publish`
    //id("io.izzel.taboolib") version "1.42"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
    //id("net.mamoe.mirai-console") version "2.7.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

val taboolibVersion = "6.0.9-77"

repositories {
    mavenCentral()
    maven("https://repo.tabooproject.org/repository/releases")
}

dependencies {
    //implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly(fileTree("libs"))

    implementation("net.mamoe:mirai-core-api:2.7.1")
    implementation("net.mamoe:mirai-console:2.7.1")

    implementation("com.electronwill.night-config:core:3.6.5")
    implementation("org.yaml:snakeyaml:1.28")

    implementation("com.zaxxer:HikariCP:4.0.3")

    implementation("io.izzel:taboolib:${taboolibVersion}:common")
    implementation("io.izzel:taboolib:${taboolibVersion}:common-5")
    implementation("io.izzel:taboolib:${taboolibVersion}:module-configuration")
    implementation("io.izzel:taboolib:${taboolibVersion}:module-database")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<ShadowJar> {
    relocate("com.zaxxer.hikari.", "com.zaxxer.hikari_4_0_3.")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            groupId = project.group.toString()
        }
    }
}