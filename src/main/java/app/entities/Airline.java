package app.entities;


import java.io.Serializable;
import java.sql.Date;

public class Airline implements Serializable {
    private int Id;
    private String Route;
    private String TypeAirlines;
    private int Time;
    private java.sql.Date data;
    //todo сделать так чтоб в обьекте были не id
    public Airline(){
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getRoute() {
        return Route;
    }

    public void setTypeAirlines(String typeAirlines) {
        TypeAirlines = typeAirlines;
    }

    public String getTypeAirlines() {
        return TypeAirlines;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
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
