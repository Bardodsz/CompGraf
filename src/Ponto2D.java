
class Ponto2D {
    double x;
    double y;
    double z;

    public Ponto2D(double var1, double var3, double var5) {
        this.x = var1;
        this.y = var3;
        this.z = var5;
    }

    public void multiplicaMat(Mat3x3 var1) {
        double var2 = var1.mat[0][0] * this.x + var1.mat[1][0] * this.y + var1.mat[2][0] * this.z;
        double var4 = var1.mat[0][1] * this.x + var1.mat[1][1] * this.y + var1.mat[2][1] * this.z;
        double var6 = var1.mat[0][2] * this.x + var1.mat[1][2] * this.y + var1.mat[2][2] * this.z;
        this.x = var2;
        this.y = var4;
        this.z = var6;
    }
}

