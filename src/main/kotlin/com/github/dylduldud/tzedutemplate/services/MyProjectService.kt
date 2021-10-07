package com.github.dylduldud.tzedutemplate.services

import com.intellij.openapi.project.Project
import com.github.dylduldud.tzedutemplate.MyBundle
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectService : ProjectManagerListener {
    override fun projectOpened(project: Project) {
        projectInstance = project
        project.getService(MyProjectService::class.java)
    }

    override fun projectClosing(project: Project) {
        projectInstance = null
        super.projectClosing(project)
    }

    companion object {
        var projectInstance: Project? = null
    }
}
