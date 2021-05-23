import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private Graphics gCanvas;
    private Graphics2D graphics2D;
    private Color color = Color.white;
    private Font myFont = new Font("Bold", Font.BOLD, 20);

    public Canvas() {

        setBackground(color);
        setFont(myFont);
        setVisible(true);

    }

    public Graphics getgCanvas() {
        return gCanvas;
    }

    public void setgCanvas(Graphics gCanvas) {
        this.gCanvas = gCanvas;
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public Font getMyFont() {
        return myFont;
    }

    public void setMyFont(Font myFont) {
        this.myFont = myFont;
    }
}
