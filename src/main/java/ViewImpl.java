import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by spatail on 6/16/16.
 */
public class ViewImpl implements View {

    private GridPane gridPane;
    private TextField userTextField;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private Text error;
    private Button btn;
    private UserService userService;

    public ViewImpl(UserService userService) {
        this.userService = userService;
        setupDialog();
    }

    @Override
    public void setError(String errorMsg) {
        error.setText(errorMsg);
    }

    public void setupDialog() {
        gridPane = new GridPane();
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

        btn.setOnAction((ActionEvent e) -> {
            Contact contact = new Contact(
                    firstNameTextField.getText(), lastNameTextField.getText(), userTextField.getText());
            userService.save(contact, this);
        });

        error = new Text();
        gridPane.add(error, 1, 6);
    }

    public void display(StageAdapter primaryStage) {
        Scene scene = new Scene(gridPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
