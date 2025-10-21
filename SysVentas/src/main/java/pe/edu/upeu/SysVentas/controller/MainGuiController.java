package pe.edu.upeu.sysventas.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sysventas.dto.MenuMenuItenTO;
import pe.edu.upeu.sysventas.service.IMenuMenuItemDao;
import pe.edu.upeu.sysventas.utils.UtilsX;

import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

@Controller
public class MainGuiController {
    @Autowired
    private ApplicationContext context;
    Preferences userPrefs = Preferences.userRoot();
    UtilsX util = new UtilsX();
    Properties myresources = new Properties();
    @Autowired
    IMenuMenuItemDao mmiDao;
    @FXML
    private TabPane tabPaneFx;
    List<MenuMenuItenTO> lista;
    @FXML
    private BorderPane bp;
    @FXML
    private MenuBar menuBarFx;
    private Parent parent;
    Stage stage;
    @FXML
    private Menu menuEstilo=new Menu("Cambiar Estilo");
    ComboBox<String> comboBox = new ComboBox<>(
            javafx.collections.FXCollections.observableArrayList("Estilo por Defecto", "Estilo Oscuro", "Estilo Azul", "Estilo Verde", "Estilo Rosado") );
    CustomMenuItem customItem = new CustomMenuItem(comboBox);
    private Menu menuIdioma=new Menu("Idioma");
    ComboBox<String> comboBoxIdioma = new ComboBox<>(javafx.collections.FXCollections.observableArrayList("Español", "Ingles") );
    CustomMenuItem customItemIdioma = new CustomMenuItem(comboBoxIdioma);


}