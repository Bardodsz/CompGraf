import java.util.ArrayList;
import java.util.Random;

public class Rotacao2D {
    // Ângulo de rotação em radianos
    private double angulo;

    public Rotacao2D(double angulo) {
        this.angulo = angulo;
    }

    // Método para definir o ângulo de rotação
    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    // Método para obter o ângulo de rotação atual
    public double getAngulo() {
        return angulo;
    }

    // Método para aplicar rotação em um ponto 2D
    public void rotacionarPonto(Ponto2D ponto) {
        double x = ponto.x;
        double y = ponto.y;

        // Fórmulas de rotação 2D
        double novoX = x * Math.cos(angulo) - y * Math.sin(angulo);
        double novoY = x * Math.sin(angulo) + y * Math.cos(angulo);

        // Atualiza as coordenadas do ponto
        ponto.x = novoX;
        ponto.y = novoY;
    }

    // Método para aplicar rotação em uma linha 2D
    public void rotacionarLinha(Linha2D linha) {
        rotacionarPonto(linha.a);
        rotacionarPonto(linha.b);
    }

    // Método para aplicar rotação em uma lista de pontos 2D
    public void rotacionarListaPontos(ArrayList<Ponto2D> listaPontos) {
        for (Ponto2D ponto : listaPontos) {
            rotacionarPonto(ponto);
        }
    }

    // Método para aplicar rotação em uma lista de linhas 2D
    public void rotacionarListaLinhas(ArrayList<Linha2D> listaLinhas) {
        for (Linha2D linha : listaLinhas) {
            rotacionarLinha(linha);
        }
    }
}
