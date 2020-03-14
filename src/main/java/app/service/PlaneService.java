package app.service;

import app.dao.DAO;
import app.entities.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneService implements DAO<Plane> {
    Connection connection = app.connection.Connection.getConnection();
    PreparedStatement preparedStatement = null;

    public PlaneService() throws SQLException {
    }

    public void add(Plane plane) throws SQLException {
        String sql = "Insert INTO plane(NamePlane, IdType)VALUES(?,?)";
    try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,plane.getNamePlane());
        preparedStatement.setInt(2,plane.getIdType());
        preparedStatement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
        System.out.println("Ошибка добавления");
    }finally {
        if(preparedStatement!=null){preparedStatement.close();}
        if(connection!=null){connection.close();}
    }
    }

    public List<Plane> getAll() throws SQLException {
        List<Plane> planes = new ArrayList<Plane>();
        String sql = "SELECT ID,NamePlane,IdType FROM plane";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Plane plane = new Plane();
                plane.setId(resultSet.getInt("ID"));
                plane.setNamePlane(resultSet.getString("NamrPlane"));
                plane.setIdType(resultSet.getInt("IdType"));
                planes.add(plane);
            }
        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){preparedStatement.close();}
            if(connection!=null){connection.close();}
        }
        return planes;
    }

    public Plane getbyId(int id) {
        Plane plane = new Plane();
        String sql = "SELECT ID,NamePlane,IdType FROM plane WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            plane.setId(resultSet.getInt("Id"));
            plane.setNamePlane(resultSet.getString("NamePlane"));
            plane.setIdType(resultSet.getInt("IdType"));
        }catch(SQLException e){e.printStackTrace();}
        return plane;
    }

    public void update(Plane plane) throws SQLException {
        String sql = "UPDATE plane set NamePlane=?,IdType=? WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,plane.getNamePlane());
            preparedStatement.setInt(2,plane.getIdType());
            preparedStatement.setInt(3,plane.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){preparedStatement.close();}
            if(connection!=null){connection.close();}
        }
    }

    public void remove(Plane plane) throws SQLException {
        String sql ="DELETE FROM plane WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,plane.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){preparedStatement.close();}
            if(connection!=null){connection.close();}
        }
    }
}
