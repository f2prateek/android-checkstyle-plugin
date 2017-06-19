package com.f2prateek.checkstyle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.quality.Checkstyle
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.tasks.StopExecutionException

class AndroidCheckstylePlugin implements Plugin<Project> {
  final Logger log = Logging.getLogger AndroidCheckstylePlugin

  @Override void apply(Project project) {
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