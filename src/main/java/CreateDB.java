import app.connection.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB {
    public static void main(String[] args) throws SQLException {
        String Sql = "create table city\n" +
                "(\n" +
                "    Id       int auto_increment\n" +
                "        primary key,\n" +
                "    NameCity varchar(20) null\n" +
                ");\n" +
                "\n" +
                "create table route\n" +
                "(\n" +
                "    Id         int auto_increment\n" +
                "        primary key,\n" +
                "    StartPoint int null,\n" +
                "    EndPoint   int null,\n" +
                "    constraint route_ibfk_1\n" +
                "        foreign key (StartPoint) references city (Id),\n" +
                "    constraint route_ibfk_2\n" +
                "        foreign key (EndPoint) references city (Id)\n" +
                ");\n" +
                "\n" +
                "create index EndPoint\n" +
                "    on route (EndPoint);\n" +
                "\n" +
                "create index StartPoint\n" +
                "    on route (StartPoint);\n" +
                "\n" +
                "create table typeairlines\n" +
                "(\n" +
                "    Id   int auto_increment\n" +
                "        primary key,\n" +
                "    Type varchar(20) null\n" +
                ");\n" +
                "\n" +
                "create table airlines\n" +
                "(\n" +
                "    Id           int auto_increment\n" +
                "        primary key,\n" +
                "    Route        int  null,\n" +
                "    TypeAirlines int  null,\n" +
                "    Time         int  null,\n" +
                "    Date         date null,\n" +
                "    constraint airlines_ibfk_1\n" +
                "        foreign key (Route) references route (Id),\n" +
                "    constraint airlines_ibfk_2\n" +
                "        foreign key (TypeAirlines) references typeairlines (Id)\n" +
                ");\n" +
                "\n" +
                "create index Route\n" +
                "    on airlines (Route);\n" +
                "\n" +
                "create index TypeAirlines\n" +
                "    on airlines (TypeAirlines);\n" +
                "\n" +
                "create table plane\n" +
                "(\n" +
                "    Id        int auto_increment\n" +
                "        primary key,\n" +
                "    NamePlane varchar(20) null,\n" +
                "    IdType    int         null,\n" +
                "    constraint plane_typeairlines_Id_fk\n" +
                "        foreign key (IdType) references typeairlines (Id)\n" +
                ");\n" +
                "\n" +
                "\n";
        PreparedStatement ps = null;
        Util util = new Util();
        Connection connection = util.getConnection();
        ps = connection.prepareStatement(Sql);
        ps.executeUpdate();
    }
}
