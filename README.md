<div align="center">

<H1><strong>♠️♥️ The Four Suits ♦️♣️</strong></H1>
<strong>Your console-based card game simulation</strong> 

<BR> CS 2103 <BR>
Doria, John Vincent <BR>
Asilo, Sofhia Aubrey <BR>
Salem, Jillian Ayesa <BR>

</div>


## ☑️ | Overview
**"The Four Suits"** is a text-based, interactive fiction game designed as a project applying Object-Oriented Programming (OOP) concepts. The player is violently thrust into the "Borderland", a sterile, featureless space, and is told their survival depends on playing a series of death games. The core objective is to successfully complete four distinct challenges, each associated with one of the four card suits (Spades, Hearts, Diamonds, Clubs), by visiting Philippine locations like Tondo, Divisoria, Malacañang, and the MRT. The game tests the player's reflexes, psychological strength, intellect, and strategic skill. Success grants the corresponding card, while failure results in the loss of a Visa (a chance to make a decision right). The ultimate goal is to acquire all four cards and make a final choice: to become a Citizen or to Reject the system.
 
## ➰ | Features
1. <strong>Clubs (♣)</strong>. Test your strategic skill in team-based or cooperative challenges.
2. <strong>Diamonds (♦)</strong>. Prove your logical thinking and wit in battles of intellect.
3. <strong>Hearts (♥)</strong>. Endure the test of psychological strength where you play with the hearts of others.
4. <strong>Spades (♠)</strong>. Survive through decisive action and physical challenges.
5. Game Loop.  Succeed in a game, and you are granted the card. Fail, and you lose one Visa. Lose your last Visa, and the game takes everything.


## ➿ | Project Structure
```
📂 src/
└── 📂 ProjectAyesa/
    ├── ☕ Main.java          
    ├── ☕ Intro.java
    └── ☕ Player.java
    └── 📂 Core/
    |   ├── ☕ Console.java
    |   ├── ☕ Game.java
    |   ├── ☕ Graphics.java
    └── 📂 Games/
        ├── ☕ GamesOfClubs.java
        ├── ☕ GameOfDiamonds.java
        ├── ☕ GameOfHearts.java
        └──  ☕ GameOfSpades.java
```
- `ProjectAyesa` - The main package for the simulation.
-  `Main.java` - Contains the entry point and game loop logic.
-  `Player` - Handles player attributes, survival status, and inventory.

## 🏃‍♀️ | How To Run The Program
Open your terminal in the `src/` folder and run:
```
javac ProjectAyesa/main/*.java
```
Run the program using:
```
java ProjectAyesa.main.Main
```

## 💻 | Object-oriented Principles
### 🎁 Encapsulation
This principle bundles data with the methods that operate on it and restricts direct access to the internal data (hiding the "how").
- Private Fields: The Player class declares fields like username, visas, and cards as private, preventing direct modification from outside classes.
- Controlled Access: Data is accessed and modified through public methods, such as getUsername() (accessor) and updateProfile() (mutator), which ensures data is handled consistently and correctly.
- Internal Logic Hiding: Helper methods, such as getCardNameForSuit(String suit) or addCard(String cardName), are likely private within the Player class to manage the internal state without exposing the specific implementation details to other parts of the application. <BR>

### 🧬 Inheritance
This principle allows a new class (subclass) to acquire the properties and methods of an existing class (superclass), promoting code reuse and establishing an "is-a" relationship.
- Base Class: The Game class serves as the base (parent) class, providing a common structure for all games.
- Subclasses: Specific games like GameOfHearts, GameOfSpades, GameOfClubs, and GameOfDiamonds all use the syntax extends Game.
- Code Reuse: All specific game types automatically inherit common attributes (like gameName or rules) and non-private methods defined in the abstract Game class. <BR>

### 🪄 Abstraction
This principle focuses on showing only essential information to the user while hiding complexity and implementation details.
- Abstract Class: The Game class is declared as public abstract class Game. This means it defines the necessary interface for all games but cannot be instantiated itself.
- Defining the Interface: The abstract Game class likely declares an abstract method like play(). This tells the rest of the program what all games can do (game.play()) without exposing how each game is implemented.
- Focus on 'What': The program interacts with the generic Game type, abstracting away the specific complex logic unique to GameOfHearts or GameOfClubs. <BR>

### 🎭 Polymorphism
This principle allows a single interface (method name) to be used to represent different, specific implementations across a class hierarchy ("many forms").
- Method Overriding: Each specific game class (GameOfHearts, etc.) provides its own unique implementation of the shared method, such as the play() method, overriding the base definition from the Game class.
- Generic Reference: The Main class uses a generic variable type, for example, Game gameToPlay, to hold any specific game object (e.g., new GameOfHearts()).
- Dynamic Behavior: When the code calls a method like gameToPlay.play(), the JVM determines which specific implementation (Hearts, Spades, etc.) to execute at runtime, based on the actual object stored in the gameToPlay variable. <BR>
 
## ✅ | Sample Output
 
## 🤝 | Contributors
<table>
<tr>
    <th> &nbsp; </th>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><img src="static/JohnVincent.jpg" width="150" height="150"> </td>
    <td><strong>John Vincent M. Doria</strong> <br/>
    <a href="https://github.com/JVinceent" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=blue" alt="JVinceent's GitHub">
        </a>
    </td>
    <td>Project Leader</td>
</tr>
<tr>
    <td><img src="static/SofhiaAubrey.jpg" width="150" height="150"> </td>
    <td><strong>Sofhia Aubrey M. Asilo</strong> <br/>
    <a href="https://github.com/asilo-sofhia" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=pink" alt="Sofhia's GitHub">
        </a>
    </td>
    <td>Member</td>
</tr>
<tr>
    <td><img src="static/JillianAyesa.jpg" width="150" height="150"> </td>
    <td><strong>Jillian Ayesa T. Salem</strong> <br/>
    <a href="https://github.com/Jillian-Ayesa" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=purple" alt="Jillian's GitHub">
        </a>
    </td>
    <td>Member</td>
</tr>
</table>

 
### 🫂 | Acknowledgment
We truly thank our instructor for all of the help and support we received in finishing this project.  We also thank our peers and classmates for their support and cooperation during the developing process.


