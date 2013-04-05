package lhs.qmaker.matching;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author jordan
 */
public class AnswerPane extends AnchorPane {
    private ScrollPane scroll = new ScrollPane();
    private VBox content = new VBox();
    private ArrayList<String> choices = new ArrayList<String>();
    private ArrayList<TextArea> answers = new ArrayList<TextArea>();
    
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
            content.getChildren().clear();
            ArrayList<TextArea> temp = new ArrayList<TextArea>();
            for (int i = 0; i < choices.size(); i++) {
                if (this.choices.size() > i && this.choices.get(i).equals(choices.get(i))
                        && this.answers.size() > i) {
                    content.getChildren().add(answers.get(i));
                    temp.add(answers.get(i));
                } else {
                    TextArea ta = new TextArea();
                    ta.setWrapText(true);
                    ta.setPromptText("Enter match for:\n"+choices.get(i));
                    content.getChildren().add(ta);
                    temp.add(ta);
                }
            }
            answers = temp;
            this.choices = choices;
    }
    
    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<String>();
        for (int i = 0; i < this.answers.size(); i++) {
            answers.add(this.answers.get(i).getText());
        }
        return answers;
    }

    
    
    public void setAnswers(ArrayList<String> choices, ArrayList<String> answers) {
        this.choices = choices;
        this.answers.clear();
        for (int i = 0; i < answers.size(); i++) {
            TextArea ta = new TextArea();
            ta.setWrapText(true);
            ta.setPromptText("Enter match for:\n"+choices.get(i));
            ta.setText(answers.get(i));
            this.answers.add(ta);
        }
        update(choices);
    }
}
