# Main Activity

## Extends

`MainActivity` extends the built-in Android class `AppCompatActivity`.

## Composes
Briefly, `MainActivity` composes Game, but only to call the function to change to the Console activity.
`MainActivity` composes the built in Android class Button, but the composition is handled in XML.

## Aggregates
`MainActivity` does not aggregate anything.

# Console

## Extends
`Console` extends the built-in Android class `AppCompatActivity`.
`Console` also extends(implements) the built in Java class `Observer`.

## Composes
`Console` composes the Game class from `/boardgames/`.
`Console` composes the built-in Android class GridLayout.
`Console` composes the built-in Android class Button.
`Console` composes the built-in Android class TextView.

## Aggregates
`Console` does not aggregate anything.