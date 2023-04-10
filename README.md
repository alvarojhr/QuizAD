# Blackjack Game (MVC Approach)

This project is a Java-based implementation of the popular casino card game, Blackjack. The game uses the Model-View-Controller (MVC) design pattern, and it allows players to bet, hit, stand, and save their progress to continue later.

## Features

- Play with a standard 52-card deck.
- Start with an initial balance of 1000 coins.
- Place bets ranging from 10 coins to an all-in.
- Save and load game progress using a local JSON file.

## Prerequisites

- Java SE 17 or later

## Installation

1. Clone the repository or download the source code.
2. Import the project into your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Add the Gson library to the project: https://github.com/google/gson
4. Compile and run the `Main` class to start the game.

## How to Play

1. Run the application.
2. Enter your name to create a new game or continue with a saved game.
3. Place your bet for each round (minimum: 10 coins).
4. Two cards will be dealt to both you and the dealer.
5. Choose to hit (receive more cards) or stand (stop receiving cards).
6. The dealer will play their turn, hitting until they reach 17 or more.
7. The round winner will be determined based on the hand values.
8. If you run out of coins, the game will end.

## Saving and Loading the Game

The game progress is automatically saved to a local JSON file named `game_save.json`. When you run the application, it will load the saved game if the file exists. If you want to start a new game, delete or rename the `game_save.json` file.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT](https://choosealicense.com/licenses/mit/)
