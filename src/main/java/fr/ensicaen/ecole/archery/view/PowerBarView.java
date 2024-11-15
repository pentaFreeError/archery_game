package fr.ensicaen.ecole.archery.view;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class PowerBarView {

    private final double _width;
    private final double _height;
    private final double _x;
    private final double _y;
    private final Canvas _canvas;
    private double _power;

    public PowerBarView(double width, double height, double x, double y, Canvas canvas) {
        _canvas = canvas;
        _width = width;
        _height = height;
        _x = x;
        _y = y;
        _power = 0;

        drawPowerBar();
    }

    public void setPower(double power) {
        _power = Math.max(0, Math.min(100, power));  
        drawPowerBar();
    }

    private void drawPowerBar() {
        GraphicsContext gc = _canvas.getGraphicsContext2D();
        gc.clearRect(_x, _y, _width, _height);
        
	    double borderRadius = 40.0;

        Color outerShadowColor1 = Color.rgb(86, 158, 0);
        
        gc.setFill(Color.rgb(0, 0, 0, 0.2));
        gc.fillRoundRect(_x, _y, _width, _height, borderRadius, borderRadius);
        
        double filledHeight = (_power / 100) *  (_height - 10);

        double greenHeight = _height * 0.33;
        double yellowHeight = _height * 0.33;

        LinearGradient greenGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.GREEN),
                new Stop(1.0, Color.YELLOW));

        LinearGradient yellowGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.YELLOW),
                new Stop(1.0, Color.ORANGE));

        LinearGradient redGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.ORANGE),
                new Stop(1.0, Color.RED));

        if (_power > 0) {
            double greenFill = Math.min(filledHeight, greenHeight);
            gc.setFill(greenGradient);
            gc.fillRoundRect(_x, _y + (_height - greenFill) - 4, _width, greenFill -2, 0,0 );
        }

        if (_power > 33) {
            double yellowFill = Math.min(filledHeight - greenHeight, yellowHeight) + 5;
            gc.setFill(yellowGradient);
            gc.fillRoundRect(_x, _y + (_height - greenHeight - yellowFill), _width, yellowFill, 0, 0);
        }

        if (_power > 66) {
            double redFill = filledHeight - greenHeight - yellowHeight + 5;
            gc.setFill(redGradient);
            gc.fillRoundRect(_x, _y + (_height - greenHeight - yellowHeight - redFill), _width, redFill, 0, 0);
        }


        gc.setStroke(outerShadowColor1);
        gc.setLineWidth(12);
        gc.strokeRoundRect(_x, _y, _width, _height, borderRadius, borderRadius);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);
        gc.strokeRoundRect(_x, _y, _width, _height, borderRadius, borderRadius);
    }
        
}
