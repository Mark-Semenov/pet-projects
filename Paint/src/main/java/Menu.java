import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JMenuBar {

    Canvas canvas;
    JMenu file = new JMenu("File");
    //JMenuItem savePicture = file.add("Сохранить рисунок"); //"TODO"
    JMenuItem exit = file.add("Выход");
    JMenu help = new JMenu("Help");

    JPopupMenu popupMenu = new JPopupMenu("Контекстное меню");
    JMenuItem clearCanvas = popupMenu.add("Очистить");


    public Menu(Canvas canvas) {

        this.canvas = canvas;
        add(file);
        add(help);
        setVisible(true);

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.exit(0);
            }
        });

    }
}
