import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserService userService = new UserService();

        Consumer<StageAdapter> viewInitializer = stage -> {
            ViewImpl view = new ViewImpl(userService);
            view.setupDialog();
            view.display(stage);
        };

        startWithStageAdapter(new JFXStageAdapter(primaryStage), viewInitializer);
    }

    protected void startWithStageAdapter(StageAdapter primaryStage, Consumer<StageAdapter> viewInitializer) {
        primaryStage.setTitle("Testing a Form");
        viewInitializer.accept(primaryStage);
    }
}

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
