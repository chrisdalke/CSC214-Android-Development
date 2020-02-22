=============================
CSC 214 - Project 02
Chris Dalke
TA: Mariana Kim
=============================

==== Project Description ====

For this project, we were tasked with building a small functional social network.
I successfully completed all the pieces of the project, creating Twiddler.
The program completes all of the basic requirements as well as adding a
number of extra features, described in the Extra Credit section.

Some general description of the app and how to use it:
When the app first launches, you will be able to log in, or create a new account.
Creating a new account will ask for some information and then return you
to the login screen.
Once logged in, you can swipe between the Feed, showing all posts by your user
and other accounts you are following, and the Users list, showing all other users in the network.
You can view the profile of any user by clicking their username or the View icon
in the list. The profile shows info about the user, including their metadata,
posts, and who they are following and followed by.

You can view your profile by clicking the Profile button (People icon) at the
top of the activity bar. This will view your profile, where you can click
Edit profile to modify your profile info and add/edit profile pictures.

You can post a new status by clicking the new Post button (Pencil icon) and
writing a post. To include a picture with a post, click the Add Picture button.
The feed will display any posts from you or those you are following in
chronological order.

For my model and database schema, I created three classes:
-"User" is an object that stores all data related to a user account: Username
and password, metadata, user photo etc.
-"Favorite" is an object that stores a "favorite" or a link between a user that
is following another user, and the user that is being followed by the first user.
This
-"Post" is an object that stores all data associated with a post, including the
author, the post timestamp, and any text and image that is given with the post.

======= Extra Credit ========

For extra credit, I completed a number of tasks that were not required:
-Users can view the Following/Followers for other users on their profile
-Users can view the posts from a single user from that user's profile
-Posts can have text and images in the same post
-Nice-looking user interface
-Generalized codebase: I created a common Feed View, User List View, and Profile View,
each of which can be applied to any user object. The same objects used to
display the logged in user's feed and show a list of all users is used to display
a list of posts or followers on a user's profile.
I accomplished this by allowing the view fragments to take in
custom collections of objects and allowed for a filter parameter.

========= Files ============

Screenshots of the project are in the /SampleOutput folder.
The Android Studio project is in the /Project02 folder.

====== Academic Honesty ====

I affirm that I did not give or receive any unauthorized help on this project,
and that all work is my own.

=============================
Contact Info:
Email: cdalke@u.rochester.edu
=============================
