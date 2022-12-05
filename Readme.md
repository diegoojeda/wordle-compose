# Wordle implementation with Jetpack Compose

This repository contains a custom implementation of the world famous game Wordle, made with Jetpack
Compose for the UI layer and providing a Ktor embedded server that will provide a different word
every time the user wants to play the game.

## Jetpack Compose

On the UI layer, I've decided to use Jetpack Compose along with the new Material Design 3 library.
Most of the code related to this can be found in the `MainActivity` class.

Regarding how I've handled recomposition, I've gone with the

```  
interface State<out T> {
  val value: T
}  
```  

approach, but any other approach such as LiveData and Flow can be used in similar ways.

To trigger recomposition, you'll find that I had to recreate the states inside each of the domain
entities by `copy`ing each of the lists that were modified, as if you just modify the items inside
of them the recomposition doesn't trigger.

Other than that, it's interesting to take a look at the `LetterBox` component, as it holds both the
animation and the logic to decide which color should the box be painted.

## Domain Layer

At first sight, the game's logic seems quite simple but once we get into it we find out it has some
cases we need to handle.

The representation of the board is made through three different entities:

- `BoardState`
- `BoardRow`
- `BoardLetter`

Each one of those have the proper methods to modify its contents in the way the rules of the game
allow, and have convenience methods to perform the needed assertions, such as what's the state of a
letter, if the row has been completed or if the word has been guessed.

## Ktor server

Last but not least, in order to provide the words that the user will have to guess, I added a server
implemented with Ktor that runs along with the application on your device when it's launched.

This server has only one endpoint `/next_word` that returns a random word from a list of 700+ most
used words.

This list is duplicated on both the server and the client, as the client needs to check if a word is
correct before checking if its letters match.

This duplication could be removed by adding an endpoint to check if the word is valid, but I didn't
find it necessary at this stage of the development, as it's not a trivial decision because it would
make us impossible to go for an offline mode.

## CI - Bitrise

A Bitrise CI has been created for this project. Even if the `bitrise.yml` file provided is the one
that is created out of the box, it's interesting to always have a pipeline running with every push
so that you get notified as soon as possible in case you break some tests.

The app also gets published to the bitrise distribution servers so that you can share it easily.

## Improvements

- Implement navigation with any given number of different libraries to benchmark which one fits your
  needs better.
- Remove the duplication of the words list on the server and client applications.
- The animation of the cells when the user clicks `Send` on the keyboard can be improved. Right now
  the cells are painted at the same time as the animation is started, and they should be when the
  rotated box starts to show. This can be done by duplicating the box component and making them
  trigger different recompositions.
- An offline mode would be great, there's already a `LocalWordsRepository` in place that would make
  its implementation trivial.

### Side notes

There are two dictionaries on the app.

The one on the client is a list of every single 5 letter word that exists in spanish.

This one has been created out of a full spanish dictionary (`es.txt` file), by removing the words
that don't have exactly 5 letters with

```grep -o -w '\w\{5,5\}' es.txt```

After that, we take the output of the above command and convert it to a valid JSON format with `jq`
such as

```jq --raw-input . | jq --slurp . ```

This way we have a Json Array with every single 5 letter word in spanish.

The second one that is on the server is a list of the most used words in spanish with 5 letters.

We need two dictionaries because we want the users to be able to type any valid word they want in
the application, but we want the words to be guessed to not be rare ones, such as many of the ones
we can find on `dictionary-es.json`.