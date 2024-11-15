package fr.ensicaen.ecole.archery.model.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Point3DTest{
    @Test
    public void testCreation(){
        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(-1, 3.5, 0);
        assertEquals(1, p1.x); 
        assertEquals(2, p1.y); 
        assertEquals(3, p1.z); 
        assertEquals(-1, p2.x);
        assertEquals(3.5, p2.y);
        assertEquals(0, p2.z);
        Point3D p3 = new Point3D(Double.MAX_VALUE, Double.MIN_VALUE, 0);
        assertEquals(Double.MAX_VALUE, p3.x);
        assertEquals(Double.MIN_VALUE, p3.y);
    }

    @Test
    public void testEquals_SameObject() {
        Point3D point = new Point3D(1.0, 2.0, 3.0);
        assertEquals(point, point);
    }

    @Test
    public void testEquals_Null() {
        Point3D point = new Point3D(1.0, 2.0, 3.0);
        assertNotNull(point);
    }

    @Test
    public void testEquals_DifferentClass() {
        Point3D point = new Point3D(1.0, 2.0, 3.0);
        String someString = "test";
        assertNotEquals(point, someString);
    }

    @Test
    public void testEquals_DifferentPoints() {
        Point3D point1 = new Point3D(1.0, 2.0, 3.0);
        Point3D point2 = new Point3D(4.0, 5.0, 6.0);
        assertNotEquals(point1, point2);
    }

    @Test
    public void testEquals_SameCoordinates() {
        Point3D point1 = new Point3D(1.0, 2.0, 3.0);
        Point3D point2 = new Point3D(1.0, 2.0, 3.0);
        assertEquals(point1, point2);
    }

    @Test
    public void testEquals_CloseCoordinates() {
        Point3D point1 = new Point3D(1.00001, 2.00001, 3.00001);
        Point3D point2 = new Point3D(1.0, 2.0, 3.0);
        assertNotEquals(point1, point2);
    }
}