package lhs.qmaker;

import javax.swing.JButton;

/**Extends JButton such that it automatically
 * puts the text into html to make multi line buttons 
 * that wrap the text by word. Probably very useful for
 * other applications as well.
 * @author Jordan Bull
 *
 */
public class WrapButton extends JButton {
    private static final long serialVersionUID = 1L;
    private String text = "";
    public WrapButton (String s) {
        text = s;
        this.setText(text);
    }
    public void setSize(int x, int y) {
        super.setSize(x, y);
        this.setText(text);
    }
    public void setText(String s) {
        text = s;
        super.setText("<html>" + "<body style=' width: 100%'><p>" + s + "</p>");
    }
    public String getT() {
        return text;
    }
}