# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses

-keepattributes Exceptions

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep class com.delivery.delivery.model.** { *; }

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#Dagger
-dontwarn com.google.errorprone.annotations.**

#Okhttp3
-dontwarn com.squareup.okhttp.*
-dontwarn okhttp3.**
-dontwarn okio.**

#Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#Glide
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}