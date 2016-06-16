import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

interface StageAdapter {

    void setScene(Scene scene);

    void show();

    void setTitle(String s);
}

class JFXStageAdapter implements StageAdapter {

    private Stage stage;

    public JFXStageAdapter(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void setTitle(String s) {
        stage.setTitle(s);
    }
}

interface ButtonAdapter {

    void setOnAction(EventHandler<ActionEvent> eventHandler);
}

class JFXButtonAdapter implements ButtonAdapter {

    private Button button;

    public JFXButtonAdapter(Button button) {
        this.button = button;
    }

    @Override
    public void setOnAction(EventHandler<ActionEvent> eventHandler) {
        button.setOnAction(eventHandler);
    }
}

public class Main extends Application {

    GridPane gridPane;
    TextField userTextField;
    TextField firstNameTextField;
    private TextField lastNameTextField;
    private Text error;
    private Button btn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startWithStageAdapter(new JFXStageAdapter(primaryStage));
    }

    protected void startWithStageAdapter(StageAdapter primaryStage) {
        primaryStage.setTitle("Testing a Form");
        gridPane = new GridPane();

        setupDialog();

        ButtonAdapter btn = getButtonAdapter();

        btn.setOnAction(getEventHandler());

        Scene scene = new Scene(gridPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected EventHandler<ActionEvent> getEventHandler() {
        return e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = userTextField.getText();

            try {
                BufferedWriter out = null;
                try {
                    FileWriter fstream = new FileWriter("userdata.csv", true);
                    out = new BufferedWriter(fstream);
                    out.write(String.format("\n%s, %s, %s", firstName, lastName, email));

                    // Set the error text
                    error.setText("No Error");
                } catch (IOException ioe) {
                    System.err.println("Error: " + ioe.getMessage());
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };
    }

    protected ButtonAdapter getButtonAdapter() {
        return new JFXButtonAdapter(btn);
    }

    protected void setupDialog() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Create User");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Email:");
        gridPane.add(userName, 0, 1);

        userTextField = new TextField();
        gridPane.add(userTextField, 1, 1);

        Label firstName = new Label("First Name:");
        gridPane.add(firstName, 0, 2);

        firstNameTextField = new TextField();
        gridPane.add(firstNameTextField, 1, 2);

        Label lastName = new Label("Last Name:");
        gridPane.add(lastName, 0, 3);

        lastNameTextField = new TextField();
        gridPane.add(lastNameTextField, 1, 3);

        btn = new Button("Create");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        gridPane.add(hbBtn, 1, 4);

        error = new Text();
        gridPane.add(error, 1, 6);
    }
}
