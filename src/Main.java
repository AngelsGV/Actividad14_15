// Utilizando la BD Empleados, crea un programa que pida al usuario los datos de un empleado,
// excepto la fecha de contratación que la generará el sistema
// (con la fecha de introducción de datos) y los inserte en la BD
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;
public class Main {
    public static void main(String[] args){
        //Declaramos las variables
        Scanner sc =new Scanner(System.in); //porque vamos a introducir los datos del nuevo empleado
        Connection con ;
        PreparedStatement sentencia;
        //ResultSet rs; --> sql select
        String sql;

        String url = "jdbc:mysql://localhost/Empresa";

        try{
            con = DriverManager.getConnection(url, "Pepe","12345"); //Cosas para que la conexión sea ok

           //Pedir datos empleado nuevo
            System.out.println("Vamos a insertar un empleado nuevo en la BD.");
            System.out.println("Introduzca el nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduzca el número de empleado: ");
            int numemp = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduzca la edad: ");
            int edad = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer,
            // poner siempre después de nextInt,double, long para evitar errores al seguirintroduciendo datos.

            System.out.println("Introduzca el puesto: ");
            String puesto = sc.nextLine();
            System.out.println("Introduzca el númeor de la oficina: ");
            int oficina = sc.nextInt();
            sc.nextLine();
            System.out.println("La fecha del contrato será la fecha que se introducen los datos.");
            //Crear la fecha de contrato -->TEMA 1
            LocalDate fechaContrato = LocalDate.now();


            sentencia = con.createStatement();//creamos la conexión
            sql = "INSERT INTO Empleados (numemp, nombre, edad, oficina, puesto, fechaContrato) VALUES (?, ?, ?, ?, ?, ?)";


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