public class Vect{
    double x;
    double y;
    Vect(){
        this.x = 0;
        this.y = 0;
    }
    Vect(double x,double y){
        this.x = x;
        this.y = y;
    }
    public double magnitude(){
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }
    public Vect add(Vect v) {
        return new Vect(this.x + v.x, this.y + v.y);
    }

    public Vect subtract(Vect v) {
        return new Vect(this.x - v.x, this.y - v.y);
    }
    public Vect multiply(double k){
        Vect v = new Vect(this.x*k,this.y*k);
        return v;
    }
    public double angle(Vect v){
        double a = v.y/v.x;
        return Math.atan(a);
    }
    public Vect unit_vector() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y);

        if (length == 0) {
            return new Vect(0, 0); // avoid dividing by zero
        }

        double newX = this.x / length;
        double newY = this.y / length;

        return new Vect(newX, newY);
    }

}