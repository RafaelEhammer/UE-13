package Controller;

import Model.Person;
import Model.Phonebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private Label ste;
    private Phonebook phonebook;
    private int Site;

    public static Controller controller;

    public static void show(Stage stage) throws Exception{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("/Controller/sample.fxml"));
            Parent root = fxmlLoader.load();

            stage.setTitle("Telephone-List");
            stage.setScene(new Scene(root));

            controller = fxmlLoader.getController();

            controller.closeStage(stage);
            stage.show();
        } catch (IOException e){
            System.err.println("Something wrong with TelephonelistV.fxml: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void displaySite(int index)
    {
        Person p = phonebook.getPerson(index - 1);
        name.setText(p.getName());
        phone.setText(p.getNumber());
        address.setText(p.getAddress());
        ste.setText("Page " + (index) + "/" + phonebook.getSize());
    }
    public void AddOnAction()
    {
        phonebook.newSite();
        Site = phonebook.getSize();
        displaySite(Site);
    }
    public void nextSite()
    {
        if (Site < phonebook.getSize())
        {
            Site++;
            displaySite(Site);
        }
        else
        {
            Site = 1;
            displaySite(Site);
        }
    }
    public void PreviousSite()
    {
        if (Site > 1)
        {
            Site--;
            displaySite(Site);
        }
        else
        {
            Site = phonebook.getSize();
            displaySite(Site);
        }
    }
    public void save()
    {
        phonebook.saveChanges(name.getText(), address.getText(), phone.getText(), Site-1);
    }
    public void delete()
    {
        phonebook.delete(Site-1);
        Site--;
        displaySite(Site);
    }
    public void saveCSV()
    {
        phonebook.save();
    }
    public void closeStage(Stage stage)
    {
        stage.setOnCloseRequest(
                windowEvent -> {
                    phonebook.save();
                    stage.close();
                    System.out.println("The Phonebook was successfully saved!");
                });
    }
    public void loadCSV()
    {
        phonebook.load();
        Site = 1;
        displaySite(Site);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        phonebook = new Phonebook();
        Site = 1;
        displaySite(Site);
        loadCSV();
    }
}
