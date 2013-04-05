package lhs.qmaker.guipieces;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author jordan
 */
public class Choices extends AnchorPane {
    
    private ScrollPane scrollPane;
    private VBox content;
    private int numChoices = 4;
    private ArrayList<TextArea> choiceList = new ArrayList<TextArea>();
    
    public Choices() {
        super();
        content = new VBox();
        this.scrollPane = new ScrollPane();
        AnchorPane.setTopAnchor(this.scrollPane, 0.0);
        AnchorPane.setLeftAnchor(this.scrollPane, 0.0);
        AnchorPane.setRightAnchor(this.scrollPane, 0.0);
        AnchorPane.setBottomAnchor(this.scrollPane, 0.0);
        this.scrollPane.fitToWidthProperty().set(true);
        scrollPane.setContent(content);
        this.getChildren().add(this.scrollPane);
        numChange();
    }
    
    public final void numChange() {
        this.content = new VBox();
        ObservableList<Node> children = this.content.getChildren();
        //System.out.println(choiceList.size());
        this.content.getChildren().clear();
        //System.out.println(choiceList.size());
        for (int i = 0; i < this.numChoices; i++) {
            if (i < this.choiceList.size()) {
                this.content.getChildren().add(choiceList.get(i));
                //System.out.println(i+" old TA");
            } else {
                TextArea choice = new TextArea();
                choice.setWrapText(true);
                choice.setPromptText("Enter choice " + (i+1) + " here.");
                this.choiceList.add(choice);
                this.content.getChildren().add(choice);
                //System.out.println(i+" new TA");
            }
        }
        scrollPane.setContent(content);
    }
    public void setNum(int num) {
        numChoices = num;
    }
    public ArrayList<String> getChoices() {
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < numChoices; i++) {
            strings.add(choiceList.get(i).getText());
        }
        return strings;
    }
    public void setChoices(ArrayList<String> choices) {
        choiceList.clear();
        setNum(choices.size());
        for (int i = 0; i < choices.size(); i++) {
            TextArea choice = new TextArea();
            choice.setWrapText(true);
            choice.setPromptText("Enter choice " + (i+1) + " here.");
            choice.setText(choices.get(i));
            this.choiceList.add(choice);
        }
        numChange();
    }
    
}
