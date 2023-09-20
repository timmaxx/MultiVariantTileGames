package timmax.tilegameenginejfx;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import timmax.basetilemodel.*;

public abstract class ViewJfx extends View {
    protected Pane root;
    protected Stage primaryStage;

    public ViewJfx( BaseModel baseModel) {
        super( baseModel);
    }

    public void setRoot( Pane root) {
        this.root = root;
    }

    public void setPrimaryStage( Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}