package org.zig.yazb.services

import org.zig.yazb.YAZbBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(YAZbBundle.message("projectService", project.name))
    }
}
