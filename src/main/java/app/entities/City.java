package app.entities;

public class City {
    private int Id;

    private String NameCity;

    public City() {
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setNameCity(String nameCity) {
        NameCity = nameCity;
    }

    public String getNameCity() {
        return NameCity;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
