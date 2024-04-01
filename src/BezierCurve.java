public class BezierCurve {
    private Ponto2D p0;
    private Ponto2D p1;
    private Ponto2D p2;
    private Ponto2D p3;

    public BezierCurve(Ponto2D var1, Ponto2D var2, Ponto2D var3, Ponto2D var4) {
        this.p0 = var1;
        this.p1 = var2;
        this.p2 = var3;
        this.p3 = var4;
    }

    public Ponto2D calculatePoint(double var1) {
        double var3 = Math.pow(1.0D - var1, 3.0D) * this.p0.x + 3.0D * Math.pow(1.0D - var1, 2.0D) * var1 * this.p1.x + 3.0D * (1.0D - var1) * Math.pow(var1, 2.0D) * this.p2.x + Math.pow(var1, 3.0D) * this.p3.x;
        double var5 = Math.pow(1.0D - var1, 3.0D) * this.p0.y + 3.0D * Math.pow(1.0D - var1, 2.0D) * var1 * this.p1.y + 3.0D * (1.0D - var1) * Math.pow(var1, 2.0D) * this.p2.y + Math.pow(var1, 3.0D) * this.p3.y;
        return new Ponto2D(var3, var5, 1.0D);
    }
}
