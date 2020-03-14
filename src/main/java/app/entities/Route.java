package app.entities;

public class Route {
    private int Id;
    private int StartPoint;
    private int EndPoint;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setStartPoint(int startPoint) {
        StartPoint = startPoint;
    }

    public int getStartPoint() {
        return StartPoint;
    }

    public void setEndPoint(int endPoint) {
        EndPoint = endPoint;
    }

    public int getEndPoint() {
        return EndPoint;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
