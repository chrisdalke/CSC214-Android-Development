=============================
CSC 214 - Assignment 09
Chris Dalke
TA: Mariana Kim
=============================

====== Lab Description ======

For this assignment, the goal was to implement a multithreaded application
using both AsyncTask and HandlerThread. I completed all the parts of the assignment.

One of the clear limitations of AsyncTask is that it only executes a single
task at a time, despite performing the calculations asynchronously. This means
that if you start two tasks, the task you started first will always finish first;
the task list is a queue.

In contrast, the HandlerThread system is harder to setup but does not have
the limitation that it can only run a single task at one time. Because of that,
HandlerThread is the preferred method.

To implement the third activity, I chose to use AsyncTask instead of HandlerThread.
The reason I decided to do this was that loading the image is not something that
should have multiple tasks occurring at once; the system can only be loading a
single image at a time. Therefore, there is no advantage to using HandlerThread
over AsyncTask since we are only using one task at a time anyway.

========= Files ======

The project for this assignment is contained in the /Assignment09 folder.
The screenshots and logs for this project are in the /SampleOutput folder.

=============================
Contact Info:
Email: cdalke@u.rochester.edu
=============================
