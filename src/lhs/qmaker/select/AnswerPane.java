package lhs.qmaker.select;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
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
    private ArrayList<CheckBox> answers = new ArrayList<CheckBox>();
    
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
        ArrayList<CheckBox> temp = new ArrayList<CheckBox>();
        for (int i = 0; i < choices.size(); i++) {
            if (this.choices.size() > i && this.choices.get(i).equals(choices.get(i))
                    && this.answers.size() > i) {
                content.getChildren().add(answers.get(i));
                temp.add(answers.get(i));
            } else {
                CheckBox cb = new CheckBox();
                cb.setWrapText(true);
                cb.setText(choices.get(i));
                content.getChildren().add(cb);
                temp.add(cb);
            }
        }
        answers = temp;
        this.choices = choices;
    }
    
    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<String>();
        for (int i = 0; i < this.answers.size(); i++) {
            if (this.answers.get(i).isSelected()) {
                answers.add(this.answers.get(i).getText());
            }
        }
        return answers;
    }

    public void setAnswers(ArrayList<String> choices, ArrayList<String> answers) {
        this.choices = choices;
        update(choices);
        for (CheckBox answer : this.answers) {
            System.out.println(answer.getText());
            if (answers.contains(answer.getText())) {
                System.out.println("in");
                answer.selectedProperty().set(true);
            }
        }
    }
}
