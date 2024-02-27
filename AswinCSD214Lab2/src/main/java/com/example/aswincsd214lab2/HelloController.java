package com.example.aswincsd214lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField AnoId;
    public TextField AnoName;
    public TextField AnoSport;
    public TextField AnoRank;
    @FXML
    private TableView<Players> player;
    @FXML
    private TableColumn<Players, Integer> id;
    @FXML
    private TableColumn<Players, String> name;
    @FXML
    private TableColumn<Players, String> sport;
    @FXML
    private TableColumn<Players, Integer> rank;

    ObservableList<Players> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Players, Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Players, String>("name"));
        sport.setCellValueFactory(new
                PropertyValueFactory<Players, String>("sport"));
        rank.setCellValueFactory(new
                PropertyValueFactory<Players, Integer>("rank"));

        player.setItems(list);
    }

    @FXML
    protected void ViewData(){populateTable();}
    public void populateTable() {

        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/order";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `players`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int PlayerID = resultSet.getInt("PlayerID");
                String Name = resultSet.getString("Name");
                String Sport = resultSet.getString("Sport");
                int Rank = resultSet.getInt("Rank");
                player.getItems().add(new Players(PlayerID, Name, Sport,Rank));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable(ActionEvent actionEvent) {

        int Id = Integer.parseInt(AnoId.getText());
        String Name = AnoName.getText();
        String Sport = AnoSport.getText();
        int Rank = Integer.parseInt(AnoRank.getText());

        UpdateData(Id, Name, Sport, Rank);
    }

    public void UpdateData(int Id, String Name, String Sport, int Rank){

        String jdbcUrl = "jdbc:mysql://localhost:3306/order";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `players` SET `Name`='"+Name+"',`Sport`='"+Sport+"',`Rank`='"+Rank+"' WHERE `PlayerID`='"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();
            AnoId.setText(" ");
            AnoName.setText(" ");
            AnoRank.setText(" ");
            AnoSport.setText(" ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertTable(ActionEvent actionEvent) {

        String Name = AnoName.getText();
        String Sport = AnoSport.getText();
        int Rank = Integer.parseInt(AnoRank.getText());

        InsertData(Name, Sport, Rank);
    }

    public void InsertData(String Name, String Sport, int Rank){

        String jdbcUrl = "jdbc:mysql://localhost:3306/order";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `players`( `Name`, `Sport`, `Rank`) VALUES ('"+Name+"','"+Sport+"','"+Rank+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();
            AnoName.setText(" ");
            AnoSport.setText(" ");
            AnoRank.setText(" ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteTable(ActionEvent actionEvent) {

        int Id=Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/order";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `players` WHERE PlayerID='"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();
            AnoId.setText(" ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadTable(ActionEvent actionEvent) {

        int Id= Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/order";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `players` WHERE PlayerID='"+Id+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int PlayerID = resultSet.getInt("PlayerID");
                String Name = resultSet.getString("Name");
                String Sport = resultSet.getString("Sport");
                int Rank = resultSet.getInt("Rank");

                AnoName.setText(Name);
                AnoSport.setText(Sport);
                AnoRank.setText(String.valueOf(Rank));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}