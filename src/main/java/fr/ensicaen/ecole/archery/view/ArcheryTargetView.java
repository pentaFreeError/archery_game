package fr.ensicaen.ecole.archery.view;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArcheryTargetView {
    private final Canvas canvas;
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final int numberOfZones;

    public ArcheryTargetView(Canvas canvas, double centerX, double centerY, double radius, int numberOfZones) {
        this.canvas = canvas;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.numberOfZones = numberOfZones;
    }

    public void drawTarget() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double zoneRadius = radius / numberOfZones;

        double squareSize = radius * 2;
        double squareX = centerX - radius;  
        double squareY = centerY - radius;  
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeRect(squareX, squareY, squareSize, squareSize); 

        for (int i = numberOfZones; i > 0; i--) {
            if (i % 2 == 0) {
                gc.setFill(Color.RED);
            } else {
                gc.setFill(Color.WHITE);
            }
            gc.fillOval(centerX - i * zoneRadius, centerY - i * zoneRadius, 2 * i * zoneRadius, 2 * i * zoneRadius);
        }

        gc.setFill(Color.RED);
        gc.fillOval(centerX - 5, centerY - 5, 10, 10);  

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        double rightX = squareX + squareSize;

        gc.strokeLine(squareX, 0, squareX, squareY);
        gc.strokeLine(rightX, 0, rightX, squareY);
    }
}
