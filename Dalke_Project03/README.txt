=============================
CSC 214 - Project 03
Chris Dalke
TA: Mariana Kim
=============================

==== Project Description ====
The app I chose to develop is a Personal Running Coach app. The app helps a
user track their progress towards weekly and monthly mileage goals.
The app allows you to set weekly or monthly goals, and you can record
all your runs, which add towards your total goal.
Mileage progress is displayed on the home screen, and the app has Achievements
that you can reach by running certain distances.

When testing my app, you can add runs using the Add interface in the top
corner. You can set a distance and time for your run, and the app will calculate
the pace. You can also add a photo, map location, or weather data to a run.
After saving your run, the run will count towards your weekly and monthly
goals, will display in the Run History tab, and will count towards unlocking
Achievements.

Note: Some of my app's design has changed since the proposal. I decided not
to complete the original idea of a Personal Running Coach, since the idea of
a natural-language chat interface was much more complicated than I initially
understood. Simplifying the project design allowed me to focus on the Add Run
and Achievement interface.

==== Project Requirements ===

-- Basic Features --
My application includes all of the basic features, as required. The app runs
with different layouts in Tablet mode, uses custom theming and complex layouts,
and persists all the data to an SQLite database, among other features.

-- Advanced Features --
My application includes 5 of the advanced features, as required.
I implemented the following features:
-Camera (You can add photos to a Run via the Add Run interface)
-SoundPool (The app plays achievement and run completion sounds)
-Network Connectivity (The app connects to the internet to get weather data)
-Services (I have two background services that load Location and Weather data)
-Google Play Services (The user can add a Location to a run which displays on a map.)

NOTE: In the simulator, to add Locations or Weather to a run, you must
click the Add Location / Add Weather button, and then send GPS data using
the Emulator Extended controls. This will trigger a location update and
download the weather or location data.

========= Files =============

Screenshots of the project are in the /SampleOutput folder.
The Android Studio project is in the /Project03 folder.

====== Academic Honesty =====

I affirm that I did not give or receive any unauthorized help on this project,
and that all work is my own.

==============================
Contact Info:
Email: cdalke@u.rochester.edu
==============================
