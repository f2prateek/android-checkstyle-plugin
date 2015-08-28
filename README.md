Android Checkstyle Plugin
==================

A plugin that lets you use the checkstyle plugin for Android projects.


Usage
-----

Apply the plugin in your `build.gradle` along with the regular 'checkstyle' plugin:
```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath 'com.f2prateek.checkstyle:checkstyle:1.0.1'
  }
}

apply plugin: 'checkstyle'
apply plugin: 'com.f2prateek.checkstyle'

checkstyle {
  configFile rootProject.file('gradle/checkstyle.xml') // points to your checkstyle config
}
```





License
--------

    Copyright 2015 Prateek Srivastava

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
