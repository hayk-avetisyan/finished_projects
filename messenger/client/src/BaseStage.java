import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public abstract class BaseStage extends Stage {
    protected FXMLLoader loader;
    protected Map<String, Object> namespace;
    protected Parent root;

    protected BaseStage(String filepath) throws IOException {
        loader = new FXMLLoader(getClass().getResource(filepath));
        namespace = loader.getNamespace();
        root = loader.load();
    }

    public abstract void start();

}
