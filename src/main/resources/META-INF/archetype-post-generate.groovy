def dir = new File(new File(request.outputDirectory), request.artifactId)
def useJavaPlatformModuleSystem = request.getProperties().get("useJavaPlatformModuleSystem")

if (useJavaPlatformModuleSystem.toLowerCase() == "false"){
    println "Deleting module-info.java"
    assert new File("$dir/src/main/java/module-info.java").delete()
}
