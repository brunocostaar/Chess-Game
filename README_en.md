# ♟️ Chess Game in Java

-----

### [🇬🇧/🇺🇸 English Version](https://github.com/xopxee/Chess-Game/blob/main/README_en.md)

-----

## 📜 Summary

* Features
* Technologies Used
* How to Run the Project
* Project Structure
* License

-----

## ✨ Features

* **Board Structure:** Complete representation of an 8x8 chessboard.

* **Object-Oriented Design:** Each piece (Pawn, Rook, Knight, Bishop, Queen, King) is modeled as a distinct class, inheriting from a base class `Piece`, which promotes code reuse and maintenance.

* **Complete Movement:** Movement and capture logic for all pieces, according to their fundamental rules.

* **Complex Rules:** The game features advanced rules such as en passant, castling, check, and pinned pieces.

* **Win or Draw Conditions:** Checkmate, Stalemate, Loss by resignation.

* **Console Interface (Optional):** Displays the current state of the board in the terminal for game visualization.

* **Graphical User Interface (GUI):** Visually interactive user interface using the JavaFX library.

## 🛠️ Technologies Used

Language: Java

Platform: JDK (Java Development Kit) 11 or higher

UI Framework: JavaFX

Dependency Manager: Maven (Only for using JavaFX)

## 🚀 How to Run the Project

To compile and run this project locally, follow the steps below.

### Prerequisites

Before you begin, make sure you have the **Java Development Kit (JDK)** (version 11 or later) installed on your machine.

### Steps for Execution

1. **Clone the repository:**

```bash
git clone https://github.com/xopxee/Chess-Game.git
```

2. **Navigate to the project directory:**

```bash
cd Chess-Game
```

3. **Compile the `.java` files from the project root:**

The command below will compile all the necessary classes, respecting the package structure.

```bash
javac src/**/*.java
```

4. **Run the application:**

After successful compilation, run the main class to start the game in the console.

```bash
java src/Main
```

## 📂 Project Structure

The source code is organized in a way that separates responsibilities, facilitating understanding and maintenance.

```
Chess-Game/
├── .idea/
├── src/
│   ├── gui/
│   │   ├── chess-gui.css
│   │   ├── ChessGUI.java
│   │   └── GuiLauncher.java
│   ├── Tabuleiro/
│   │   ├── Casa.java
│   │   └── Tabuleiro.java
│   ├── pecas/
│   │   ├── Bispo.java
│   │   ├── Cavalo.java
│   │   ├── Peao.java
│   │   ├── Peca.java
│   │   ├── Rainha.java
│   │   ├── Rei.java
│   │   └── Torre.java
│   └── Main.java
├── .gitignore
├── ChessGame.iml
├── LICENSE
├── pom.xml
├── README.md
└── README_en.md
```

### [📂UML Diagram (Dated, will be updated soon)](https://lucid.app/lucidchart/95e617d8-9ed0-4962-9897-b22b88b38569/edit?beaconFlowId=1853CEFB36C7CF9A&invitationId=inv_1e47aab7-1f42-41f4-a381-2b2e6b5ff430&page=HWEp-vi-RSFO#)

## 📄 License

This project is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License). See the archive for more details.

----