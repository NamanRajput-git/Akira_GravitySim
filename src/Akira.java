import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Akira {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Akira Gravity Engine");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);

            ArrayList<Body> bodies = new ArrayList<>();
            bodies.add(new Body(new Vect(400, 400), new Vect(0, 0), 1e15));

            Akira_Engine engine = new Akira_Engine(bodies);
            Simulation simPanel = new Simulation(bodies, engine);

            // Controls Panel
            JPanel controls = new JPanel();
            controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

            JSlider massSlider = new JSlider(1, 1000, 100);
            JLabel massLabel = new JLabel("Mass: 100");
            massSlider.addChangeListener(e -> massLabel.setText("Mass: " + massSlider.getValue()));

            JSlider velXSlider = new JSlider(-100, 100, 0);
            JLabel velXLabel = new JLabel("Vel X: 0");
            velXSlider.addChangeListener(e -> velXLabel.setText("Vel X: " + velXSlider.getValue()));

            JSlider velYSlider = new JSlider(-100, 100, 0);
            JLabel velYLabel = new JLabel("Vel Y: 0");
            velYSlider.addChangeListener(e -> velYLabel.setText("Vel Y: " + velYSlider.getValue()));

            JButton pauseBtn = new JButton("Pause");
            JButton showInfoBtn = new JButton("Show Info");

            controls.add(massLabel);
            controls.add(massSlider);
            controls.add(velXLabel);
            controls.add(velXSlider);
            controls.add(velYLabel);
            controls.add(velYSlider);
            controls.add(pauseBtn);
            controls.add(showInfoBtn);

            // Add bodies on click
            simPanel.setOnClick((x, y) -> {
                double mass = massSlider.getValue() * 1e12;
                double vx = velXSlider.getValue() * 0.01;
                double vy = velYSlider.getValue() * 0.01;
                bodies.add(new Body(new Vect(x, y), new Vect(vx, vy), mass));
            });

            // Pause/resume logic
            final boolean[] paused = {false};
            Timer timer = new Timer(16, e -> {
                if (!paused[0]) {
                    engine.update(0.5);
                    simPanel.repaint();
                }
            });
            timer.start();

            pauseBtn.addActionListener(e -> {
                paused[0] = !paused[0];
                pauseBtn.setText(paused[0] ? "Resume" : "Pause");
            });

            showInfoBtn.addActionListener(e -> simPanel.toggleInfo());

            frame.setLayout(new BorderLayout());
            frame.add(simPanel, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.EAST);
            frame.setVisible(true);
        });
    }
}
