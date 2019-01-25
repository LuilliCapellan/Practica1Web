import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Luis Capellan on 24/01/2019
 *
 */



public class Practica1 {
    public static void main(String [] args){

        Document document;
        Connection.Response c;
        String URL;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------------Practica de  HTTP---------------------\n");

        //receiver
        while (true){
            try{
                System.out.print("Direccion URL:");
                URL = scanner.nextLine();

                document = Jsoup.connect("http://"+URL).get();
                c = Jsoup.connect("http://"+URL).execute();
            }catch (Exception ex){
                System.out.println("URL no valida");
                continue;
            }
            System.out.println("\nURL Valida\n");
            break;
        }

        //Practica

        //parte a) Indicar la cantidad de lineas del recurso retornado
        System.out.println("\nParte A: \n");
        System.out.println("Cantidad de Líneas: " + c.body().split("\n").length);

    }
}