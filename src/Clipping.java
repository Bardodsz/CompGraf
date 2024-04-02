public class Clipping {
    // Definições de códigos de região
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    private int xMin, yMin, xMax, yMax;

    public Clipping(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    // Método para realizar o recorte de uma linha
    public void clipLine(Linha2D linha) {
        int x1 = (int) linha.a.x;
        int y1 = (int) linha.a.y;
        int x2 = (int) linha.b.x;
        int y2 = (int) linha.b.y;

        // Calcula os códigos de região para os pontos iniciais e finais
        int code1 = calculateCode(x1, y1);
        int code2 = calculateCode(x2, y2);

        boolean accept = false;

        while (true) {
            if ((code1 == 0) && (code2 == 0)) {
                // Ambos os pontos estão dentro, então aceite a linha
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // Ambos os pontos estão fora da janela de visualização, então rejeite a linha
                break;
            } else {
                // Pelo menos um ponto está fora da janela de visualização
                int codeOut;
                int x, y;

                // Seleciona o ponto externo
                if (code1 != 0) {
                    codeOut = code1;
                } else {
                    codeOut = code2;
                }

                // Encontra a interseção do ponto externo com as bordas da janela de visualização
                if ((codeOut & TOP) != 0) {
                    x = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                    y = yMax;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                    y = yMin;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                    x = xMax;
                } else {
                    y = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                    x = xMin;
                }

                // Atualiza o ponto externo com as novas coordenadas de interseção
                if (codeOut == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = calculateCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = calculateCode(x2, y2);
                }
            }
        }

        if (accept) {
            // Atualiza os pontos da linha com as coordenadas resultantes após o recorte
            linha.a.x = x1;
            linha.a.y = y1;
            linha.b.x = x2;
            linha.b.y = y2;
        } else {
            // Se a linha for totalmente rejeitada, defina os pontos como nulos
            linha.a = null;
            linha.b = null;
        }
    }

    // Método para calcular o código de região para um ponto
    private int calculateCode(int x, int y) {
        int code = INSIDE; // Inicialmente, assume-se que o ponto está dentro da janela de visualização

        if (x < xMin) {
            code |= LEFT;
        } else if (x > xMax) {
            code |= RIGHT;
        }
        if (y < yMin) {
            code |= BOTTOM;
        } else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }
}
