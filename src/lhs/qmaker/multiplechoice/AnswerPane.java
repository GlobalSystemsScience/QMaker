package lhs.qmaker.multiplechoice;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author jordan
 */
public class AnswerPane extends AnchorPane {
    private Button selected;
    private ScrollPane scroll = new ScrollPane();
    private VBox content = new VBox();
    private ArrayList<String> choices = new ArrayList<String>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    
    public AnswerPane() {
        super();
        AnchorPane.setTopAnchor(this.scroll, 0.0);
        AnchorPane.setLeftAnchor(this.scroll, 0.0);
        AnchorPane.setRightAnchor(this.scroll, 0.0);
        AnchorPane.setBottomAnchor(this.scroll, 0.0);
        this.scroll.fitToWidthProperty().set(true);
        this.getChildren().add(scroll);
        scroll.setContent(content);
        content.fillWidthProperty().set(true);
        content.setFillWidth(true);
    }
    
    public void update(ArrayList<String> choices) {
        if (!this.choices.equals(choices)) {
            selected = null;
            buttons.clear();
            ObservableList<Node> children = content.getChildren();
            children.clear();
            for (int i = 0; i < choices.size(); i++) {
                Button b = new Button(choices.get(i));
                b.setWrapText(true);
                b.setMinWidth(USE_PREF_SIZE);
                b.prefWidthProperty().bind(content.widthProperty());
                b.minWidthProperty().bind(content.widthProperty());
                children.add(b);
                b.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {
                        if (selected != null) {
                            selected.setDefaultButton(false);
                        }
                        selected = (Button) t.getSource();
                        selected.setDefaultButton(true);
                    }
                });
                buttons.add(b);
            }
            this.choices = choices;
        }
    }
    
    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<String>();
        if (selected != null) {
            answers.add(selected.getText());
        }
        return answers;
    }

    public void setAnswers(ArrayList<String> choices, ArrayList<String> answers) {
        selected = null;
        update(choices);
        String answer = answers.get(0);
        for (Button button : buttons) {
            System.out.println(button.getText());
            if (button.getText().equals(answer)) {
                button.setDefaultButton(true);
                selected = button;
            } else {
                button.setDefaultButton(false);
            }
        }
    }
}
