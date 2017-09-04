package com.leinardi.android.checkstyle

import com.android.build.gradle.AppPlugin

/*
    Copyright 2017 Roberto Leinardi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.tasks.StopExecutionException

/**
 * A plugin that lets you use the checkstyle plugin for Android projects.
 */
class AndroidCheckstylePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        if (!hasPlugin(project, CheckstylePlugin)) {
            throw new StopExecutionException("Must be applied with 'checkstyle' plugin.")
        }

        def variants
        if (hasPlugin(project, AppPlugin)) {
            variants = project.android.applicationVariants
        } else if (hasPlugin(project, LibraryPlugin)) {
            variants = project.android.libraryVariants
        } else {
            throw new StopExecutionException(
                    "Must be applied with 'android' or 'android-library' plugin.")
        }

        variants.all { variant ->
            def name = variant.name
            def checkstyle = project.tasks.create "checkstyle${name.capitalize()}", Checkstyle
            checkstyle.group = "Verification"
            checkstyle.dependsOn variant.javaCompile
            checkstyle.source variant.javaCompile.source
            checkstyle.classpath = project.fileTree(variant.javaCompile.destinationDir)
            checkstyle.exclude('**/BuildConfig.java')
            checkstyle.exclude('**/R.java')
            checkstyle.showViolations true
            project.tasks.getByName("check").dependsOn checkstyle
        }
    }

    static def hasPlugin(Project project, Class<? extends Plugin> plugin) {
        return project.plugins.hasPlugin(plugin)
    }
}