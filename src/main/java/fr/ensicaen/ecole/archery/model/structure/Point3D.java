package fr.ensicaen.ecole.archery.model.structure;
public class Point3D {
    public double x;
    public double y;
    public double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true; 
        }
       
        if ((objet == null) || (getClass() != objet.getClass())) {
            return false; 
        }

        Point3D point3D = (Point3D) objet;

        return (Double.compare(point3D.x, x) == 0) &&
                (Double.compare(point3D.y, y) == 0) &&
                (Double.compare(point3D.z, z) == 0);
    }
}
