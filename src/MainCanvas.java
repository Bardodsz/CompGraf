import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    int W = 1000;
    int H = 800;
    Thread runner;
    boolean ativo = true;
    int paintcounter = 0;
    BufferedImage imageBuffer;
    byte[] bufferDeVideo;
    Random rand = new Random();
    byte[] memoriaPlacaVideo;
    short[][] paleta;
    int framecount = 0;
    int fps = 0;
    Font f = new Font("", 0, 30);
    int clickX = 0;
    int clickY = 0;
    int mouseX = 0;
    int mouseY = 0;
    int pixelSize = 0;
    int Largura = 0;
    int Altura = 0;
    BufferedImage imgtmp = null;
    float posx = 0.0F;
    float posy = 0.0F;
    boolean LEFT = false;
    boolean RIGHT = false;
    boolean UP = false;
    boolean DOWN = false;
    Linha2D l1;
    Linha2D l2;
    Linha2D l3;
    Ponto2D p1;
    Ponto2D p2;
    Ponto2D p3;
    ArrayList<Ponto2D> listaDePontos = new ArrayList();
    ArrayList<Linha2D> listaDeLinhas = new ArrayList();
    Ponto2D pclik1 = null;
    Ponto2D pclik2 = null;
    private ArrayList<Ponto2D> pontosBezier = new ArrayList();

    public MainCanvas() {
        this.setSize(this.W, this.H);
        this.setFocusable(true);
        this.Largura = this.W;
        this.Altura = this.H;
        this.pixelSize = this.W * this.H;
        this.imgtmp = this.loadImage("fundo.jpg");
        this.imageBuffer = new BufferedImage(this.W, this.H, 6);
        this.bufferDeVideo = ((DataBufferByte)this.imageBuffer.getRaster().getDataBuffer()).getData();
        System.out.println("Buffer SIZE " + this.bufferDeVideo.length);
        this.p1 = new Ponto2D(50.0D, 50.0D, 1.0D);
        this.p2 = new Ponto2D(250.0D, 50.0D, 1.0D);
        this.p3 = new Ponto2D(150.0D, 200.0D, 1.0D);
        this.listaDePontos.add(this.p1);
        this.listaDePontos.add(this.p2);
        this.listaDePontos.add(this.p3);
        this.l1 = new Linha2D(this.p1, this.p2);
        this.l2 = new Linha2D(this.p2, this.p3);
        this.l3 = new Linha2D(this.p3, this.p1);
        this.listaDeLinhas.add(this.l1);
        this.listaDeLinhas.add(this.l2);
        this.listaDeLinhas.add(this.l3);
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent var1) {
            }

            public void keyReleased(KeyEvent var1) {
                int var2 = var1.getKeyCode();
                if (var2 == 87) {
                    MainCanvas.this.UP = false;
                }

                if (var2 == 83) {
                    MainCanvas.this.DOWN = false;
                }

                if (var2 == 65) {
                    MainCanvas.this.LEFT = false;
                }

                if (var2 == 68) {
                    MainCanvas.this.RIGHT = false;
                }

            }

            public void keyPressed(KeyEvent var1) {
                int var2 = var1.getKeyCode();
                Mat3x3 var3;
                int var4;
                if (var2 == 87) {
                    var3 = new Mat3x3();
                    var3.setTranslate(0.0D, 50.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 83) {
                    var3 = new Mat3x3();
                    var3.setTranslate(0.0D, -50.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 65) {
                    var3 = new Mat3x3();
                    var3.setTranslate(50.0D, 0.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 68) {
                    var3 = new Mat3x3();
                    var3.setTranslate(-50.0D, 0.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 90) {
                    var3 = new Mat3x3();
                    var3.setTranslate(-500.0D, -400.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }

                    var3.setSacale(1.1D, 1.1D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }

                    var3.setTranslate(500.0D, 400.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 88) {
                    var3 = new Mat3x3();
                    var3.setTranslate(-500.0D, -400.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }

                    var3.setSacale(0.9D, 0.9D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }

                    var3.setTranslate(500.0D, 400.0D);

                    for(var4 = 0; var4 < MainCanvas.this.listaDePontos.size(); ++var4) {
                        ((Ponto2D)MainCanvas.this.listaDePontos.get(var4)).multiplicaMat(var3);
                    }
                }

                if (var2 == 112) {
                    try {
                        BufferedWriter var19 = new BufferedWriter(new FileWriter("MeuDenhoLindo.txt"));

                        for(var4 = 0; var4 < MainCanvas.this.listaDeLinhas.size(); ++var4) {
                            Linha2D var5 = (Linha2D)MainCanvas.this.listaDeLinhas.get(var4);
                            var19.write(var5.a.x + ";" + var5.a.y + ";" + var5.b.x + ";" + var5.b.y + ";\n");
                        }

                        var19.close();
                        System.out.println("Salvou");
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }
                }

                if (var2 == 113) {
                    MainCanvas.this.listaDeLinhas.clear();
                    MainCanvas.this.listaDePontos.clear();

                    try {
                        BufferedReader var20 = new BufferedReader(new FileReader("MeuDenhoLindo.txt"));
                        String var22 = "";

                        while((var22 = var20.readLine()) != null) {
                            String[] var21 = var22.split(";");
                            double var6 = Double.parseDouble(var21[0]);
                            double var8 = Double.parseDouble(var21[1]);
                            double var10 = Double.parseDouble(var21[2]);
                            double var12 = Double.parseDouble(var21[3]);
                            Ponto2D var14 = new Ponto2D(var6, var8, 1.0D);
                            Ponto2D var15 = new Ponto2D(var10, var12, 1.0D);
                            MainCanvas.this.listaDePontos.add(var14);
                            MainCanvas.this.listaDePontos.add(var15);
                            Linha2D var16 = new Linha2D(var14, var15);
                            MainCanvas.this.listaDeLinhas.add(var16);
                        }

                        var20.close();
                        System.out.println("carregou");
                    } catch (IOException var17) {
                        var17.printStackTrace();
                    }
                }

            }
        });
        this.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent var1) {
            }

            public void mousePressed(MouseEvent var1) {
                MainCanvas.this.clickX = var1.getX();
                MainCanvas.this.clickY = var1.getY();
                if (MainCanvas.this.pclik1 == null) {
                    MainCanvas.this.pclik1 = new Ponto2D((double)MainCanvas.this.clickX, (double)MainCanvas.this.clickY, 1.0D);
                } else {
                    MainCanvas.this.pclik2 = new Ponto2D((double)MainCanvas.this.clickX, (double)MainCanvas.this.clickY, 1.0D);
                    MainCanvas.this.listaDePontos.add(MainCanvas.this.pclik1);
                    MainCanvas.this.listaDePontos.add(MainCanvas.this.pclik2);
                    MainCanvas.this.listaDeLinhas.add(new Linha2D(MainCanvas.this.pclik1, MainCanvas.this.pclik2));
                    MainCanvas.this.pclik1 = null;
                    MainCanvas.this.pclik2 = null;
                }

            }

            public void mouseExited(MouseEvent var1) {
            }

            public void mouseEntered(MouseEvent var1) {
            }

            public void mouseClicked(MouseEvent var1) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent var1) {
                MainCanvas.this.mouseX = var1.getX();
                MainCanvas.this.mouseY = var1.getY();
            }

            public void mouseDragged(MouseEvent var1) {
            }
        });
        JMenuBar var1 = new JMenuBar();
        JMenu var2 = new JMenu("Curvas de Bezier");
        JMenuItem var3 = new JMenuItem("Nova Curva de Bezier");
        var3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
                MainCanvas.this.criarNovaCurvaBezier();
            }
        });
        var2.add(var3);
        var1.add(var2);
        JFrame var4 = new JFrame("Curvas de Bezier");
        var4.setJMenuBar(var1);
        var4.getContentPane().add(this);
        var4.pack();
        var4.setDefaultCloseOperation(3);
        var4.setVisible(true);
    }

    private void criarNovaCurvaBezier() {
        this.pontosBezier.clear();
        this.repaint();
    }

    private void desenharCurvaBezier() {
        if (this.pontosBezier.size() == 4) {
            BezierCurve var1 = new BezierCurve((Ponto2D)this.pontosBezier.get(0), (Ponto2D)this.pontosBezier.get(1), (Ponto2D)this.pontosBezier.get(2), (Ponto2D)this.pontosBezier.get(3));

            for(double var2 = 0.0D; var2 <= 1.0D; var2 += 0.001D) {
                Ponto2D var4 = var1.calculatePoint(var2);
                this.desenhaPixel((int)var4.x, (int)var4.y, 255, 0, 0);
            }
        }

    }

    private void drawImageToBuffer(BufferedImage var1, int var2, int var3, float var4, float var5, float var6) {
        byte[] var7 = ((DataBufferByte)var1.getRaster().getDataBuffer()).getData();
        int var8 = var1.getWidth();
        int var9 = var1.getHeight();

        for(int var10 = 0; var10 < var9; ++var10) {
            for(int var11 = 0; var11 < var8; ++var11) {
                int var12 = var10 * var8 * 4 + var11 * 4;
                int var13 = (var10 + var3) * this.W * 4 + (var11 + var2) * 4;
                this.bufferDeVideo[var13] = var7[var12];
                int var14 = var7[var12 + 1] & 255;
                int var15 = var7[var12 + 2] & 255;
                int var16 = var7[var12 + 3] & 255;
                var14 = (int)((float)var14 * var6);
                var15 = (int)((float)var15 * var5);
                var16 = (int)((float)var16 * var4);
                var14 = Math.min(255, var14);
                var15 = Math.min(255, var15);
                var16 = Math.min(255, var16);
                this.bufferDeVideo[var13 + 1] = (byte)(var14 & 255);
                this.bufferDeVideo[var13 + 2] = (byte)(var15 & 255);
                this.bufferDeVideo[var13 + 3] = (byte)(var16 & 255);
            }
        }

    }

    public void paint(Graphics var1) {
        int var2;
        for(var2 = 0; var2 < this.bufferDeVideo.length; ++var2) {
            this.bufferDeVideo[var2] = 0;
        }

        var1.setColor(Color.white);
        var1.fillRect(0, 0, this.W, this.H);
        var1.drawImage(this.imageBuffer, 0, 0, (ImageObserver)null);
        var1.setColor(Color.black);

        for(var2 = 0; var2 < this.listaDeLinhas.size(); ++var2) {
            ((Linha2D)this.listaDeLinhas.get(var2)).desenhase((Graphics2D)var1);
        }

        var1.setColor(Color.green);
        if (this.pontosBezier.size() > 0) {
            Iterator var4 = this.pontosBezier.iterator();

            while(var4.hasNext()) {
                Ponto2D var3 = (Ponto2D)var4.next();
                var1.drawOval((int)var3.x - 2, (int)var3.y - 2, 4, 4);
            }

            this.desenharCurvaBezier();
        }

        var1.setColor(Color.black);
        var1.drawString("FPS " + this.fps, 10, 25);
    }

    public void desenhaPixel(int var1, int var2, int var3, int var4, int var5) {
        int var6 = var2 * this.W * 4 + var1 * 4;
        this.bufferDeVideo[var6] = -1;
        this.bufferDeVideo[var6 + 1] = (byte)var5;
        this.bufferDeVideo[var6 + 2] = (byte)var4;
        this.bufferDeVideo[var6 + 3] = (byte)var3;
    }

    public BufferedImage loadImage(String var1) {
        BufferedImage var2 = null;

        try {
            var2 = ImageIO.read(new File(var1));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return var2;
    }

    public void run() {
        long var1 = System.nanoTime();
        long var3 = 0L;

        while(this.ativo) {
            long var5 = System.nanoTime();
            var3 += var5 - var1;
            var1 = var5;
            if (var3 >= 1000000000L) {
                this.fps = this.framecount;
                this.framecount = 0;
                var3 = 0L;
            }

            ++this.framecount;
            this.repaint();
        }

    }

    public void mouseClicked(MouseEvent var1) {
    }

    public void mouseEntered(MouseEvent var1) {
    }

    public void mouseExited(MouseEvent var1) {
    }

    public void mousePressed(MouseEvent var1) {
    }

    public void mouseReleased(MouseEvent var1) {
    }

    public void mouseDragged(MouseEvent var1) {
    }

    public void mouseMoved(MouseEvent var1) {
    }

    public static void main(String[] var0) {
        (new MainCanvas()).start();
    }

    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
            this.runner.start();
        }

    }
}
