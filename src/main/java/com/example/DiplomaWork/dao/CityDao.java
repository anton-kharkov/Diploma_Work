package com.example.DiplomaWork.dao;

import com.example.DiplomaWork.domain.City;
import com.example.DiplomaWork.domain.Human;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDao {

    public static final String URL = "jdbc:h2:~/diplomaWork";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    public static final String INSERT_CITY = "INSERT INTO city(name,country_id," +
            "residents, capital ) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ALL = "select * from city LIMIT 0,20";
    public static final String DELETE_CITY = "DELETE FROM CITY WHERE ID = ?";
    public static final String SELECT_ALL_HUMAN_BY_CITY_NAME = "select * from human as hm left join city as ci on" +
            "(hm.city_id = ci.id) where ci.name Like ";



    private Statement statement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;


    public void addCity(City city) {

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(INSERT_CITY);

            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry_id());
            preparedStatement.setInt(4, city.getResidents());
            preparedStatement.setBoolean(3, city.isCapital());

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<City> findAllCity() {
        ArrayList<City> cityArrayList = new ArrayList<>();

        try{
            connectionStatementResultSet(SELECT_ALL);

            while (resultSet.next()){
                int id = resultSet.getInt("city_id");
                String name = resultSet.getString("name");
                int country = resultSet.getInt("country_id");
                int residents = resultSet.getInt("residents");
                boolean capital = resultSet.getBoolean("capital");

                City city = new City(id, name, country, residents, capital);
                cityArrayList.add(city);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cityArrayList;
    }

    public void deleteById(int id) {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE_CITY);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Human> getAllHumanByCityName(String city) {
        List<Human> humansList = new ArrayList<>();

        try{
            connectionStatementResultSet(SELECT_ALL_HUMAN_BY_CITY_NAME + "'%" + city + "%'");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String patronymic = resultSet.getString("patronymic");
                String gender = resultSet.getString("gender");
                String city_name = resultSet.getString("city.name");
                int year_of_birth = resultSet.getInt("year_of_birth");
                int inn = resultSet.getInt("inn");
                int city_id = resultSet.getInt("city_id");


                Human human = new Human(id, first_name, last_name, patronymic, gender, city_name,
                        year_of_birth, inn, city_id);
                humansList.add(human);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return humansList;
    }

    private void connectionStatementResultSet(String textSql) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        statement = connection.createStatement();

        resultSet = statement.executeQuery(textSql);
    }
}
