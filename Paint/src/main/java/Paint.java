public class Paint {

    public static void main(String[] args) {

        Canvas canvas = new Canvas();
        Menu menu = new Menu(canvas);
        PaintDemoApp app = new PaintDemoApp(canvas, menu);

        app.paintStart();

    }
}
