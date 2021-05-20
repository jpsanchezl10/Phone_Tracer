package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;


public class Main {

    private static HttpURLConnection connection;

    public static void main(String[] args) {

        String key = "your key";
        Scanner number = new Scanner(System.in);
        System.out.println("Ingresa el numero a escanear :");
        String numb = number.nextLine();

	    String url = "http://apilayer.net/api/validate?access_key="+key+"&number="+numb+"&country_code=&format=1";

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
	    try {
	        URL url1 = new URL(url);
	        connection = (HttpURLConnection) url1.openConnection();

	        //Configuracion de conexion
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);

	        int status = connection.getResponseCode();
	        System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();

            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

            //Formato de texto
            String Phone_Info = responseContent.toString();
            Phone_Info = Phone_Info.replaceAll(",","\n");
            Phone_Info = Phone_Info.replaceAll("\""," ");
            Phone_Info = Phone_Info.replace("number", "numero");
            Phone_Info = Phone_Info.replaceAll("local_format","Formato Local");
            Phone_Info = Phone_Info.replaceAll("international_format","Formato internacional");
            Phone_Info = Phone_Info.replaceAll("country_prefix","Lada");
            Phone_Info = Phone_Info.replaceAll("country_code","Codigo de pais");
            Phone_Info = Phone_Info.replaceAll("country_name","Pais");
            Phone_Info = Phone_Info.replaceAll("location","Ubicacion");
            Phone_Info = Phone_Info.replaceAll("carrier","Compañia");
            Phone_Info = Phone_Info.replaceAll("line_type","Tipo de linea");
            Phone_Info = Phone_Info.replaceAll("valid","Valido");
            System.out.print(Phone_Info);


        }catch (MalformedURLException e){
	        e.printStackTrace();
        }catch (IOException e){
	        e.printStackTrace();
        }finally {
	        connection.disconnect();
        }
    }
}
