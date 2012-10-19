package lhs.qmaker;

import java.awt.Container;

import lhs.qmaker.MultipleChoice.MultipleChoiceQuestionPanel;


public class MenuController {
    static QMaker app = null;
    public static QuestionPanel question;
    public static ChoicesPanel choices;
    public static AnswersPanel answers;
    public static CommentsPanel comments;

    public static void setQMaker(QMaker app) {
        MenuController.app = app;
    }
    public static void setPane(Container pane) {
        app.setPane(pane);
    }
    public static void newMultipleChoice() {
        question = new MultipleChoiceQuestionPanel();
        app.setPane(question);
    }
}
