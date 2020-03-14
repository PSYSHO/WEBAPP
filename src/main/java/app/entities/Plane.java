package app.entities;

import javax.servlet.annotation.WebServlet;

public class Plane {
    private int Id;
    private String NamePlane;
    private int IdType;

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setNamePlane(String namePlane) {
        NamePlane = namePlane;
    }

    public String getNamePlane() {
        return NamePlane;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public int getIdType() {
        return IdType;
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
