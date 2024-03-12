// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.app) version Versions.androidGradlePlugin apply false
    id(Plugins.jetbrainsKotlin) version Versions.kotlin apply false
    id(Plugins.hilt) version Versions.hilt apply false
    id(Plugins.library) version Versions.androidGradlePlugin apply false
    id(Plugins.safeArgs) version Versions.safeVarargs apply false
}