
class Mat3x3 {
    double[][] mat = new double[3][3];

    public Mat3x3() {
        this.identidade();
    }

    public void identidade() {
        for(int var1 = 0; var1 < 3; ++var1) {
            for(int var2 = 0; var2 < 3; ++var2) {
                if (var1 == var2) {
                    this.mat[var1][var2] = 1.0D;
                } else {
                    this.mat[var1][var2] = 0.0D;
                }
            }
        }

    }

    public void setTranslate(double var1, double var3) {
        this.identidade();
        this.mat[0][2] = var1;
        this.mat[1][2] = var3;
    }

    public void setSacale(double var1, double var3) {
        this.identidade();
        this.mat[0][0] = var1;
        this.mat[1][1] = var3;
    }
}