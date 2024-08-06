package MaquinaSnacksArchivos.Servicio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import MaquinaSnacksArchivos.dominio.Snack;

public class ServicioSnacksArchivos implements IServicioSnacks{

    private final String NOMBRE_ARCHIVO = "snacks.txt";

    private List<Snack> snacks = new ArrayList<>();

    

    public ServicioSnacksArchivos() {

        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;

        try {
            existe = archivo.exists();

            if (existe) {
                this.snacks = obtenerSnacks();
            }else{
                var salida = new PrintWriter(new FileWriter(archivo));
            }
        } catch (Exception e) {
            System.out.println("Error al crear el archivo"+ e);
        }

        if(!existe)
        cargarSnacksIniciales();

    }

    private void cargarSnacksIniciales(){

        this.agregarSnack(new Snack("Refresco", 50));
        this.agregarSnack(new Snack("Papas", 80));
        this.agregarSnack(new Snack("Sandwich", 90));
        this.agregarSnack(new Snack("Pizza", 30));
    }

    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea : lineas) {
                String[] lineaSnack = linea.split(",");
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);    
                var snack = new Snack(nombre, precio);
                snacks.add(snack);
            }

        } catch (Exception e) {
           System.out.println("Error al leer el archivo de snacks: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    } 

    @Override
    public void agregarSnack(Snack snack) {
        //Agg snacks 
        this.snacks.add(snack);
        //Guardar los snacks en el archivo
        this.agregarSnackArchivo(snack);

        }

        public void agregarSnackArchivo(Snack snack){
             var archivo = new File(NOMBRE_ARCHIVO);
             boolean anexar = false;
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();

        } catch (Exception e) {
           System.out.println("ERROR al agregar el snack: " + e.getMessage());
        }
        }
   
    @Override
    public void mostrarSnacks() {
        System.out.println("---SNACKS DEL INVENTARIO---");
        var inventarioSnacks = "";
        for (Snack snack : snacks) {
            inventarioSnacks += snack.toString() + "\n";
        }
        
        System.out.println(inventarioSnacks);

      }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }

}
