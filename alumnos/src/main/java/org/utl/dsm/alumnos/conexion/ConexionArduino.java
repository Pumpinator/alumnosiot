package org.utl.dsm.alumnos.conexion;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class ConexionArduino {

    SerialPort serialPort;
    OutputStream outputStream;
    InputStream inputStream;

    public void abrir() {
        String port = "COM3";
        int vel = 9600;
        try {
            serialPort = SerialPort.getCommPort(port);
            serialPort.setBaudRate(vel);
            serialPort.setNumDataBits(0);
            serialPort.setNumStopBits(1);
            serialPort.setParity(1);
            serialPort.openPort();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void busData() {
        outputStream = serialPort.getOutputStream();
        inputStream = serialPort.getInputStream();
    }

    public void mandarData(String data) throws IOException {
        outputStream.write(data.getBytes());
        outputStream.flush();
    }

}
