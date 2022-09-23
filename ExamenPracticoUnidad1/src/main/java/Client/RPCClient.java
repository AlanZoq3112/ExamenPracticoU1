package Client;

import Server.Metodos;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class RPCClient {
    public static void main(String[] args) throws MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        int opc;

        Scanner sc = new Scanner(System.in);
        System.out.println("EXAMEN");
        System.out.println("Que accion deseas realizar?");
        System.out.println("1. REGISTRAR PERSONA");
        System.out.println("2. CONSULTAR DATOS DE UNA PERSONA");
        System.out.println("");
        opc = sc.nextInt();

        switch (opc) {

            case 1:
                System.out.println("Ingresa tu Nombre(s): ");
                String nombre = sc.next();
                System.out.println("Imgresa tu Primer apellido: ");
                String PrimerApellido = sc.next();
                System.out.println("Segundo apellido (Si lo tinees): ");
                String SegundoApellido = sc.next();
                System.out.println("Ingresa tu Sexo (Hombre o mujer): ");
                String sexo = sc.next();
                System.out.println("Ingresa la Abreviatura del Estado donde Naciste: Aguascalientes\tAS\n" +
                        "Baja California\tBC\n" +
                        "Baja California Sur\tBS\n" +
                        "Campeche\tCC\n" +
                        "Chiapas\tCS\n" +
                        "Chihuahua\tCH\n" +
                        "Ciudad de México\tDF\n" +
                        "Coahuila\tCL\n" +
                        "Colima\tCM\n" +
                        "Durango\tDG\n" +
                        "Guanajuato\tGT\n" +
                        "Guerrero\tGR\n" +
                        "Hidalgo\tHG\n" +
                        "Jalisco\tJC\n" +
                        "México\tMC\n" +
                        "Michoacán\tMN\n" +
                        "Morelos\tMS\n" +
                        "Nayarit\tNT\n" +
                        "Nuevo León\tNL\n" +
                        "Oaxaca\tOC\n" +
                        "Puebla\tPL\n" +
                        "Querétaro\tQO\n" +
                        "Quintana Roo\tQR\n" +
                        "San Luis Potosí\tSP\n" +
                        "Sinaloa\tSL\n" +
                        "Sonora\tSR\n" +
                        "Tabasco\tTC\n" +
                        "Tamaulipas\tTS\n" +
                        "Tlaxcala\tTL\n" +
                        "Veracruz\tVZ\n" +
                        "Yucatán\tYN\n" +
                        "Zacatecas\tZS" );



                String estado = sc.next();
                System.out.println("Ingresa año de Nacimiento: ");
                String anio = sc.next();
                System.out.println("Ingresa tu Mes de nacimiento (Con 2 dijitos, 01,02,rtc.): ");
                String mes = sc.next();
                System.out.println("Ingresa tu dia de nacimiento (Con 2 digitos, 01,02,etc.): ");
                String dia = sc.next();

                String CURP = Metodos.GenerateCURP(nombre,PrimerApellido,SegundoApellido,sexo, estado, anio,mes,dia);
                String FechaNac = " " + dia + "-" + mes + "-" + anio;

                System.out.println(CURP);

                String sql = "INSERT INTO persona.persona (CURP, Nombre, PrimerApellido, SegundoApellido, sexo, EstadoDeNacimiento, FechaDeNacimiento) values('"+CURP+"','"+nombre+"','"+PrimerApellido+"','"+SegundoApellido+"','"+sexo+"','"+estado+"','"+FechaNac+"')";
                Connection connection = null;

                try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persona", "root", "root");
                Statement statement = connection.createStatement();
                int x = statement.executeUpdate(sql);
                if (x == 1) {

                    System.out.println("Registrado exitosamente ");
                }
                else
                    System.out.println("Ups, algo falló");
                connection.close();

            }catch (Exception e){
                e.printStackTrace();
            }
                System.out.println(CURP);
                break;
            case 2:
                System.out.println("consulta");
                System.out.println("Ingrese su CURP");
                String curp = sc.next();

                String query = "SELECT * FROM persona where CURP = '"+ curp + "'";
                try {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persona", "root", "root");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next()){
                        String curp1 = resultSet.getString("CURP");
                        String nombre1 = resultSet.getString("nombre");
                        String PrimerApellido1 =resultSet.getString("PrimerApellido");
                        String SegundoApellido1 = resultSet.getString("SegundoApellido");
                        String sexo1 = resultSet.getString("Sexo");
                        String edo = resultSet.getString("EstadoDeNacimiento");
                        String fecha = resultSet.getString("FechaDeNacimiento");


                        System.out.format("%s, %s, %s, %s, %s, %s, %s", curp1, nombre1, PrimerApellido1, SegundoApellido1, sexo1, edo, fecha);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(curp);
                break;
        }
        }
    }

