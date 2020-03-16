package app.entities;


import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.sql.Date;

public class Airline implements Serializable {
    private int Id;
    private Route Route;
    private Plane plane;
    private java.sql.Date data;
    public Airline(){
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setRoute(Route route) {
        Route = route;
    }

    public Route getRoute() {
        return Route;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane() {
        return plane;
    }


    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
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
