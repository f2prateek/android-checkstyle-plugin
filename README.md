Android Checkstyle Plugin
==================

A plugin that lets you use the checkstyle plugin for Android projects.


Usage
-----

Apply the plugin in your `build.gradle` along with the regular 'checkstyle' plugin:
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.leinardi.android:android-checkstyle-plugin:1.0.0'
    }
}

apply plugin: 'com.leinardi.android.checkstyle'

checkstyle {
    ignoreFailures = true // Whether this task will ignore failures and continue running the build.
    configFile rootProject.file('config/checkstyle/checkstyle.xml') // The Checkstyle configuration file to use.
    toolVersion = '8.2' // The version of Checkstyle you want to be used
}
```


License
--------

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
