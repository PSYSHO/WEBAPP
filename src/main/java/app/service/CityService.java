package app.service;

import app.dao.DAO;
import app.entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.connection.Connection.getConnection;

public class CityService implements DAO<City> {
    PreparedStatement preparedStatement = null;
    Connection connection  = getConnection();

    public CityService() throws SQLException {
    }

    public void add(City city) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "Insert INTO city(NameCity)VALUES(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,city.getNameCity());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement!=null){
                preparedStatement.close();
            }if (connection!=null){
                connection.close();
            }
        }
    }

    public List<City> getAll() throws SQLException {
        List<City> cityList = new ArrayList<City>();
        String sql = "SELECT ID,NAMECITY FROM CITY";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("ID"));
                city.setNameCity(resultSet.getString("NAMECITY"));
                cityList.add(city);
            }
        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){
                preparedStatement.close();
            }if(connection!=null){
                connection.close(); }
        }
        return cityList;
    }

    public City getbyId(int id) throws SQLException {
        City city = new City();
        String sql = "SELECT ID,NAMECITY FROM city WHERE ID=?";
        try {
            preparedStatement  = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            city.setId(resultSet.getInt("ID"));
            city.setNameCity(resultSet.getString("NAMECITY"));
        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){
                preparedStatement.close(); }
            if(connection!=null){
                connection.close();}
        }
        return city;
    }

    public void update(City city) throws SQLException {
        String sql = "UPDATE city set NameCity=? WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,city.getNameCity());
            preparedStatement.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}finally {
            if(preparedStatement!=null){preparedStatement.close();}
            if(connection!=null){connection.close();}
        }
    }

    public void remove(City city) {
    PreparedStatement preparedStatement = null;
        String sql ="DELETE FROM city WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,city.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){e.printStackTrace();}
    }
    public String getRoute(int Eid,int Sid) throws SQLException {
       CityService cityService = null;
        String Start="";String End ="";
       Start = cityService.getbyId(Sid).getNameCity();
       End = cityService.getbyId(Eid).getNameCity();
       String str = Start +"  |  "+End;
       return str;
    }
}
