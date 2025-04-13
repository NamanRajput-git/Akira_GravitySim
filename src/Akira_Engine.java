import java.util.ArrayList;
public class Akira_Engine {
        public ArrayList<Body> bodies = new ArrayList<>();
        public static final double G = 6.67430e-11;

    public Akira_Engine(ArrayList<Body> bodies) {
        this.bodies = bodies;
    }

    public void addBody(Body body) {
            bodies.add(body);
        }
        public void update(double time) {
            // Compute gravitational forces between all pairs
            for (int i = 0; i < bodies.size(); i++) {
                Body a = bodies.get(i);
                for (int j = 0; j < bodies.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    Body b = bodies.get(j);
                    Vect position_vect = b.r.subtract(a.r);
                    double distance = position_vect.magnitude();
                    if (distance == 0){
                        continue;
                    }
                    double forceMagnitude = G * a.mass * b.mass / (distance * distance);
                    Vect force = position_vect.unit_vector().multiply(forceMagnitude);
                    a.force_applied(force);
                }
            }
            // Update positions of all bodies
            for (int i = 0; i < bodies.size(); i++) {
                bodies.get(i).update(time);
            }
            for (Body body : bodies) {
                body.v = body.v.add(body.a.multiply(time));
                body.r = body.r.add(body.v.multiply(time));
                body.a = new Vect();
                body.updateTrail(100); // 🌀 save last 100 positions
            }

        }
    }

