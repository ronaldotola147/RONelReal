package pe.edu.upeu.sysventas.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sysventas.components.ColumnInfo;
import pe.edu.upeu.sysventas.components.ComboBoxAutoComplete;
import pe.edu.upeu.sysventas.components.TableViewHelper;
import pe.edu.upeu.sysventas.components.Toast;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.service.ICategoriaService;
import pe.edu.upeu.sysventas.service.IMarcaService;
import pe.edu.upeu.sysventas.service.IUnidadMedidaService;
import pe.edu.upeu.sysventas.service.ProductoIService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Controller

public class ProductoController {


    public void validarFormulario(ActionEvent actionEvent) {
    }

    @Controller
    public class productoController {
        @FXML
        TextField txtNombreProducto, txtPUnit,
                txtPUnitOld, txtUtilidad, txtStock, txtStockOld,
                txtFiltroDato;
        @FXML
        ComboBox<ComboBoxOption> cbxMarca;
        @FXML
        ComboBox<ComboBoxOption> cbxCategoria;
        @FXML
        ComboBox<ComboBoxOption> cbxUnidMedida;
        @FXML
        private TableView<Producto> tableView;
        @FXML
        Label lbnMsg, idPrueba;
        @FXML
        private AnchorPane miContenedor;
        Stage stage;
        @Autowired
        IMarcaService ms;
        @Autowired
        ICategoriaService cs;
        @Autowired
        ProductoIService ps;
        @Autowired
        IUnidadMedidaService ums;
        private Validator validator;
        ObservableList<Producto> listarProducto;
        Producto formulario;
        Long idProductoCE = 0L;

        private void filtrarProductos(String filtro) {
            if (filtro == null || filtro.isEmpty()) {
                tableView.getItems().clear();
                tableView.getItems().addAll(listarProducto);
            } else {
                String lowerCaseFilter = filtro.toLowerCase();
                List<Producto> productosFiltrados = listarProducto.stream()
                        .filter(producto -> {
                            if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            }
                            if (String.valueOf(producto.getPu()).contains(lowerCaseFilter)) {
                                return true;
                            }
                            if (String.valueOf(producto.getUtilidad()).contains(lowerCaseFilter)) {
                                return true;
                            }
                            if
                            (producto.getMarca().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            }
                            if
                            (producto.getCategoria().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            }
                            return false; // Si no coincide con ningún campo
                        })
                        .collect(Collectors.toList());
                tableView.getItems().clear();
                tableView.getItems().addAll(productosFiltrados);
            }
        }

        public void listar() {
            try {
                tableView.getItems().clear();
                listarProducto = FXCollections.observableArrayList(ps.findAll(  ));
                tableView.getItems().addAll(listarProducto);
                txtFiltroDato.textProperty().addListener((observable, oldValue,
                                                          newValue) -> {
                    filtrarProductos(newValue);
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @FXML
        public void initialize() {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000),
                    event -> {
                        stage = (Stage) miContenedor.getScene().getWindow();
                        if (stage != null) {
                            System.out.println("El título del stage es: " +
                                    stage.getTitle());
                        } else {
                            System.out.println("Stage aún no disponible.");
                        }
                    }));
            timeline.setCycleCount(1);
            timeline.play();
            cbxMarca.getItems().addAll(ms.listarCombobox());
            cbxMarca.setOnAction(event -> {
                ComboBoxOption selectedProduct =
                        cbxMarca.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    String selectedId = selectedProduct.getKey(); // Obtener el ID
                    System.out.println("ID del producto seleccionado: " +
                            selectedId);
                }
            });
            new ComboBoxAutoComplete<>(cbxMarca);
            cbxCategoria.getItems().addAll(cs.listarCombobox());
            cbxCategoria.setOnAction(event -> {
                ComboBoxOption selectedProduct =
                        cbxCategoria.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    String selectedId = selectedProduct.getKey(); // Obtener el ID
                    System.out.println("ID del producto seleccionado: " +
                            selectedId);
                }
            });
            new ComboBoxAutoComplete<>(cbxCategoria);
            cbxUnidMedida.getItems().addAll(ums.listarCombobox());
            cbxUnidMedida.setOnAction(event -> {
                ComboBoxOption selectedProduct =
                        cbxUnidMedida.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    String selectedId = selectedProduct.getKey(); // Obtener el ID
                    System.out.println("ID del producto seleccionado: " +
                            selectedId);
                }
            });
            new ComboBoxAutoComplete<>(cbxUnidMedida);
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
            TableViewHelper<Producto> tableViewHelper = new TableViewHelper<>();
            LinkedHashMap<String, ColumnInfo> columns = new LinkedHashMap<>();
            columns.put("ID Pro.", new ColumnInfo("idProducto", 60.0));
            columns.put("Nombre Producto", new ColumnInfo("nombre", 200.0));
            columns.put("P. Unitario", new ColumnInfo("pu", 150.0));
            columns.put("Utilidad", new ColumnInfo("utilidad", 100.0));
            columns.put("Marca", new ColumnInfo("marca.nombre", 200.0));
            columns.put("Categoria", new ColumnInfo("categoria.nombre", 200.0));
            Consumer<Producto> updateAction = (Producto producto) -> {
                System.out.println("Actualizar: " + producto);
                editForm(producto);
            };
            Consumer<Producto> deleteAction = (Producto producto) ->
            {
                System.out.println("Actualizar: " + producto);
                ps.delete(producto.getIdProducto());
                double with = stage.getWidth() / 1.5;
                double h = stage.getHeight() / 2;
                Toast.showToast(stage, "Se eliminó correctamente!!", 2000, with,
                        h);
                listar();
            };
            tableViewHelper.addColumnsInOrderWithSize(tableView,
                    columns, updateAction, deleteAction);
            tableView.setTableMenuButtonVisible(true);
            listar();
        }

        public void limpiarError() {
            List<Control> controles = List.of(
                    txtNombreProducto, txtPUnit, txtPUnitOld,
                    txtUtilidad, txtStock, txtStockOld,
                    cbxMarca, cbxCategoria, cbxUnidMedida
            );
            controles.forEach(c -> c.getStyleClass().remove("text-field-error"));
        }

        public void clearForm() {
            txtNombreProducto.clear();
            txtPUnit.clear();
            txtPUnitOld.clear();
            txtUtilidad.clear();
            txtStock.clear();
            txtStockOld.clear();
            cbxMarca.getSelectionModel().clearSelection();
            cbxCategoria.getSelectionModel().clearSelection();
            cbxUnidMedida.getSelectionModel().clearSelection();
            idProductoCE = 0L;
            limpiarError();
        }

        public void editForm(Producto producto) {
            txtNombreProducto.setText(producto.getNombre());
            txtPUnit.setText(producto.getPu().toString());
            txtPUnitOld.setText(producto.getPuOld().toString());
            txtUtilidad.setText(producto.getUtilidad().toString());
            txtStock.setText(producto.getStock().toString());
            txtStockOld.setText(producto.getStockOld().toString());
            cbxMarca.getSelectionModel().select(
                    cbxMarca.getItems().stream()
                            .filter(marca ->
                                    Long.parseLong(marca.getKey()) == producto.getMarca().getIdMarca()).findFirst().orElse(null)
            );
            cbxCategoria.getSelectionModel().select(
                    cbxCategoria.getItems().stream()
                            .filter(categoria ->
                                    Long.parseLong(categoria.getKey()) == producto.getCategoria().getIdCategoria()).findFirst().orElse(null));
            cbxUnidMedida.getSelectionModel().select(
                    cbxUnidMedida.getItems().stream().filter(unidad -> Long.parseLong(unidad.getKey()) == producto.getUnidadMedida().getIdUnidad()).findFirst().orElse(null));
            idProductoCE = producto.getIdProducto();
            limpiarError();
        }


    }
}