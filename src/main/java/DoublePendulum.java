import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.AZURE;

public class DoublePendulum implements Runnable {

    private double w  = 600;
    private double h  = 600;

    private Thread t;
    private GraphicsContext gc;
    private Canvas backCanvas = new Canvas();
    private GraphicsContext back = backCanvas.getGraphicsContext2D();
    private SnapshotParameters params = new SnapshotParameters();

    private final double translateX = 300;
    private final double translateY = 50;

    private double r1 = 200;
    private double r2 = 200;
    private double m1 = 40;
    private double m2 = 40;
    private double theta1 = Math.PI/2;
    private double theta2 = Math.PI/3;

    public void draw() {
        gc.setFill(Color.rgb(100, 100, 100));

        /*gc.fillRect(0,0,w, h);
        gc.drawImage(back., 0 ,0);*/
        copyCanvas();

        double x1 = r1 * Math.sin(theta1);
        double y1 = r1 * Math.cos(theta1);

        gc.translate(translateX, translateY);

        gc.strokeLine(0,0, x1, y1);
        gc.setFill(Color.rgb(200, 100, 0));


        double x2 = x1 + r2 * Math.sin(theta2);
        double y2 = y1 + r2 * Math.cos(theta2);

        gc.strokeLine(x1,y1, x2, y2);
        gc.fillOval(x1 - m1/2, y1 - m1/2, m1, m1);
        gc.fillOval(x2 - m2/2, y2 - m2/2, m2, m2);

        theta1 += 0.1;
        theta2 -= 0.2;

        back.getPixelWriter().setColor((int)(x2+translateX), (int)(y2+translateY), AZURE);

        gc.translate(-translateX, -translateY);
    }

    public void run() {
        while (true) {
            draw();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public DoublePendulum(GraphicsContext gc) {
        this.gc = gc;

        t = new Thread(this);
        t.start();

        this.gc.setLineWidth(2);
        params.setFill(Color.TRANSPARENT);
        back.setFill(Color.rgb(100,100,100));
        back.fillRect(0,0, w, h);
        back.setStroke(Color.rgb(100,150,150));
    }

    public Thread getT() {
        return t;
    }

    private void copyCanvas() {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        WritableImage image = backCanvas.snapshot(params, null);
        gc.drawImage(image, 0, 0);

    }
}
