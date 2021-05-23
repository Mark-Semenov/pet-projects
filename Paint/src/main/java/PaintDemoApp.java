import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PaintDemoApp extends JFrame {

    private Canvas canvas;
    private Menu menu;
    private Color color;
    private JToolBar toolBar = new JToolBar("Палитра цветов");
    private JPanel currentColor = new JPanel();
    private ColorButtons [] buttons = new ColorButtons[10];
    JFrame help = new JFrame("Справка");
    JTextArea textAreaHelp = new JTextArea("\n" + "  1. <Правая/Левая> кнопка мыши - рисовать 3D прямоугольником\n" +
            "  2. <Правая/Левая> кнопка мыши + Shift - рисовать овалом\n" + "  3. Зажать Alt - резинка");


    public PaintDemoApp(Canvas canvas, Menu menu) throws HeadlessException {

        this.canvas = canvas;
        this.menu = menu;

    }

    public void paintStart(){

        setSize(1400,900);
        setLocation(200, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Simple paint");
        setJMenuBar(menu);
        toolBar.addSeparator();
        add(toolBar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        help.setSize(450, 300);
        help.setBackground(Color.white);
        help.setLocation(400,200);
        help.setAlwaysOnTop(true);
        textAreaHelp.setEditable(false);
        textAreaHelp.revalidate();
        help.add(textAreaHelp, BorderLayout.CENTER);

        buttons [0] = new ColorButtons(Color.red);
        buttons [1] = new ColorButtons(Color.green);
        buttons [2] = new ColorButtons(Color.cyan);
        buttons [3] = new ColorButtons(Color.blue);
        buttons [4] = new ColorButtons(Color.pink);
        buttons [5] = new ColorButtons(Color.yellow);
        buttons [6] = new ColorButtons(Color.orange);
        buttons [7] = new ColorButtons(Color.magenta);
        buttons [8] = new ColorButtons(Color.black);
        buttons [9] = new ColorButtons(Color.gray);

        //добавляем цветные кнопки в тулбар
        for (ColorButtons i : buttons) {
            toolBar.add(i, BorderLayout.WEST);
            toolBar.addSeparator();
            i.setBackground(i.getBackground());
        }

        canvas.add(currentColor, BorderLayout.WEST); //Индикатор цвета, который выбрал пользователь

        setVisible(true);
        colorSet();
        userDraw();
        canvasClear ();
        helpMenu();
    }

    //Метод который менят цвет
    private void colorSet (){
        for (ColorButtons i : buttons){
            i.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mousePressed(e);
                    color = i.getBackground();
                    currentColor.setBackground(color);
                }
            });
        }
    }

    //Метод рисования
    private void userDraw(){

        canvas.setgCanvas(getGraphics());
        canvas.setGraphics2D((Graphics2D) canvas.getgCanvas());

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

                canvas.getGraphics2D().setColor(color);

                if (!e.isShiftDown()) {
                    canvas.getGraphics2D().fill3DRect(e.getX(), e.getY(), 5, 5, true);
                } else canvas.getGraphics2D().fillOval(e.getX(), e.getY(), 10, 10);

            }

            //Отслеживаем координаты мыши и затираем изображение, если зажат Alt
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.isAltDown()) {
                    repaint(e.getX(), e.getY(), 10, 10);
                }
            }

        });

    }


    //Вызывает контекстное меню из тулбара. Очищает холст при нажатии пункта "Очистить".
    private void canvasClear (){

        toolBar.setComponentPopupMenu(menu.popupMenu);
        menu.clearCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                canvas.repaint(canvas.getMaximumSize().width);
            }
        });

    }

    private void helpMenu (){

        menu.help.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            help.setVisible(true);
        }
    });

    }

}
