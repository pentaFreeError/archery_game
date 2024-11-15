package fr.ensicaen.ecole.archery.model.target;

import fr.ensicaen.ecole.archery.model.projectile.Arrow;
import fr.ensicaen.ecole.archery.model.structure.Point3D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

public final class ArcheryTargetTest {

    private final Point3D _targetPositionTest = new Point3D(35.0,63.0,40.0);
    private final Point3D _pointInTargetTest = new Point3D(38.0,65.0,41.0);
    private final Point3D _pointOutsideTargetTest = new Point3D(20.0,10.0,30.0);
    private final Point3D _veryDistantPointTest = new Point3D(200.0,300.0,400.0);
    private final double _radiusTest = 15.0;
    private final int _nbPartTest = 5;
    private final ArcheryTarget _archeryTargetTest = new ArcheryTarget(_targetPositionTest, _radiusTest, _nbPartTest);

    @Test
    public void should_return_4_when_calculating_the_score_for_arrowMockInside() {
        Arrow arrowMockInside = mock(Arrow.class);
        when(arrowMockInside.getEndPosition()).thenReturn(_pointInTargetTest);
        assertEquals(4,_archeryTargetTest.calculateScore(arrowMockInside));
    }

    @Test
    public void should_return_0_when_calculating_the_score_for_arrow_outside_the_target() {
        Arrow arrowMockOutside = mock(Arrow.class);
        when(arrowMockOutside.getEndPosition()).thenReturn(_pointOutsideTargetTest);
        assertEquals(0,_archeryTargetTest.calculateScore(arrowMockOutside));
    }

    @Test
    public void should_return_0_when_calculating_the_score_for_arrow_outside_the_target_with_small_radius() {
        Arrow arrowMockOutside = mock(Arrow.class);
        when(arrowMockOutside.getEndPosition()).thenReturn(_pointOutsideTargetTest);
        _archeryTargetTest.setRadius(_archeryTargetTest.getRadius()-14);
        assertEquals(0,_archeryTargetTest.calculateScore(arrowMockOutside));
    }

    @Test
    public void should_return_0_when_calculating_the_score_for_a_very_distant_arrow_from_the_target() {
        Arrow arrowMockOutside = mock(Arrow.class);
        when(arrowMockOutside.getEndPosition()).thenReturn(_veryDistantPointTest);
        assertNotSame(_archeryTargetTest.getCenterPosition(), _veryDistantPointTest);
        assertEquals(0,_archeryTargetTest.calculateScore(arrowMockOutside));
    }

}
