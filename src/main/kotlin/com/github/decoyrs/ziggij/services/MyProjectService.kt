package com.github.decoyrs.ziggij.services

import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(ZiggIjBundle.message("projectService", project.name))
    }
}
