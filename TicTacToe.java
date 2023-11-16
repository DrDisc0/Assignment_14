package assignment_14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TicTacToe {
	private static final int BOARD_SIZE = 5;
    private static final int WINNING_COUNT = 5;

    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private boolean playerXTurn = true;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = createBoard();
        Scene scene = new Scene(grid, 300, 300);

        primaryStage.setTitle("Modified TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createBoard() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Button button = createButton(i, j);
                grid.add(button, j, i);
            }
        }

        return grid;
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setMinSize(50, 50);
        button.setOnAction(e -> handleButtonClick(button, row, col));
        return button;
    }

    private void handleButtonClick(Button button, int row, int col) {
        if (board[row][col] == '\u0000') {
            if (playerXTurn) {
                board[row][col] = 'X';
                button.setText("X");
            } else {
                board[row][col] = 'O';
                button.setText("O");
            }

            if (checkWin(row, col)) {
                showWinner(playerXTurn ? "Player X" : "Player O");
                resetGame();
            } else if (isBoardFull()) {
                showDraw();
                resetGame();
            } else {
                playerXTurn = !playerXTurn;
            }
        }
    }

    private boolean checkWin(int row, int col) {
        char currentPlayer = playerXTurn ? 'X' : 'O';

        // Check horizontal
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] != currentPlayer) {
                break;
            }
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        // Check vertical
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] != currentPlayer) {
                break;
            }
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        // Check diagonal
        if (row == col) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] != currentPlayer) {
                    break;
                }
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }

        // Check anti-diagonal
        if (row + col == BOARD_SIZE - 1) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][BOARD_SIZE - 1 - i] != currentPlayer) {
                    break;
                }
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner(String winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winner + " wins!");
        alert.showAndWait();
    }

    private void showDraw() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        alert.showAndWait();
    }

    private void resetGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        playerXTurn = true;

        GridPane newGrid = createBoard();
        Scene scene = new Scene(newGrid, 300, 300);
        Stage stage = (Stage) ((Scene) button.getScene()).getWindow();
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
