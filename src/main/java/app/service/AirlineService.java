package app.service;

import app.connection.Util;
import app.dao.DAO;
import app.entities.Airline;
import app.entities.AirlinesType;
import app.entities.Plane;
import app.entities.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class AirlineService implements DAO<Airline> {
    Util util = new Util();
    Connection connection = util.getConnection();
    PreparedStatement preparedStatement = null;
    CityService cityService;RouteService routeService;PlaneService planeService;
    AirlinesTypeService t;

    public AirlineService() throws SQLException {
    }

    //todo переделать стейтмант чтоб он создавался один раз
    public void add(Airline airline) throws SQLException {
        String sql = "Insert INTO AIRLINES(Id,Route,Plane,Date)VALUES(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, airline.getId());
            preparedStatement.setInt(2, airline.getRoute().getId());
            preparedStatement.setInt(3, airline.getPlane().getId());
            preparedStatement.setDate(4, airline.getData());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public List<Airline> getAll() throws SQLException {
        List<Airline> airlineList = new ArrayList<Airline>();
        String sql = "SELECT airlines.id, airlines.id, airlines.route, route.id, route.id, city1.namecity as City_from, city2.namecity as City_to, route.time, airlines.date, route.Time, airlines.Plane, plane.NamePlane ,plane.idType, typeairlines.Type From flights.route inner join  flights.city as city1 on route.StartPoint = city1.Id inner join airlines.city as city2 on route.EndPoint = city2.Id inner join flights.airlines on airlines.Route = route.Id inner join flights.plane on plane.Id = airlines.plane inner join flights.typeairlines on typeairlines.Id = plane.idType\n";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Airline airline = new Airline();
                airline.setId(resultSet.getInt("ID"));
                airline.setRoute(routeService.getbyId(resultSet.getInt("ROUTE")));
                airline.setPlane(planeService.getbyId(resultSet.getInt("PLANE")));
                airline.setData(resultSet.getDate("DATE"));
                airlineList.add(airline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return airlineList;
    }


    public Airline getbyId(int id) throws SQLException {
        String sql = "SELECT ID,ROUTE,TYPEAIRLINES,TIME,DATE FROM AIRLINES WHERE ID=?";
        Airline airline = new Airline();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            airline.setId(resultSet.getInt("ID"));
            Route route = routeService.getbyId(resultSet.getInt("ROUTE"));
            airline.setRoute(routeService.getbyId(resultSet.getInt("Route")));
            airline.setPlane(planeService.getbyId(resultSet.getInt("Plane")));
            airline.setData(resultSet.getDate("DATE"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return airline;
    }

    public void update(Airline airline) throws SQLException {
        String sql = "UPDATE AIRLINES set ROUTE=?,TYPEAIRLINES=?,TIME=?,DATE=? WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, airline.getRoute().getId());
            preparedStatement.setInt(2, airline.getRoute().getId());
            preparedStatement.setDate(4, airline.getData());
            preparedStatement.setInt(5, airline.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void remove(Airline airline) throws SQLException {
        String sql = "DELETE FROM AIRLINES WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, airline.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
