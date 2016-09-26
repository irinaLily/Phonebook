package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import searchcontacts.SearchPerson;
import searchcontacts.SearchPersonImpl;
import workToFile.ReadFile;
import workToFile.WriteFile;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(new Scene(root, 800,700));
        primaryStage.show();
    }



    public static void main(String[] args) {
        SearchPerson searchPerson =new SearchPersonImpl();
        WriteFile writeFile=new WriteFile();
        ReadFile readFile=new ReadFile();
        searchPerson.fillTestData();
        writeFile.writeCollectionsperson(searchPerson.getPersonSet());

        launch(args);
        readFile.readPerson("output.json");



    }
}
