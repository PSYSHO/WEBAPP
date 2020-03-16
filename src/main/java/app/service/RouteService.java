package app.service;

import app.connection.Util;
import app.dao.DAO;
import app.entities.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RouteService  implements DAO<Route> {
    Util util = new Util();
    Connection connection = util.getConnection();
    PreparedStatement preparedStatement = null;

    public RouteService() throws SQLException {
    }

    public int getid(String NameCity){
        String sql = "SELECT ID FROM city WHERE NameCity=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"NameCity");
            ResultSet resultSet = preparedStatement.executeQuery();
             i = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void add(Route route) throws SQLException {
        String sql = "Insert INTO route(startpoint, endpoint,Time)VALUES(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, route.getStartPoint());
            preparedStatement.setInt(2, route.getEndPoint());
            preparedStatement.setInt(3,route.getTime());
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

    public List<Route> getAll() throws SQLException {
        List<Route> routes = new ArrayList<Route>();
        String sql = "SELECT ID,StartPoint,EndPoint FROM route";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt("Id"));
                route.setStartPoint(resultSet.getInt("StartPoint"));
                route.setEndPoint(resultSet.getInt("EndPoint"));
                routes.add(route);
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
        return routes;
    }

    public Route getbyId(int id) throws SQLException {
        Route route = new Route();
        String sql = "SELECT ID,StartPoint,EndPoint FROM route WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            route.setId(resultSet.getInt("Id"));
            route.setStartPoint(resultSet.getInt("StartPoint"));
            route.setEndPoint(resultSet.getInt("EndPoint"));
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
        return route;
    }

    public void update(Route route) throws SQLException {
        String sql = "UPDATE route set StartPoint=?,EndPoint=? WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, route.getStartPoint());
            preparedStatement.setInt(2, route.getEndPoint());
            preparedStatement.setInt(3, route.getId());
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

    public void remove(Route route) throws SQLException {
        String sql = "DELETE FROM route WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, route.getId());
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
