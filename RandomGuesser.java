
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.awt.*;

/**
 * This is an game that makes the user guess a number
 * that was randomly chosen by the program within a given range
 * of 5 numbers.
 *
 * @author Mitchell Aninyang
 */
public class RandomGuesser extends Application {

    // TODO: Instance Variables for View Components and Model

    Label header;                   //The Header Text "Guess within the range"
    Label scoreKeeperCorrect;       //This tracks the number of correct guesses
    Label scoreKeeperWrong;         //This tracks the number of wrong guesses
    Label cOrW;                     //Tells if the guess is Correct Or Wrong
    Label outOfRangeMessage;        //This tells you when the guess is out of range
    Label range;                    //This is the range specifier
    TextField guess;                //This is where the user inputs his guess
    Button next;                    //This is the next button, where a new range will be generated
    Button check;                   //This is the check button, that checks your answer

    private int corrects = 0;       //Initializing the number of correct guesses
    private int wrongs = 0;         //Initializing the number of wrong guesses

    private int randomNumber1 = (int) (Math.random() * 24);     //smallerNumber
    private int randomNumber2 = (randomNumber1 + 5);            //largerNumber
    private int choiceRandomNumber = (int)(Math.random() * ((randomNumber2 - randomNumber1) + 1)) + randomNumber1;      //this is the randomly generated number
    // TODO: Private Event Handlers and Helper Methods

    /**
     * This is the Event handler that generates new random range,
     * and a new randomly generated number to be guessed.
     * @param e unused
     */
    private void next(ActionEvent e){
            randomNumber1 = (int) (Math.random() * 24);
            randomNumber2 = randomNumber1 + 5;
            choiceRandomNumber = (int)(Math.random() * ((randomNumber2 - randomNumber1) + 1)) + randomNumber1;
            range.setText(String.format("%d - %d", randomNumber1, randomNumber2));
            cOrW.setText("You are...");
            outOfRangeMessage.setText("");
    }

    /**
     * This is the Event handler to check if the users guess is correct.
     * @param e unused
     */
    private void setCheck(ActionEvent e){
        int i = Integer.parseInt(guess.getText());
        //equality check
        if (i == choiceRandomNumber) {
            corrects ++;
            String MessageCorrect = "Corrects: " + corrects;
            String Correct = "CORRECT!";
            outOfRangeMessage.setText("");
            guess.setText("");
            scoreKeeperCorrect.setText(MessageCorrect);
            cOrW.setText(Correct);
        }
        //out of range check
        else if (randomNumber1 > i || randomNumber2 < i){
            String outOfRange = "Your Guess is out of range";
            guess.setText("");
            outOfRangeMessage.setText(outOfRange);
        }
        //inequality check
        else {
            wrongs++;
            String Message = "Wrongs: " + wrongs;
            String Wrong = "WRONG!";
            outOfRangeMessage.setText("");
            guess.setText("");
            scoreKeeperWrong.setText(Message);
            cOrW.setText(Wrong);
        }

    }


    /**
     * This is where I created my components and the model and added event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 350); // set the size here
        stage.setTitle("Random Guesser"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here
        // 1. Create the model
        // 2. Create the GUI components
        guess = new TextField("Guess...");
        next = new Button("Next");
        header = new Label("Guess Within The Range");
        range = new Label(String.format("%d - %d", randomNumber1, randomNumber2));
        scoreKeeperCorrect = new Label("Corrects: " + corrects);
        scoreKeeperWrong = new Label("Wrongs: " + wrongs);
        cOrW = new Label("You are...");
        check = new Button("Check");
        outOfRangeMessage = new Label("");
        // 3. Add components to the root
        root.getChildren().addAll(guess, header, next, range, scoreKeeperCorrect, scoreKeeperWrong, cOrW, check, outOfRangeMessage);
        // 4. Configure the components (colors, fonts, size, location)
        header.relocate(0, 10);
        range.relocate(168, 70);
        guess.relocate(135, 160);
        next.relocate(250, 195);
        check.relocate(134, 195);
        scoreKeeperCorrect.relocate(450, 80);
        scoreKeeperWrong.relocate(450, 150);
        cOrW.relocate(162, 115);
        outOfRangeMessage.relocate(100, 115);


        header.setPrefWidth(scene.getWidth());
        header.setFont(Font.font("Helvetica", 40));
        header.setStyle("-fx-background-color: lightblue; -fx-text-alignment: center; -fx-text-fill: black;");


        outOfRangeMessage.setFont(Font.font("Helvetica", 20));
        outOfRangeMessage.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        range.setFont(Font.font("Helvetica", 30));

        next.setPrefWidth(70);
        next.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

        check.setPrefWidth(70);
        check.setStyle("-fx-background-color: lightblue;");

        scoreKeeperCorrect.setFont(Font.font("Helvetica", 60));
        scoreKeeperWrong.setFont(Font.font("Helvetica", 20));

        cOrW.setFont(Font.font("Helvetica", 20));
        // 5. Add Event Handlers and do final setup
        check.setOnAction(this::setCheck);
        next.setOnAction(this::next);
        // 6. Show the stage
        stage.show();
    }

    /**
     * My Main method
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
