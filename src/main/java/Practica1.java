import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Luis Capellan on 24/01/2019
 */


public class Practica1 {
    public static void main(String[] args) {

        Document document;
        Connection.Response c;
        String URL;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------------Practica de  HTTP---------------------\n");

        //receiver
        while (true) {
            try {
                System.out.print("Direccion URL:");
                URL = scanner.nextLine();

                document = Jsoup.connect("http://" + URL).get();
                c = Jsoup.connect("http://" + URL).execute();
            } catch (Exception ex) {
                System.out.println("URL no valida");
                continue;
            }
            System.out.println("\nURL Valida\n");
            break;
        }

        //Practica

        //parte a) Indicar la cantidad de lineas del recurso retornado
        System.out.println("A) : " + c.body().split("\n").length + " Lineas");

        //Parte b) Indicar la cantidad de párrafos (p) que contiene el documento HTML
        Elements elements = document.getElementsByTag("p");
        System.out.println("B) : " + elements.size() + " Parrafos");

        //Parte c) Indicar la cantidad de imágenes (img) dentro de los párrafos que
        //contiene el archivo HTML.
        System.out.println("C) : " + document.select("p img").size() + " Fotos dentro del parrafo");

        //Parte d) indicar la cantidad de formularios (form) que contiene el HTML por
        //categorizando por el método implementado POST o GET.
        elements = document.getElementsByTag("form");
        System.out.println("D) : " + elements.size() + " formularios");

        int Get = 0, Post = 0;
        for (FormElement form : document.getElementsByTag("form").forms()) {
            if (form.attr("method").equalsIgnoreCase("GET")) {
                Get++;
            }
            if (form.attr("method").equalsIgnoreCase("POST")) {
                Post++;
            }
        }
        System.out.println("\n" + Get + " GET \n" + Post + " POST");

    }
}