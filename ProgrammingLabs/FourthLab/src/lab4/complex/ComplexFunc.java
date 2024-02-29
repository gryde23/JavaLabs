package lab4.complex;

public class ComplexFunc {
    public static String ExpGradeZ(Complex z) {
        return (Math.exp(z.x()) + "(" + Math.cos(z.y()) + " + i*" + Math.sin(z.y()) + "))");
    }
    public static Complex Exp(Complex z) {
        double x = Math.cos(z.x());
        double y = Math.sin(z.x());
        return new Complex(x,y);
    }
    public static String sin(Complex z) {
        double e1 = Math.exp(z.x() + z.y()), e2 = Math.exp(-z.x()-z.y());
        return ("sin(" + z.getComplex() + ") = " + e1 + "^i - " + e2 + "^(-i) / 2i");
    }
}
