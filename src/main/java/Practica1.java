import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Luis Capellan on 24/01/2019
 */


public class Practica1 {
    public static void main(String[] args) {

        Document doc;
        Connection.Response conn;
        String URL;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n------------------------Practica de  HTTP---------------------\n");

        //receiver
        while (true) {
            try {
                System.out.print("Direccion URL:");
                URL = scanner.nextLine();

                doc = Jsoup.connect("http://" + URL).get();
                conn = Jsoup.connect("http://" + URL).execute();
            } catch (Exception ex) {
                System.out.println("URL no valida");
                continue;
            }
            System.out.println("\nURL Valida\n");
            break;
        }

        //Practica

        //parte a) Indicar la cantidad de lineas del recurso retornado
        System.out.println("A) : " + conn.body().split("\n").length + " Lineas");

        //Parte b) Indicar la cantidad de párrafos (p) que contiene el documento HTML
        Elements elements = doc.getElementsByTag("p");
        System.out.println("B) : " + elements.size() + " Parrafos");

        //Parte c) Indicar la cantidad de imágenes (img) dentro de los párrafos que
        //contiene el archivo HTML.
        System.out.println("C) : " + doc.select("p img").size() + " Fotos dentro del parrafo");

        //Parte d) indicar la cantidad de formularios (form) que contiene el HTML por
        //categorizando por el método implementado POST o GET.
        elements = doc.getElementsByTag("form");
        System.out.println("D) : " + elements.size() + " formularios");

        int Get = 0, Post = 0;
        for (FormElement form : doc.getElementsByTag("form").forms()) {
            if (form.attr("method").equalsIgnoreCase("GET")) {
                Get++;
            }
            if (form.attr("method").equalsIgnoreCase("POST")) {
                Post++;
            }
        }
        System.out.println("\n" + Get + " GET \n" + Post + " POST");

        //Parte e) Para cada formulario mostrar los campos del tipo input y su
        //respectivo tipo que contiene en el documento HTML.
        int formulario = 1;

        for (Element e : elements) {
            System.out.println("Titulo del formulario " + formulario + " es " + elements.attr("name\n"));
            int input = 1;
            for (Element element : e.getAllElements()) {
                if (element.tagName().equals("input")) {
                    System.out.println("Nombre del Input " + input + " es " + element.attr("name\n"));

                    for (Attribute attribute : element.attributes()) {
                        System.out.println(" " + attribute.getKey() + " =  " + attribute.getValue());
                    }
                    input++;
                    System.out.println();
                }
            }
            formulario++;
        }
        int x = 1;
        Document ResultingDoc;
        for (Element form : doc.getElementsByTag("form").forms()) {
            Elements postForm = form.getElementsByAttributeValueContaining("method", "post");
            for (Element form2 : postForm) {
                try {
                    System.out.println("Formulario " + x + ":");
                    String absURL = form2.absUrl("action");
                    ResultingDoc = Jsoup.connect(absURL).data("asignatura", "practica1").header("matricula", "20140984").post();
                    System.out.println("\nResultado");
                    System.out.println(ResultingDoc.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            x++;
        }

    }
}