import javafx.scene.Scene;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void itInitializesTheView() throws Exception {
        Main main = new Main();
        StageAdapter stageAdapter = new TestStageAdapter();
        boolean[] initialized = {false};
        Consumer<StageAdapter> viewInitializer = stage -> initialized[0] = true;
        main.startWithStageAdapter(stageAdapter, viewInitializer);

        assertTrue("Should have initialized view", initialized[0]);
    }
}

class TestStageAdapter implements StageAdapter {

    @Override
    public void setScene(Scene scene) {

    }

    @Override
    public void show() {

    }

    @Override
    public void setTitle(String s) {

    }
}