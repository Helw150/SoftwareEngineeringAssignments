# Interaction Description
### Disclaimer
Although each game has it's own onClick function, this is only to work around the fact that we cannot bundle game without changing the API(using serializable or a similar class extension) and therefore break the rules of the assignment. The code path that each initialization follows is nearly identical, except for two instances of `new INSERT_GAME_NAME_HERE` in order to instantiate the appropriate game type. Otherwise, the generic Game API is used along the same game path throughout. Because of this, I will only describe the interactions once in generic terms, instead of repeat the same description 3 times.

## Step 1 - App Initialization

When the app is initialized, Android calls `MainActivity.onCreate` with no Bundle.
------------------------------
In response, `MainActivity` loads the layout declared in activity_main.xml.


## Step 2 - Game Selection
Each game has an `onClick` function associated with it, when a button is pressed the associated `onClick` for that game is called.
------------------------------
In response, an instance of the selected game is constructed to get the row and column size and stores the name of the selected game. It passes this information as intent to the `Console` activity class.

## Step 3 - Game Initialization
The `onClick` for the game sends the row, column and game name as intent to `startActivity` console.
------------------------------
In response, the `Console` view creates a `GridLayout` of `Buttons` using the `addView` function, informing the number and size of buttons by the row and column info as well as some Android built-in info about screen size. It then constructs an instance of the selected `Game` (using the name of the game passed with the Intent) and adds the activity as an `Observer` of the game state. Finally, the text displaying the current player is grabbed using the state of the `Game` composed in `Console`.

### Variations between each game
The only differences in each game are the Intent passed by their onClick functions and the type of `Game` constructed inside of `Console.onCreate`. 