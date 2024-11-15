package fr.ensicaen.ecole.archery.model.projectile;

import fr.ensicaen.ecole.archery.model.structure.Point3D;
import fr.ensicaen.ecole.archery.model.target.Shootable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArrowTest {

    private final Point3D _arrowPositionTest = new Point3D(300.0,400.0,500.0);
    private final Point3D _arrowStoppedMaxXYTest = new Point3D(1080,720,300.0);
    private final Point3D _arrowStoppedNullXYTest = new Point3D(0,0,300.0);
    private final Point3D _arrowStoppedNullXYZTest = new Point3D(0,0,0);
    private Point3D _arrowEndPositionTest;
    private Arrow _arrowTest;
    private double _t;
    @Mock
    private Shootable _targetMock;

    @BeforeEach
    public void setUp() {

        _t = 300.0 / (0.5 * Math.cos(20.0) * Math.cos(30.0));
        double g = 9.8;
        double x = 300.0 + 0.5 * Math.cos(30.0) * Math.sin(20.0) * _t;
        double y = 400.0 + 1.5 * 0.5 * Math.sin(30.0) * _t + (0.5 * g * _t * _t);
        double z = 500.0 + 0.5 * Math.cos(30.0) * Math.cos(20.0) * _t;
        _arrowEndPositionTest = new Point3D(x, y, z);

        Point3D targetPositionPoint = new Point3D(400.0, 400.0, 800.0);
        _targetMock = mock(Shootable.class);
        when(_targetMock.getCenterPosition()).thenReturn(targetPositionPoint);

        _arrowTest = new Arrow(_arrowPositionTest, _targetMock);
        _arrowTest.setAngleX(20.0);
        _arrowTest.setAngleY(30.0);
        _arrowTest.setPower(0.5);
    }

    @Test
    public void should_return_arrowEndPositionTest_when_calculating_position_at_time_t() {
        assertEquals(_arrowEndPositionTest,_arrowTest.getPositionAtTime(_t));
    }

    @Test
    public void should_return_arrowEndPositionTest_when_calculating_end_position() {
        assertEquals(_arrowEndPositionTest,_arrowTest.getEndPosition());
    }

    @Test
    public void should_return_arrowStoppedMaxXYTest_when_stopping_the_arrow_because_of_huge_xy() {
        _arrowTest.setPosition(_arrowStoppedNullXYZTest);
        assertEquals(_arrowStoppedMaxXYTest,_arrowTest.stopPosition(2000.0,800.0,300.0));
    }

    @Test
    public void should_return_arrowStoppedNullXYTest_when_stopping_the_arrow_because_of_negative_xy() {
        _arrowTest.setPosition(_arrowStoppedNullXYZTest);
        assertEquals(_arrowStoppedNullXYTest,_arrowTest.stopPosition(-100.0,-200.0,300.0));
    }
}
