#!/bin/sh
exec java -Xmx2048m -cp gradle/wrapper/gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain "$@"
