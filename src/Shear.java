import java.util.ArrayList;

public class Shear {
    private double shearX;
    private double shearY;

    public Shear(double shearX, double shearY) {
        this.shearX = shearX;
        this.shearY = shearY;
    }

    public void setShearX(double shearX) {
        this.shearX = shearX;
    }

    public void setShearY(double shearY) {
        this.shearY = shearY;
    }

    public double getShearX() {
        return shearX;
    }

    public double getShearY() {
        return shearY;
    }

    public void applyShear(Ponto2D ponto) {
        double newX = ponto.x + shearX * ponto.y;
        double newY = ponto.y + shearY * ponto.x;
        ponto.x = newX;
        ponto.y = newY;
    }

    public void applyShear(ArrayList<Ponto2D> pontos) {
        for (Ponto2D ponto : pontos) {
            applyShear(ponto);
        }
    }

    public void applyShear(Linha2D linha) {
        applyShear(linha.a);
        applyShear(linha.b);
    }

    public void applyShearToPointList(ArrayList<Linha2D> linhas) {
        for (Linha2D linha : linhas) {
            applyShear(linha);
        }
    }
}

