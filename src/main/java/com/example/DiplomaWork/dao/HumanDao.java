package com.example.DiplomaWork.dao;

import com.example.DiplomaWork.domain.Human;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HumanDao {

    public static final String URL = "jdbc:h2:~/diplomaWork";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";
    public static final String INSERT_HUMAN = "INSERT INTO human(first_name, last_name, patronymic, gender, " +
            "year_of_birth, inn, city_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String FIND_ALL = "select hm.id, first_name, last_name, " +
            "patronymic, gender, year_of_birth, inn, ci.name, city_id " +
            "from human as hm left join city as ci where(city_id = ci.id) LIMIT 0,20";
    public static final String DELETE_HUMAN = "DELETE FROM HUMAN WHERE ID = ?";

    private Statement statement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    public void addHuman(Human human) throws SQLException {

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        preparedStatement = connection.prepareStatement(INSERT_HUMAN);

        preparedStatement.setString(1, human.getFirstName());
        preparedStatement.setString(2, human.getLastName());
        preparedStatement.setString(3, human.getPatronymic());
        preparedStatement.setString(4, human.getGender());
        preparedStatement.setInt(5, human.getYearOfBirth());
        preparedStatement.setInt(6, human.getInn());
        preparedStatement.setInt(7, human.getCity_id());

        preparedStatement.executeUpdate();

        connection.close();
    }

    public List<Human> findAllHuman() {

        List<Human> humanList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String patronymic = resultSet.getString("patronymic");
                String gender = resultSet.getString("gender");
                int year_of_birth = resultSet.getInt("year_of_birth");
                int inn = resultSet.getInt("inn");
                String city_name = resultSet.getString("city.name");
                int city_id = resultSet.getInt("city_id");


                Human human = new Human(id, first_name, last_name, patronymic, gender, city_name, year_of_birth, inn,
                        city_id);
                humanList.add(human);
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return humanList;
    }

    public void deleteById(int id) {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE_HUMAN);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}