import java.util.ArrayList;

//public class Main {
//
//        public static void main(String[] args) {
//            Akira_Engine engine = new Akira_Engine();
//
//            Body earth = new Body(new Vect(0, 0), new Vect(0, 0), 5.972e24);
//            Body moon = new Body(new Vect(384400000, 0), new Vect(0, 1022), 7.348e22);
//
//            engine.addBody(earth);
//            engine.addBody(moon);
//
//            double time_interval = 60;
//
//            for (int i = 0; i < 10000;i++) {
//                engine.update(time_interval);
//                System.out.printf("Step %d - Moon Position: (%.2f, %.2f)%n", i, moon.r.x, moon.r.y);
//            }
//        }
//    }
