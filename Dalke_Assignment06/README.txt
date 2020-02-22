=============================
CSC 214 - Assignment 06
Chris Dalke
TA: Mariana Kim
=============================

====== Lab Description ======

For this assignment, we were tasked with configuring List View and
Recycler View widgets, which provide different ways of displaying collections
of objects. I used video games as my objects.

To see the different types of views, swipe horizontally. I've included
a basic list view which only shows some information, a custom list view
which displays more detailed information in a custom layout, and the recycler
view, which also uses a custom layout. All three lists will display dialogs
with additional info for any of the items if clicked.
The current type of list widget is displayed in the title of the page.

Note: On the simulator it's really hard to swipe between the views,
but that seems to be because of the frame rate lag and not because of
my implementation.

ADVANTAGES OF RECYCLER VIEW OVER LIST VIEW:
The recycler view can be customized with any type of layout, as opposed
to the list view which hardcodes a vertical list layout. The recyclerView
gives you a much more powerful level of control over the widget and can
be customized to fit almost any organization of the data.
It forces programmers to keep their code clean by using the ViewHolder structure.

======= Extra Credit ========

For extra credit, I added a third fragment: a custom list view.
This uses the ListView, but shows the potential for configuring ListView
to use a custom View Adapter. Recycler View is still the better option,
but I thought it would be useful to show all options.

========= Files ======

The project for this assignment is contained in the /Assignment06 folder.
The screenshots and logs for this project are in the /SampleOutput folder.

=============================
Contact Info:
Email: cdalke@u.rochester.edu
=============================
