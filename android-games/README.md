#### Documentation is held within the `documentation` directory

# Android

The objective of this assignment is to port your existing board game
to Android.

## Setup

Please fork this project and [remove all
collaborators](https://help.github.com/articles/removing-a-collaborator-from-a-personal-repository/)
except @jsw7 and @kqm1

## Android Studio

This repository represents a skeleton to get you started. Once cloned,
Android Studio should recognize the directory as one its own, and be
comfortable opening the project. You may get various messages about
library support, but they should be easily fixable by going through
the suggested steps to download what's necessary.

Android Studio has the ability to manage a Git repository natively. If
you decided to use this option please ensure that you are navigating
source that is in your account. Further, please do not let their tools
get in the way of providing quality commit messages.

## Existing game

A directory has been setup in which you should add the model from your
existing board game implementation:

```bash
$> ls -1 app/src/main/java/edu/nyuad/boardgames
Chip.java
Game.java
GameIndexOutOfBoundsException.java
GameStateException.java
```

Note that the files that are there are the bare necessity: the API and
the exception definitions. If you did things correctly for board
games, you should probably only need to add the files in
`src/impl/game` from that project.

Because the directory has changed (from board-games), the packages
assigned to the Java files must change as well. See the package
definitions in the four Java files that are there to get a sense of
what that means; particularly for the files you add.

When adding code, please obey the following:

* All concrete games should have a no argument constructor that sets
  things up. This was implied in the board-games assignment, so for
  most nothing should change.

* Classes that need to be written to help Android interact with your
  model should go in the main Android package. For example, if you
  require a class to translate Android coordinates into row-column
  coordinates for your game, that class should appear:

  ```bash
  $> ls -1 app/src/main/java/edu/nyuad/androidgames
  MainActivity.java
  AndroidToGameTranslator.java
  ```

  The new class should *not* go in the `boardgames` package, nor
  should you alter classes in `boardgames` to make it work. In
  general, additions that your controller requires will make
  [portability](#portability-35-points) difficult.

# Implementation

Android is a "pervasive" framework. As such, there are many ways to do
things. Do your best to follow "best practices" where you can.

## User experience (55 points)

Your app should:

1. Present players with a list of games that are available (Connect
   Four, Complica, Tic-Tac-Toe). A player should be able to select
   that game and play should commence.

2. As was the case with the console game, players should get
   turns. Touches to illegal positions, or to completed games, should
   "fail safely" by not interrupting the overall experience; the game
   certainly shouldn't fail!

   For games that are column based (Connect Four and Complica), your
   model probably prevents chip placement to rows other than the top
   (zero). Maintaining this functionality is fine; however, if you
   feel it makes interaction more natural, you are free to allow
   players to touch any row, and ultimately drop the chip in the
   proper location. If you choose to do this, it is probably best to
   make this change -- a translation really -- in your controller
   (Activity).

3. Keep a running display of the current player.

4. Give some indication that the game is over. A user should be able
   to distinguish between a tie and a win. If the game has a winner,
   the winning player should be displayed.

## Portability (35 points)

Your Android implementation should be able to work with any model that
obeys the Game API. This includes the three games that have been
previously developed, as well as other games the grading staff have
implemented.

## Documentation (10 points)

The intention of the documentation is to give an overview of what
objects exist and how those objects interact. Because there are
several ways to do things in Android, the intention of this
documentation is to provide a quick summary high-level of the design
decisions you have made.

### Class diagram

For each class in `edu.nyuad.androidgames`, denote the classes it
aggregates, composes, and extends. The expressed relationships only
need to be between classes within the `edu.nyuad`, and within the
Android library; documenting that a class composes a List or a String,
for example, is not necessary.

### Interaction diagram

For a given *use case*, describe how the objects in
`edu.nyuad.androidgames` use one another to provide the experience. An
example use case would might be selecting Connect Four: from the time
the user is presented with the initial screen, outline the flow of
object creation and message passing that happens until the user is presented with the Connect Four board (prior to placing their first chip).

Interaction diagrams can be [quite
involved](https://www.tutorialspoint.com/uml/uml_interaction_diagram.htm). Because
we have not formally covered them, however, obeying the strict UML
details is encourage, but not required. Instead, you can use
simplified versions of the same idea -- Head First Android Development
has several such examples (Page 121, 311, 355, and 382), as does Nerd Ranch. Essentially,
the diagram should denote:

1. The objects involved in the use-case.

2. Messages passed between those objects.

3. The ordering of interaction.

## Suggestions

In many ways, the Android SDK is like one big hack fest. This means
that while figuring things out can be tough, the ability to do almost
whatever you want is there. You are free to choose whatever design you
want in implementing your game; however, here are a few suggestions:

* It's probably best to draw the grid for the games using an
  [AdapterView](https://developer.android.com/reference/android/widget/AdapterView.html). The
  advantage is that you point a list of items at the view, and Android
  takes care of drawing them to the screen. There are AdapterView's
  for
  [lists](https://developer.android.com/reference/android/widget/ListView.html)
  and
  [grids](https://developer.android.com/reference/android/widget/GridView.html)
  specifically, and even
  [some](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html)
  that are not necessarily bound to a layout "flow." Chapter 8 or 9
  (depending on your edition) of Android Programming: Big Nerd Ranch
  Guide (Big Nerd) talks about some of these dynamic interfaces.

* Different games can technically be thought of as different
  *activities*. However, a full blown activity could also be seen as
  slightly heavyweight for what you
  need. [Fragments](https://developer.android.com/reference/android/app/Fragment.html)
  are an Android concept that can fill that void. Chapter 9 of Head
  First Android Development (HFAD) does a great job of explaining what
  they are and how they work.

* ... to that point,
  [Intent](https://developer.android.com/reference/android/content/Intent.html)'a
  provide support for passing messages to and from activities (or
  fragments). See Chapter 3 of HFAD or 5 of Big Nerd for more.

Please see the "Course Materials" section of the [class
syllabus](https://github.abudhabi.nyu.edu/pages/jsw7/cs209/syllabus/)
for more information/resources on the books mentioned above.

## Working with Git

You should continue to work with Git extensively. As previously
mentioned, Android Studio provides native support; but you can also
continue to work from the command line if you choose. Note that
Android Studio automatically saves files, so before you decide to make
wild changes, you may want to make a checkpoint commit.

Questions or discussions should take place via issues. Unlike with
previous assignments, where issues were mostly used for problems or
ambiguities, in this case issues can also be as a message board for
ideas. In doing so, please be respectful of academic integrity and do
not share literal solutions.

## Workflow

See the assignment listing in NYU Classes for the due date. We will
grade the code that is on your master branch at the exact deadline.
