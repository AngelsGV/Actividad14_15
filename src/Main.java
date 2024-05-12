// Utilizando la BD Empleados, crea un programa que pida al usuario los datos de un empleado,
// excepto la fecha de contratación que la generará el sistema
// (con la fecha de introducción de datos) y los inserte en la BD
import java.sql.*;
public class Main {
    public static void main(String[] args){
        //Declaramos las variables
        Connection con ;
        Statement sentencia;
        ResultSet rs;
        String sql;

        String url = "jdbc:mysql://localhost/Empresa";

        try{
            con = DriverManager.getConnection(url, "Pepe","12345"); //Cosas para que la conexión sea ok
            sentencia = con.createStatement();//creamos la conexión
            sql = "SELECT * FROM Empleados";
            // He decidido que para saber el empleado es necesario saber el nombre y
            // el número de empleado, para que no hayan dudas
            rs = sentencia.executeQuery(sql);//para sacar los resultados
            System.out.println("Lista de empleados: ");

            int i = 1;//Defino este valor para que el resultado mostrado en pantalla sea más visual
            // y para saber cuantos empleados hay en esa tabla.
            while (rs.next()){
                //Mientras haya más filas en la tabla:

                String nombre = rs.getString("nombre");
                int numemp = rs.getInt("numemp");
                System.out.println("Empleado "+ i + ": " + nombre + ". Número de empleado: " + numemp);
                i ++;
            }
            con.close(); //cerramos la conexión
        }catch (SQLException EX){
            System.out.println(EX);//saber porque falla si falla.
        }
    }
}