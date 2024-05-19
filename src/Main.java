// Utilizando la BD Empleados, crea un programa que pida al usuario los datos de un empleado,
// excepto la fecha de contratación que la generará el sistema
// (con la fecha de introducción de datos) y los inserte en la BD
import java.sql.*;
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

            //Insertar los datos en BD
            sql = "INSERT INTO Empleados (numemp, nombre, edad, oficina, puesto, contrato) VALUES (?, ?, ?, ?, ?, ?)";

            sentencia = con.prepareStatement(sql);
            sentencia.setInt(1, numemp);
            sentencia.setString(2, nombre);
            sentencia.setInt(3, edad);
            sentencia.setInt(4, oficina);
            sentencia.setString(5, puesto); //En BD data yyyy-mm-dd
            sentencia.setString(6, fechaContrato.toString());
            sentencia.executeUpdate();//RESUELTO 14.10
            System.out.println("Se ha registrado empleado correctamente.");


        }catch (SQLException EX){
            System.out.println(EX);//saber porque falla si falla.
        }
    }
}