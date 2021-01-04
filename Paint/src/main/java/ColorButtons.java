import javax.swing.*;
import java.awt.*;

public class ColorButtons extends JButton {

    private Color color;

    public ColorButtons(Color color) {

        this.color = color;
        setBackground(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
