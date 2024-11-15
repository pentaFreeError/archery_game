package fr.ensicaen.ecole.archery.view;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BoxView {
    private final Canvas canvas;
    private final double width;
    private final double height;
    private final double x;
    private final double y;
    private final String name;

    public BoxView(Canvas canvas, double width, double height, double x, double y, String name) {
        this.canvas = canvas;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void drawRectangle() {
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.rgb(0, 0, 0, 0.60));
        gc.fillRoundRect(x, y, width, height, 25, 25);

        Color colorBorderTop = Color.rgb(48, 74, 41, 1);
        Color colorBorderBottom = Color.rgb(102, 114, 38, 1);
        LinearGradient colorBorder = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, colorBorderTop),
                new Stop(1.0, colorBorderBottom));

        gc.setStroke(colorBorder);
        gc.setLineWidth(4);
        gc.strokeRoundRect(x, y, width, height, 25, 25);

    }

    public void changeValue(int newValue) {
        
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(x, y, width, height);
        drawRectangle();

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24));  

        gc.setFill(Color.WHITE);
        gc.fillText(this.name + ": " + newValue, x + 20, y + height / 2 + 10);
    }
}
