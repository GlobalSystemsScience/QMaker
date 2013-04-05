/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lhs.qmaker;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lhs.qmaker.database.Database;

/**
 *
 * @author jordan
 */
public class QMakerFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //int i = StoreQuestion.storeMultiChoice("q", new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
    }
}
