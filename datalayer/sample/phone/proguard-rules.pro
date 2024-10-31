# https://issuetracker.google.com/issues/144631039
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite { <fields>; }

-if class androidx.credentials.CredentialManager
-keep class androidx.credentials.playservices.** {
  *;
}