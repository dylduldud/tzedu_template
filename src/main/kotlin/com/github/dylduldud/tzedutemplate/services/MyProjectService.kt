package com.github.dylduldud.tzedutemplate.services

import com.intellij.openapi.project.Project
import com.github.dylduldud.tzedutemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
