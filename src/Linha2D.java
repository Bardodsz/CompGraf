//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Graphics2D;

class Linha2D {
    Ponto2D a;
    Ponto2D b;

    public Linha2D(Ponto2D var1, Ponto2D var2) {
        this.a = var1;
        this.b = var2;
    }

    public void desenhase(Graphics2D var1) {
        var1.drawLine((int)this.a.x, (int)this.a.y, (int)this.b.x, (int)this.b.y);
    }
}
