package app.service;

import app.dao.DAO;
import app.entities.Airline;
import app.entities.AirlinesType;
import app.entities.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.connection.Connection.getConnection;

public class AirlineService implements DAO<Airline> {
    Connection connection = getConnection();
    PreparedStatement preparedStatement = null;
    CityService cityService;RouteService routeService;TypeAirlinesService t;

    public AirlineService() throws SQLException {
    }

    //todo переделать стейтмант чтоб он создавался один раз
    public void add(Airline airline) throws SQLException {
        String sql = "Insert INTO AIRLINES(Id,Route,TypeAirlines,Time,Date)VALUES(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, airline.getId());
            preparedStatement.setInt(2, Integer.parseInt(airline.getRoute()));
            preparedStatement.setInt(3, Integer.parseInt(airline.getTypeAirlines()));
            preparedStatement.setInt(4, airline.getTime());
            preparedStatement.setDate(5, airline.getData());
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
        String sql = "SELECT airlines.id, airlines.id, airlines.route, route.id, route.id, city1.name_city as City_from, city2.name_city as City_to, route.time_travel, flights.date, flights.time, flights.id_plane, plane.name_plane ,plane.id_type, typea.name_type From airlines.route inner join  airlines.city as city1 on route.id_from = city1.Id inner join airlines.city as city2 on route.id_to = city2.Id inner join airlines.flights on flights.id_route = route.Id inner join airlines.plane on plane.Id = flights.id_plane inner join airlines.typea on typea.Id = plane.id_type;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                Airline airline = new Airline();
                airline.setId(resultSet.getInt("ID"));
                Route route = routeService.getbyId(resultSet.getInt("ROUTE"));
                airline.setRoute(cityService.getRoute(route.getEndPoint(),route.getStartPoint()));
                AirlinesType airlinestype;airlinestype = t.getbyId(resultSet.getInt("TYPEAIRLINES"));
                airline.setTypeAirlines(airlinestype.getType());
                airline.setTime(resultSet.getInt("Time"));
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
            airline.setRoute(cityService.getRoute(route.getEndPoint(),route.getStartPoint()));
            airline.setTypeAirlines(String.valueOf(resultSet.getInt("TYPEAIRLINES")));
            airline.setTime(resultSet.getInt("Time"));
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
            preparedStatement.setInt(1, Integer.parseInt(airline.getRoute()));
            preparedStatement.setInt(2, Integer.parseInt(airline.getTypeAirlines()));
            preparedStatement.setInt(3, airline.getTime());
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
