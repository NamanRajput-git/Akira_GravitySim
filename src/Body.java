import java.util.*;
public class Body {
    Vect r;
    Vect v;
    Vect a;
    double mass;
    public ArrayList<Vect> trail = new ArrayList<>();

    public Body(Vect r, Vect v, double mass) {
        this.r = r;
        this.v = v;
        this.mass = mass;
        this.a = new Vect();
    }

    public void force_applied(Vect force) {
        a = force.multiply(1 / mass);
        a = a.add(a);
    }

    public void update(double time) {
        v = v.add(a.multiply(time));
        r = r.add(v.multiply(time));
        a = new Vect();
    }

    public void updateTrail(int maxTrailSize) {
        trail.add(new Vect(r.x, r.y));
        if (trail.size() > maxTrailSize) {
            trail.remove(0); // Keep only recent points
        }
    }
}
