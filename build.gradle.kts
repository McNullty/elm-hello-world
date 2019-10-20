tasks.register<Exec>("build") {
    val jsPath = "$projectDir/build/elm.js"

    inputs.dir("$projectDir/src/")
    outputs.file(file(jsPath))

    workingDir = File("$projectDir/src/")
    commandLine = listOf("elm", "make", "$projectDir/src/main/elm/Main.elm", "--output", jsPath )
}

tasks.register<Exec>("elmLive") {
    workingDir = File("$projectDir/build/")
    commandLine = listOf("elm-live", "src/main/elm/Main.elm", "--pushstate", "--open", "--", "--output=build/elm.js")

    dependsOn("copyResources")
}

tasks.register<Copy>("copyResources") {

    from(File("$projectDir/src/main/resources/"))
    into(File("$projectDir/build/"))
}

tasks.register<Delete>("clean") {
    delete.add("elm/elm-stuff/0.19.0")
    delete.add("elm/elm-stuff/generated-code")
    delete.add("build")
}