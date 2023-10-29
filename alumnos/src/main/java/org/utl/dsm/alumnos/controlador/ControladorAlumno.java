package org.utl.dsm.alumnos.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.utl.dsm.alumnos.conexion.ConexionArduino;
import org.utl.dsm.alumnos.conexion.ConexionSQL;
import org.utl.dsm.alumnos.modelo.ModeloAlumno;

import java.io.IOException;
import java.sql.*;

public class ControladorAlumno {

    ConexionArduino conexionArduino;

    public ControladorAlumno() {
        this.conexionArduino = new ConexionArduino();
        conexionArduino.abrir();
        conexionArduino.busData();
    }

    public ObservableList<ModeloAlumno> obtenerTodos() throws SQLException {
        ObservableList<ModeloAlumno> alumnos = FXCollections.observableArrayList();
        String query = "SELECT * FROM alumnos;";
        ConexionSQL conexionSQL = new ConexionSQL();
        Connection connection = conexionSQL.abrir();
        Statement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            ModeloAlumno alumno = new ModeloAlumno();
            alumno.setId(resultSet.getInt("id"));
            alumno.setNombre(resultSet.getString("nombre"));
            alumno.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
            alumno.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
            alumno.setMatricula(resultSet.getString("matricula"));
            alumno.setImagen("");
            alumnos.add(alumno);
        }
        resultSet.close();
        statement.close();
        connection.close();
        conexionSQL.cerrar();
        return alumnos;
    }
    public void mostrar(ModeloAlumno alumno) throws IOException {
        if(alumno != null) {
            conexionArduino.mandarData(alumno.getMatricula());
        } else {
            conexionArduino.mandarData(" ");
        }
    }

}
