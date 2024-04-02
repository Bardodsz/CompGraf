public class Ponto2D {
    double x;
    double y;
    double z;

    public Ponto2D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void multiplicaMat(Mat3x3 matriz) {
        double newX = matriz.mat[0][0] * this.x + matriz.mat[1][0] * this.y + matriz.mat[2][0] * this.z;
        double newY = matriz.mat[0][1] * this.x + matriz.mat[1][1] * this.y + matriz.mat[2][1] * this.z;
        double newZ = matriz.mat[0][2] * this.x + matriz.mat[1][2] * this.y + matriz.mat[2][2] * this.z;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    // Método para realizar o cisalhamento em um eixo específico
    public void shear(double shearAmount, char axis) {
        if (axis == 'x' || axis == 'X') {
            this.x += shearAmount * this.z;
        } else if (axis == 'y' || axis == 'Y') {
            this.y += shearAmount * this.z;
        }
    }
}
