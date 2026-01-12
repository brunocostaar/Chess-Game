package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Casa;
import pecas.*;

import java.util.ArrayList;
import java.util.Optional;

import static Tabuleiro.Tabuleiro.FEN_POS_INICIAL;
public class ChessGUI extends Application {

    // Constants for visual layout
    private static final int TILE_SIZE = 80; // Size of each square in pixels
    private static final int BOARD_SIZE = 8; // 8x8 Board

    // UI Components
    private GridPane boardGrid; // The grid container for the 64 squares
    private Label turnLabel;    // Displays whose turn it is
    private VBox configPanel;   // The hidden configuration panel
    private TextField fenInput; // Input field for FEN strings
    private RadioButton whiteTurnRadio; // Radio button for White's turn
    private RadioButton blackTurnRadio; // Radio button for Black's turn

    // State Management
    // We store the LOGICAL coordinates of the selected piece (0-7), not the visual ones.
    // This ensures that even if the board is flipped visually, the selection remains correct.
    private Integer selectedLogicCol = null;
    private Integer selectedLogicRow = null;

    // Toggle for auto-rotating the board based on turn
    private boolean autoFlip = false;
    private boolean showConfig = false; // Toggle for showing/hiding config panel
    private boolean gameEnded = false; // Flag to stop interaction when game ends

    @Override
    public void start(Stage primaryStage) {
        // 1. Initialize the Game Logic (Model)
        Tabuleiro.criarCasas(); // Create the internal 8x8 array of 'Casa' objects
        Tabuleiro.preencherCasasToString(); // Helper for algebraic notation (e.g., "a1")
        Tabuleiro.lerFEN(FEN_POS_INICIAL); // Load standard starting position
        refreshGameState(); // Calculate initial legal moves for all pieces

        // 2. Setup the Main Layout (BorderPane)
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root"); // Apply CSS class for background styling

        // --- Header Section ---
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new javafx.geometry.Insets(20));

        // Title and Turn Indicator
        VBox titleBox = new VBox(5);
        titleBox.setAlignment(Pos.CENTER);
        Label title = new Label("Java Chess Engine");
        title.getStyleClass().add("header-label");
        turnLabel = new Label("White's Turn");
        turnLabel.getStyleClass().add("turn-indicator");
        titleBox.getChildren().addAll(title, turnLabel);

        // Config Button (Top Right)
        Button configBtn = new Button("⚙ Config");
        configBtn.setOnAction(e -> toggleConfigPanel());
        
        // Spacer to push config button to the right
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        header.getChildren().addAll(titleBox, spacer, configBtn);
        root.setTop(header);

        // --- Right Side Config Panel (Hidden by default) ---
        configPanel = new VBox(15);
        configPanel.setPadding(new javafx.geometry.Insets(20));
        configPanel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2); -fx-border-color: #475569; -fx-border-width: 0 0 0 1;");
        configPanel.setPrefWidth(300);
        configPanel.setVisible(false);
        configPanel.setManaged(false); // Don't take up space when hidden

        Label configTitle = new Label("Configuration");
        configTitle.getStyleClass().add("sub-header-label");

        // FEN Input
        Label fenLabel = new Label("FEN String:");
        fenInput = new TextField();
        fenInput.setPromptText("Paste FEN here...");
        
        // Turn Selection for FEN
        Label turnSelectLabel = new Label("Active Color:");
        ToggleGroup turnGroup = new ToggleGroup();
        whiteTurnRadio = new RadioButton("White");
        whiteTurnRadio.setToggleGroup(turnGroup);
        whiteTurnRadio.setSelected(true);
        whiteTurnRadio.setStyle("-fx-text-fill: white;");
        
        blackTurnRadio = new RadioButton("Black");
        blackTurnRadio.setToggleGroup(turnGroup);
        blackTurnRadio.setStyle("-fx-text-fill: white;");
        
        HBox turnBox = new HBox(10, whiteTurnRadio, blackTurnRadio);

        Button loadFenBtn = new Button("Load FEN");
        loadFenBtn.setOnAction(e -> loadFEN());

        // Auto-Flip Toggle
        CheckBox autoFlipCheck = new CheckBox("Auto-rotate Board");
        autoFlipCheck.setStyle("-fx-text-fill: white;");
        autoFlipCheck.setOnAction(e -> {
            autoFlip = autoFlipCheck.isSelected();
            renderBoard();
        });

        configPanel.getChildren().addAll(
            configTitle, 
            new Separator(),
            fenLabel, 
            fenInput, 
            turnSelectLabel,
            turnBox,
            loadFenBtn,
            new Separator(),
            autoFlipCheck
        );
        
        root.setRight(configPanel);

        // --- Board Section (Center) ---
        boardGrid = new GridPane();
        boardGrid.getStyleClass().add("chess-board");
        boardGrid.setAlignment(Pos.CENTER);
        renderBoard(); // Draw the initial board state

        root.setCenter(boardGrid);

        // --- Footer Section (Controls) ---
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new javafx.geometry.Insets(20));

        Button resetBtn = new Button("New Game");
        resetBtn.setOnAction(e -> resetGame()); // Hook up the reset button

        footer.getChildren().add(resetBtn);
        root.setBottom(footer);

        // 3. Finalize Scene and Show
        Scene scene = new Scene(root, 1100, 900); // Increased width to accommodate config panel
        // Load the external CSS file
        scene.getStylesheets().add(getClass().getResource("chess-gui.css").toExternalForm());

        primaryStage.setTitle("Java Chess - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleConfigPanel() {
        showConfig = !showConfig;
        configPanel.setVisible(showConfig);
        configPanel.setManaged(showConfig);
    }

    /**
     * Renders the 8x8 grid of squares and pieces based on the current game state.
     * This method is called whenever the board needs to be updated (move made, selection changed, board flipped).
     */
    private void renderBoard() {
        boardGrid.getChildren().clear(); // Remove all existing squares/pieces

        // Determine if the board should be visually flipped (Black at bottom)
        // This happens if autoFlip is ON and it is currently Black's turn (odd number of moves).
        boolean isFlipped = autoFlip && (Tabuleiro.getJogadas() % 2 != 0);

        // Prepare list of legal moves for the selected piece (if any)
        ArrayList<Casa> legalMoves = new ArrayList<>();
        if (selectedLogicCol != null && selectedLogicRow != null) {
            Casa selectedCasa = Tabuleiro.getCasa(selectedLogicCol, selectedLogicRow);
            Peca selectedPiece = selectedCasa.getPeca();
            if (selectedPiece != null) {
                legalMoves = selectedPiece.getCasasLegais();
            }
        }

        // Loop through visual rows and columns (0,0 is top-left of the screen)
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                // Create the visual container for the square
                StackPane tile = new StackPane();
                tile.setPrefSize(TILE_SIZE, TILE_SIZE);

                // Color the square (Light vs Dark)
                boolean isLight = (row + col) % 2 == 0;
                tile.getStyleClass().add(isLight ? "light-square" : "dark-square");

                // --- Coordinate Mapping ---
                // We need to map the VISUAL (row, col) to the LOGICAL (fileira, coluna) used by the backend.
                // Backend Logic:
                //   - Fileira 0 = Row 1 (Bottom for White)
                //   - Fileira 7 = Row 8 (Top for White)
                //   - Coluna 0 = A, Coluna 7 = H

                int logicFileira;
                int logicColuna;

                if (isFlipped) {
                    // Black Perspective (Rotated 180 degrees)
                    // Visual Top-Left (0,0) becomes Logic Bottom-Right (H1 -> Col 7, Row 0)
                    // Visual Row 0 -> Logic Fileira 0 (Row 1)
                    // Visual Col 0 -> Logic Coluna 7 (H)
                    logicFileira = row;
                    logicColuna = 7 - col;
                } else {
                    // White Perspective (Standard)
                    // Visual Top-Left (0,0) is Logic Top-Left (A8 -> Col 0, Row 7)
                    // Visual Row 0 -> Logic Fileira 7 (Row 8)
                    logicFileira = 7 - row;
                    logicColuna = col;
                }

                // --- Selection Highlighting ---
                // Check if this square matches the currently selected logical coordinates
                if (selectedLogicCol != null && selectedLogicCol == logicColuna && selectedLogicRow == logicFileira) {
                    tile.getStyleClass().add("selected");
                }

                // --- Legal Move Indicator ---
                // Check if this square is a legal move for the selected piece
                Casa currentCasa = Tabuleiro.getCasa(logicColuna, logicFileira);
                if (legalMoves.contains(currentCasa)) {
                    Circle indicator = new Circle(TILE_SIZE / 6.0);
                    indicator.getStyleClass().add("legal-move-indicator");
                    tile.getChildren().add(indicator);
                }

                // --- Render Piece ---
                Peca peca = currentCasa.getPeca();

                if (peca != null) {
                    Label pieceLabel = new Label(getPieceSymbol(peca));
                    pieceLabel.getStyleClass().add("piece");
                    // Add specific class for White or Black piece styling
                    pieceLabel.getStyleClass().add(peca.getCor() == Tabuleiro.BRANCO ? "white-piece" : "black-piece");
                    tile.getChildren().add(pieceLabel);
                }

                // --- Click Handling ---
                // Capture the logical coordinates for the click handler
                int finalLogicCol = logicColuna;
                int finalLogicRow = logicFileira;
                tile.setOnMouseClicked(e -> handleTileClick(finalLogicCol, finalLogicRow));

                // Add the tile to the grid
                boardGrid.add(tile, col, row);
            }
        }
        updateTurnLabel();
    }

    /**
     * Handles user clicks on the board squares.
     * Implements the "Select -> Move" interaction pattern.
     *
     * @param logicCol The logical column (0-7) of the clicked square.
     * @param logicRow The logical row (0-7) of the clicked square.
     */
    private void handleTileClick(int logicCol, int logicRow) {
        if (gameEnded) return; // Prevent moves if game is over

        // Case 1: No piece is currently selected
        if (selectedLogicCol == null) {
            Casa casa = Tabuleiro.getCasa(logicCol, logicRow);
            // If the user clicked on a piece...
            if (casa.getPeca() != null) {
                // ...and it's that piece's color's turn...
                int currentTurnColor = (Tabuleiro.getJogadas() % 2 == 0) ? Tabuleiro.BRANCO : Tabuleiro.PRETO;
                if (casa.getPeca().getCor() == currentTurnColor) {
                    // ...select it!
                    selectedLogicCol = logicCol;
                    selectedLogicRow = logicRow;
                    renderBoard(); // Re-render to show selection highlight
                }
            }
        }
        // Case 2: A piece is already selected
        else {
            // If the user clicked the SAME square again, deselect it.
            if (selectedLogicCol == logicCol && selectedLogicRow == logicRow) {
                selectedLogicCol = null;
                selectedLogicRow = null;
                renderBoard();
                return;
            }

            // Attempt to move the selected piece to the clicked square.
            
            // Check for promotion BEFORE moving
            char promotionChar = 'q'; // Default
            Casa sourceCasa = Tabuleiro.getCasa(selectedLogicCol, selectedLogicRow);
            Peca piece = sourceCasa.getPeca();
            
            if (piece instanceof Peao) {
                int promotionRow = (piece.getCor() == Tabuleiro.BRANCO) ? Tabuleiro.OITAVA_FILEIRA : Tabuleiro.PRIMEIRA_FILEIRA;
                // Check if the move destination is the promotion row
                if (logicRow == promotionRow) {
                    // It's a promotion move! Ask user.
                    promotionChar = askForPromotion();
                    if (promotionChar == ' ') {
                        // User cancelled
                        selectedLogicCol = null;
                        selectedLogicRow = null;
                        renderBoard();
                        return;
                    }
                }
            }

            // The 'moverPeca' method in Tabuleiro handles validation (is the move legal?).
            Tabuleiro.moverPeca(selectedLogicCol, selectedLogicRow, logicCol, logicRow, promotionChar);

            // CRITICAL: After any move attempt, we must refresh the game state.
            // This recalculates legal moves, checks for check/checkmate, etc.
            refreshGameState();

            // Check for Checkmate or Stalemate
            checkGameOver();

            // Deselect and re-render the board to show the new state.
            selectedLogicCol = null;
            selectedLogicRow = null;
            renderBoard();
        }
    }
    
    private char askForPromotion() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Queen", "Queen", "Rook", "Bishop", "Knight");
        dialog.setTitle("Pawn Promotion");
        dialog.setHeaderText("Select a piece for promotion:");
        dialog.setContentText("Piece:");
        
        // Apply CSS to the dialog
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("chess-gui.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String choice = result.get();
            switch (choice) {
                case "Rook": return 'r';
                case "Bishop": return 'b';
                case "Knight": return 'n';
                case "Queen": 
                default: return 'q';
            }
        }
        return ' '; // Cancelled
    }

    /**
     * Refreshes the internal game logic state.
     * This mirrors the logic found in the CLI 'Main.java' loop.
     * It is essential to call this after every move so that pieces know their legal moves
     * for the next turn.
     */
    private void refreshGameState() {
        Tabuleiro.clearCasasLegais();       // Clear old legal moves
        Tabuleiro.clearCasasDeBloqueio();   // Clear old blocking squares
        Tabuleiro.clearPecasAtacantes();    // Reset attacker counts
        Tabuleiro.clearIsAtacked();         // Reset attacked status of squares

        Tabuleiro.refreshCasasLegais();     // Calculate raw legal moves for all pieces
        Tabuleiro.refreshIsInCheck();       // Check if Kings are in check
        Tabuleiro.refreshFiltroCasasLegais(); // Filter moves that would leave King in check
        Tabuleiro.refreshCravaPecas();      // Handle pinned pieces
        Tabuleiro.uniteCasasLegais();       // Aggregate all legal moves for white/black
    }

    private void checkGameOver() {
        // Debugging output to console
        System.out.println("Checking Game Over...");
        System.out.println("White King Checkmated: " + Tabuleiro.getReiBranco().isCheckmated());
        System.out.println("Black King Checkmated: " + Tabuleiro.getReiPreto().isCheckmated());
        System.out.println("White King Stalemate: " + Tabuleiro.getReiBranco().isStalemate());
        System.out.println("Black King Stalemate: " + Tabuleiro.getReiPreto().isStalemate());
        System.out.println("Black King In Check: " + Tabuleiro.getReiPreto().isInCheck());
        System.out.println("Black Legal Moves Count: " + Tabuleiro.casasLegaisPecasPretas.size());

        if (Tabuleiro.getReiBranco().isCheckmated()) {
            gameEnded = true;
            showAlert("Game Over", "Black wins by Checkmate!");
        } else if (Tabuleiro.getReiPreto().isCheckmated()) {
            gameEnded = true;
            showAlert("Game Over", "White wins by Checkmate!");
        } else if (Tabuleiro.getReiBranco().isStalemate() || Tabuleiro.getReiPreto().isStalemate()) {
            gameEnded = true;
            showAlert("Game Over", "Draw by Stalemate!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Apply CSS to the alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("chess-gui.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");

        alert.showAndWait();
    }

    /**
     * Resets the game to the starting position.
     */
    private void resetGame() {
        gameEnded = false; // Reset game over flag
        Tabuleiro.limpar(); // Clear the board array
        Tabuleiro.setJogadas(0); // Reset the move counter to 0 (White's turn)
        Tabuleiro.lerFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"); // Reload start FEN
        refreshGameState(); // Recalculate state

        selectedLogicCol = null;
        selectedLogicRow = null;
        renderBoard();
    }

    /**
     * Loads a game state from a FEN string entered by the user.
     */
    private void loadFEN() {
        String fen = fenInput.getText().trim();
        if (fen.isEmpty()) {
            return;
        }

        int turnColor = whiteTurnRadio.isSelected() ? Tabuleiro.BRANCO : Tabuleiro.PRETO;

        Tabuleiro.limpar();
        // Use the new overloaded lerFEN method that accepts the turn color
        Tabuleiro.lerFEN(fen, turnColor);
        
        gameEnded = false; // Reset game over flag
        refreshGameState();
        checkGameOver(); // Check if the loaded position is already checkmate/stalemate

        selectedLogicCol = null;
        selectedLogicRow = null;
        renderBoard();
    }

    /**
     * Updates the text label indicating whose turn it is.
     */
    private void updateTurnLabel() {
        if (gameEnded) {
            turnLabel.setText("Game Over");
            return;
        }
        boolean whiteTurn = Tabuleiro.getJogadas() % 2 == 0;
        turnLabel.setText(whiteTurn ? "White's Turn" : "Black's Turn");
    }

    /**
     * Helper method to get the Unicode character for a given piece.
     */
    private String getPieceSymbol(Peca p) {
        if (p instanceof Torre)
            return "♜";
        if (p instanceof Cavalo)
            return "♞";
        if (p instanceof Bispo)
            return "♝";
        if (p instanceof Rainha)
            return "♛";
        if (p instanceof Rei)
            return "♚";
        if (p instanceof Peao)
            return "♟";
        return "?";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
