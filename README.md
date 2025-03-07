An Android application to get data from a json object of books. This is an application built to satisfy a coding exercise given by Vaxcare. The UI is built using Compose and retrofit is used to fetch data from an api. The application requires an internet connection to fetch the data.

## Android Studio setup
- The project was built using default Android Studio "Jellyfish" settings.
- Gradle JDK is set to GRADLE_LOCAL_JAVA_HOME which is JetBrains Runtime 21.0.4 - aarch64
- Gradle Version is 8.10.2
- Project was built to target SDK 35 with a minimum of 33

## Build Steps
- Clone this github repository
- In Android Studio go to File -> New -> Import Project
- Select the github project
- In SDK manager ensure that API 33 "Android 13.0" is downloaded
- Make sure to sync gradle before trying to build

## Build Options
- This project works for any Android device
- If using a Virtual Device in Android Studio ensure that the device has API 33 downloaded
- If using a physical device ensure that it is running on Android 13 "Tiramisu"
- Select the running device you want to target from the dropdown menu near the play button at the top
- Hit the play button to run and build the project

## Application Function
- The application will launch to a single activity that has a list of books and whether or not they are on the shelf
- Pressing on one of the books will bring up a detail view of the book displaying all information about the book and when it was edited
- The books are cached in the background using Room DB
