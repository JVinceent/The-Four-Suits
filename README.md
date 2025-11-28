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
[Pending Update: Describe which classes use private fields (e.g., Player health, Card rank) and how getters/setters are used.]
### 🧬 Inheritance
[Pending Update: Describe how classes inherit from one another (e.g., does HeartsGame inherit from a Game parent class?)]
### 🪄 Abstraction
[Pending Update: Describe if you used Abstract classes or Interfaces for the different Suit types.]
### 🎭 Polymorphism
[Pending Update: Describe how methods are overridden or overloaded to handle different game rules for each Suit.]
 
## ✅ | Sample Output
 
## 🤝 | Contributors
<table>
<tr>
    <th> &nbsp; </th>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><img src="static/marieemoiselle.JPG" width="100" height="100"> </td>
    <td><strong>John Vincent M. Doria</strong> <br/>
    <a href="https://github.com/JVinceent" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=blue" alt="JVinceent's GitHub">
        </a>
    </td>
    <td>Project Leader</td>
</tr>
<tr>
    <td><img src="static/jeisquared.jpg" width="100" height="100"> </td>
    <td><strong>Sofhia Aubrey M. Asilo</strong> <br/>
    <a href="https://github.com/asilo-sofhia" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=pink" alt="Sofhia's GitHub">
        </a>
    </td>
    <td>Member</td>
</tr>
<tr>
    <td><img src="static/renzmarrion.jpg" width="100" height="100"> </td>
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


