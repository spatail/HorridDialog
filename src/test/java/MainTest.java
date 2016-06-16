import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void ItTestsStuff() throws Exception {
        TestMain main = new TestMain();
        StageAdapter stageAdapter = new TestStageAdapter();
        ButtonAdapter buttonAdapter = new TestButtonAdapter();
        main.setButtonAdapter(buttonAdapter);

        final boolean[] wasClicked = {false};
        EventHandler<ActionEvent> eventHandler = e -> wasClicked[0] = true;
        main.setEventHandler(eventHandler);
        main.startWithStageAdapter(stageAdapter);

        main.fireEvent();

        assertTrue(wasClicked[0]);
    }
}

class TestMain extends Main {

    ButtonAdapter buttonAdapter;
    private EventHandler<ActionEvent> eventHandler;

    @Override
    protected void setupDialog() {

    }

    public void setButtonAdapter(ButtonAdapter buttonAdapter) {
        this.buttonAdapter = buttonAdapter;
    }

    @Override
    protected ButtonAdapter getButtonAdapter() {
        return buttonAdapter;
    }

    public void setEventHandler(EventHandler<ActionEvent> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    protected EventHandler<ActionEvent> getEventHandler() {
        return eventHandler;
    }

    public void fireEvent() {
        getEventHandler().handle(new ActionEvent());
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

class TestButtonAdapter implements ButtonAdapter {

    @Override
    public void setOnAction(EventHandler<ActionEvent> eventHandler) {

    }
}