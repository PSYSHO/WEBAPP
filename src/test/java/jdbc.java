import app.entities.*;
import app.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

public class jdbc {
    public static void main(String[] args) throws SQLException {
        City city = new City(),city1= new City(),city2 = new City();
        city.setNameCity("Samara");city1.setNameCity("Tokyo");city2.setNameCity("NewYork");
        CityService cityService = new CityService();
        //cityService.add(city2);
        Route route = new Route(),route1= new Route(),route2= new Route();
        route.setEndPoint(3);route.setStartPoint(2);route.setTime(30);route1.setStartPoint(1);route1.setEndPoint(2);route1.setTime(60);route2.setStartPoint(1);route2.setEndPoint(3);route2.setTime(210);
        RouteService routeService = new RouteService();
        //routeService.add(route2);
        AirlinesType airlinesType = new AirlinesType(),airlinesType1 = new AirlinesType(),airlinesType2 = new AirlinesType();
        airlinesType.setType("Грузовые");airlinesType1.setType("Пассажирские");airlinesType2.setType("Военные");
        AirlinesTypeService airlinesTypeService = new AirlinesTypeService();
        //airlinesTypeService.add(airlinesType2);
        Date date = new Date(System.currentTimeMillis());
        Plane plane = new Plane(),plane1  = new Plane(),plane2 = new Plane();
        plane.setNamePlane("A350");plane.setIdType(1);plane1.setNamePlane("АН255");plane1.setIdType(1);plane2.setNamePlane("МИГ3");plane2.setIdType(3);
        PlaneService planeService = new PlaneService();planeService.add(plane2);
        Airline airline = new Airline(),airline1 = new Airline(),airline2 = new Airline();
        route.setId(1);plane.setId(1);
        airline.setPlane(plane);airline.setRoute(route);airline.setData(date);
        AirlineService airlineService = new AirlineService();
        airlineService.add(airline);
    }
}
