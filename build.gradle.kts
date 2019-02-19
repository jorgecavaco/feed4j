plugins {
    `java-library`
}

dependencies {

    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.guava:guava:23.0")
    implementation("dom4j:dom4j:1.6.1")
    implementation("org.cyberneko:com.springsource.org.cyberneko.html:0.9.5")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

repositories {
    jcenter()
}
