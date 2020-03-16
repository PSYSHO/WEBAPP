package app.service;

import app.connection.Util;
import app.dao.DAO;
import app.entities.AirlinesType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AirlinesTypeService implements DAO<AirlinesType> {
    Util util = new Util();
    Connection connection = util.getConnection();

    public AirlinesTypeService() throws SQLException {
    }

    public void add(AirlinesType o) throws SQLException {
        PreparedStatement preparedStatement = null;
        AirlinesType airlinesType = (AirlinesType)o;
        String sql = "Insert INTO typeairlines(Type)VALUES(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, airlinesType.getType());
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

    public List<AirlinesType> getAll() {
        List<AirlinesType> airlinesTypeList = new ArrayList<AirlinesType>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID,Type FROM typeairlines";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AirlinesType airlinesType = new AirlinesType();
                airlinesType.setId(resultSet.getInt("Id"));
                airlinesType.setType(resultSet.getString("Type"));
                airlinesTypeList.add(airlinesType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlinesTypeList;
    }

    public AirlinesType getbyId(int id) {
        AirlinesType airlinesType = new AirlinesType();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID,Type FROM typeairlines WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (Integer) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            airlinesType.setId(resultSet.getInt("ID"));
            airlinesType.setType(resultSet.getString("Type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlinesType;
    }

    public void update(AirlinesType airlinesType) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE typeairlines set Type=? WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, airlinesType.getType());
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

    public void remove(AirlinesType airlinesType) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM typeairlines WHERE Id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, airlinesType.getId());
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
