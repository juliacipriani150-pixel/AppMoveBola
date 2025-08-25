import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PainelBola extends JPanel implements KeyListener, ActionListener,
        MouseListener, MouseMotionListener {

    private int x = 100, y = 100;
    private final int DIAMETRO = 30;
    private final int VELOCIDADE = 5;
    private Timer timer;
    private boolean cima, baixo, esquerda, direita;

    private boolean arrastando = false;
    private int offsetX, offsetY;

    public PainelBola() {
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
        timer = new Timer(15, this);
        timer.start();

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, DIAMETRO, DIAMETRO);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!arrastando) {
            if (cima) y -= VELOCIDADE;
            if (baixo) y += VELOCIDADE;
            if (esquerda) x -= VELOCIDADE;
            if (direita) x += VELOCIDADE;

            // Limita dentro da janela
            x = Math.max(0, Math.min(x, getWidth() - DIAMETRO));
            y = Math.max(0, Math.min(y, getHeight() - DIAMETRO));
        }

        repaint();
    }

    // Teclado
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> cima = true;
            case KeyEvent.VK_DOWN -> baixo = true;
            case KeyEvent.VK_LEFT -> esquerda = true;
            case KeyEvent.VK_RIGHT -> direita = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> cima = false;
            case KeyEvent.VK_DOWN -> baixo = false;
            case KeyEvent.VK_LEFT -> esquerda = false;
            case KeyEvent.VK_RIGHT -> direita = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Mouse
    @Override public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Detecta se o clique foi dentro da bola
        if (mouseX >= x && mouseX <= x + DIAMETRO &&
            mouseY >= y && mouseY <= y + DIAMETRO) {
            arrastando = true;
            offsetX = mouseX - x;
            offsetY = mouseY - y;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        arrastando = false;
    }





    @Override public void mouseEntered(MouseEvent e) {


    }

    @Override public void mouseExited(MouseEvent e) {


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (arrastando) {
            x = e.getX() - offsetX;
            y = e.getY() - offsetY;
            repaint();
        }
    }

    @Override public void mouseMoved(MouseEvent e) {

        
    }
    
}

public class AppMoveBolaPanel extends JFrame {
    public AppMoveBolaPanel() {
        setTitle("Movimentar Bola com Teclado e Mouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        PainelBola painel = new PainelBola();
        add(painel);
        addKeyListener(painel); // controle de teclado
        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppMoveBolaPanel());
    }
}