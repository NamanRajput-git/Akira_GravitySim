import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Simulation extends JPanel {
    List<Body> bodies;
    Akira_Engine engine;

    double zoom = 1.0;
    double offsetX = 0, offsetY = 0;
    boolean dragging = false;
    int lastX, lastY;
    boolean showInfo = false;

    interface ClickCallback {
        void onClick(double x, double y);
    }

    private ClickCallback clickCallback;

    public void setOnClick(ClickCallback callback) {
        this.clickCallback = callback;
    }

    public Simulation(List<Body> bodies, Akira_Engine engine) {
        this.bodies = bodies;
        this.engine = engine;
        setBackground(Color.BLACK);

        addMouseWheelListener(e -> {
            zoom *= (e.getPreciseWheelRotation() > 0) ? 0.9 : 1.1;
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    dragging = true;
                    lastX = e.getX();
                    lastY = e.getY();
                } else if (clickCallback != null) {
                    double x = (e.getX() - offsetX) / zoom;
                    double y = (e.getY() - offsetY) / zoom;
                    clickCallback.onClick(x, y);
                }
            }

            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    offsetX += e.getX() - lastX;
                    offsetY += e.getY() - lastY;
                    lastX = e.getX();
                    lastY = e.getY();
                    repaint();
                }
            }
        });
    }

    public void toggleInfo() {
        showInfo = !showInfo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Body b : bodies) {
            int x = (int) (b.r.x * zoom + offsetX);
            int y = (int) (b.r.y * zoom + offsetY);

            g2.setColor(Color.WHITE);
            g2.fillOval(x - 5, y - 5, 10, 10);

            if (showInfo) {
                g2.setColor(Color.GREEN);
                g2.drawString(String.format("(%.1f, %.1f)", b.r.x, b.r.y), x + 10, y);
                g2.drawString(String.format("v=%.2f", b.v.magnitude()), x + 10, y + 15);
            }
        }
    }
}
