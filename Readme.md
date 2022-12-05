# Wordle implementation with Jetpack Compose

This repository contains a custom implementation of the world famous game Wordle, made with Jetpack Compose for the UI layer and providing a Ktor embedded server that will provide a different word every time the user wants to play the game.

## Jetpack Compose

On the UI layer, I've decided to use Jetpack Compose along with the new Material Design 3 library. Most of the code related to this can be found in the `MainActivity` class.

Regarding how I've handled recomposition, I've gone with the
```
interface State<out T> {  
    val value: T  
}
```
approach, but any other approach such as LiveData and Flow can be used in similar ways.

To trigger recomposition, you'll find that I had to recreate the states inside each of the domain entities by `copy`ing each of the lists that were modified, as if you just modify the items inside of them the recomposition doesn't trigger.

Other than that, it's interesting to take a look at the `LetterBox` component, as it holds both the animation and the logic to decide which color should the box be painted.

## Domain Layer

At first sight, the game's logic seems quite simple but once we get into it we find out it has some cases we need to handle.

The representation of the board is made through three different entities:
- `BoardState`
- `BoardRow`
- `BoardLetter`

Each one of those have the proper methods to modify its contents in the way the rules of the game allow, and have convenience methods to perform the needed assertions, such as what's the state of a letter, if the row has been completed or if the word has been guessed.

## Ktor server

Last but not least, in order to provide the words that the user will have to guess, I added a server implemented with Ktor that runs along with the application on your device when it's launched.

This server has only one endpoint `/next_word` that returns a random word from a list of 700+ most used words.

This list is duplicated on both the server and the client, as the client needs to check if a word is correct before checking if its letters match.

This duplication could be removed by adding an endpoint to check if the word is valid, but I didn't find it necessary at this stage of the development, as it's not a trivial decision because it would make us impossible to go for an offline mode.

## Improvements

- Implement navigation with any given number of different libraries to benchmark which one fits your needs better.
- Remove the duplication of the words list on the server and client applications.
- The animation of the cells when the user clicks `Send` on the keyboard can be improved. Right now the cells are painted at the same time as the animation is started, and they should be when the rotated box starts to show. This can be done by duplicating the box component and making them trigger different recompositions.
- An offline mode would be great, there's already a `LocalWordsRepository` in place that would make its implementation trivial.


### Side notes
The very first approach for getting the words was to use a whole Spanish dictionary (that can be found inside the `es.txt` file), and remove the words that don't have exactly 5 letters with

```grep -o -w '\w\{5,5\}' es.txt```

After that, we could take the output of the above command and convert it to a valid JSON format with `jq` such as
```jq  --raw-input .  | jq --slurp . ```

The problem with this approach is that there were too many words, a lot of them quite hard to guess because they're not being used really often, so I ended up searching for a list of the most used words in Spanish and taking only the ones with 5 letters on them.