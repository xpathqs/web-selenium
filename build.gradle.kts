
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "0.0.7"

plugins {
    kotlin("jvm") version "1.5.0"
    id("org.jetbrains.dokka") version "1.4.32"
    `java-library`
    jacoco
    maven
    `maven-publish`
    signing
}

java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

jacoco {
    toolVersion = "0.8.7"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    api("org.xpathqs:xpathqs-core:[0.0.4,)")
    api("org.xpathqs:xpathqs-driver:[0.0.4,)")
    api("org.xpathqs:xpathqs-web:[0.0.4,)")
    api("org.xpathqs:xpathqs-log:[0.0.1,)")

    api("org.seleniumhq.selenium:selenium-java:3.141.59")
    api("io.github.bonigarcia:webdrivermanager:4.4.1")

    implementation(kotlin("stdlib"))

    implementation("net.sourceforge.nekohtml:nekohtml:1.9.22")

    testImplementation(kotlin("test-junit5"))
    implementation("org.mockito:mockito-core:2.1.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23.1")
}

publishing {
    publications {
        beforeEvaluate {
            signing.sign(this@publications)
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("XpathQS Web Selenium")
                description.set("Framework for the interaction with Selenium WebDriver")
                url.set("https://xpathqs.org/")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        id.set("nachg")
                        name.set("Nikita A. Chegodaev")
                        email.set("nikchgd@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/nachg/xpathqs-web-selenium.git")
                    developerConnection.set("scm:git:ssh://github.com/nachg/xpathqs-web-selenium.git")
                    url.set("https://xpathqs.org/")
                }
            }
            groupId = "org.xpathqs"
            artifactId = "xpathqs-web-selenium"

            from(components["java"])
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                username = project.property("ossrhUsername").toString()
                password = project.property("ossrhPassword").toString()
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = true
    }
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets {
        configureEach {
            samples.from("src/test/kotlin/org/xpathqs/web/selenium", "src/main/kotlin/org/xpathqs/web/selenium")
        }
    }
}