import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.id

val protobufVersion = "3.21.12"
plugins {
    kotlin("jvm") version "1.8.0"
    id("com.google.protobuf") version "0.9.2"
}

group = "com.paymentology"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }

    // Enable Kotlin generate
    generateProtoTasks {
        all().forEach{ task ->
            task.builtins {
                id("kotlin")
            }
        }
    }
}
