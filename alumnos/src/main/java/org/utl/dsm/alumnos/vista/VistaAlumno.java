package org.utl.dsm.alumnos.vista;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import org.utl.dsm.alumnos.conexion.ConexionArduino;
import org.utl.dsm.alumnos.controlador.ControladorAlumno;
import org.utl.dsm.alumnos.modelo.ModeloAlumno;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class VistaAlumno implements Initializable {

    @FXML
    private TextField txtMatricula;

    @FXML
    private TableColumn<ModeloAlumno, String> colMatricula;

    @FXML
    private TableColumn<ModeloAlumno, String> colNombre;

    @FXML
    private TableView<ModeloAlumno> tableAlumnos;
    private ObservableList<ModeloAlumno> alumnos;
    private ControladorAlumno controladorAlumno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alumnos = FXCollections.observableArrayList();
        controladorAlumno = new ControladorAlumno();
        iniciarTabla();
        escuharCambios();
    }

    public void iniciarTabla()  {
        colNombre.setCellValueFactory(param -> new SimpleObjectProperty<>(
                    param.getValue().getNombre()
                    + " " + param.getValue().getApellidoPaterno()
                    + " " + param.getValue().getApellidoMaterno()
            ));
        colMatricula.setCellValueFactory(param -> new SimpleObjectProperty<>(
                    param.getValue().getMatricula()
            ));
        try {
            alumnos = controladorAlumno.obtenerTodos(); // OBTENEMOS DE LA BASE DE DATOS NUESTRA LISTA DE ALUMNOS
        } catch (SQLException e) {
            e.printStackTrace();
            // NO HAY CONEXION CON LA BD
        }
        alumnos.sort(Comparator.comparing(ModeloAlumno::getNombre)); // ORDENAMOS LA LISTA DE LA A - Z
        tableAlumnos.setItems(alumnos);
    }

    public void buscar() {
        String matricula = txtMatricula.getText(); // OBTENEMOS LA CADENA DEL CAMPO DE TEXTO
        try {
            ModeloAlumno alumnoBuscado = null; // CREAMOS UN ALUMNO
            for (ModeloAlumno alumno : alumnos) { // ITERAMOS SOBRE LA LISTA PARA BUSCAR EL ALUMNO
                if (alumno.getMatricula().equals(matricula)) { // BUSCAMOS POR MATRICULA
                    alumnoBuscado = alumno; // ASIGNAMOS LA COINCIDENCIA EN NUESTRO OBJETO
                }
            }
            if(alumnoBuscado != null) {
                alumnos.remove(alumnoBuscado);
                alumnos.add(0, alumnoBuscado);
                tableAlumnos.setItems(alumnos);
                controladorAlumno.mostrar(alumnoBuscado);
                // SI EL OBJETO COINCIDE LO POSICIONAMOS ARRIBA EN LA LISTA
            } else {
                // SI NO, NO ENCONTRAMOS AL USUARIO
                controladorAlumno.mostrar(alumnoBuscado);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // NO HAY CONEXION CON EL ARDUINO
        }
        txtMatricula.clear(); // LIMPIAMOS EL CAMPO DE TEXTO DESPUES DE CADA BUSQUEDA
    }

    public void escuharCambios() {
        txtMatricula.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                buscar();
            }
        });
    }

}