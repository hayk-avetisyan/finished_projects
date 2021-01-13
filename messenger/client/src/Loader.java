import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Map;

public class Loader {
    private FXMLLoader fxmlLoader;
    private Map<String, Object> namespace;
    private Parent root;

    protected Loader(String filepath) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource(filepath));
        namespace = fxmlLoader.getNamespace();
        root = fxmlLoader.load();
    }

    public Object get(String id) {
        return namespace.get(id);
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Map<String, Object> getNamespace() {
        return namespace;
    }

    public Parent getRoot() {
        return root;
    }
}
