package com.github.decoyrs.ziggij.services

import com.github.decoyrs.ziggij.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
