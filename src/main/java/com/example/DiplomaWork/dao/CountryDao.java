package com.example.DiplomaWork.dao;

import com.example.DiplomaWork.domain.City;
import com.example.DiplomaWork.domain.Country;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDao {

    public static final String URL = "jdbc:h2:~/diplomaWork";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    public static final String INSERT_COUNTRY = "INSERT INTO country(name, continent, residents," +
            "gdp) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM country LIMIT 0,20";
    public static final String DELETE_COUNTRY = "DELETE FROM COUNTRY WHERE ID = ?";
    public static final String SELECT_ALL_CITY_BY_COUNTRY_NAME = "select * from city as ci left join country as co " +
            "on(ci.country_id = co.id) where co.name Like ";
    public static final String SELECT_CITY_CAPITAL_BY_COUNTRY_NAME = "select * from city as ci left join country as co " +
            "on(ci.country_id = co.id) where ci.capital = 1 and co.name Like ";

    private Statement statement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    public void addCountry(Country country) throws SQLException {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(INSERT_COUNTRY);

            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(3, country.getContinent());
            preparedStatement.setInt(2, country.getResidents());
            preparedStatement.setInt(4, country.getGdp());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Country> findAllCountry() {
        ArrayList<Country> countryArrayList = new ArrayList<>();

        try{
            connectionStatementResultSet(SELECT_ALL);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String continent = resultSet.getString("continent");
                int residents = resultSet.getInt("residents");
                int gdp = resultSet.getInt("gdp");

                Country country = new Country(id, name, continent, residents, gdp);
                countryArrayList.add(country);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countryArrayList;
    }

    public void deleteById(int id) {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE_COUNTRY);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<City> getAllCityByCountryName(String country) {

        List<City> cityList = new ArrayList<>();

        try{
            connectionStatementResultSet(SELECT_ALL_CITY_BY_COUNTRY_NAME + "'%" + country + "%'");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int country_id = resultSet.getInt("country_id");
                int residents = resultSet.getInt("residents");
                boolean capital = resultSet.getBoolean("capital");


                City city = new City(id, name, country_id, residents, capital);
                cityList.add(city);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cityList;
    }

    public City getCapitalByCountryName(String country) {

        City city = null;

        try{
            connectionStatementResultSet(SELECT_CITY_CAPITAL_BY_COUNTRY_NAME + "'%" + country + "%'");

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int country_id = resultSet.getInt("country_id");
            int residents = resultSet.getInt("residents");
            boolean capital = resultSet.getBoolean("capital");

            city = new City(id, name, country_id, residents, capital);


            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return city;
    }


    private void connectionStatementResultSet(String textSql) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        statement = connection.createStatement();

        resultSet = statement.executeQuery(textSql);
    }
}
