package com.example;

import java.net.*;
import java.io.*;

public class HttpServer {

    HttpConnectionExample httpConnectionExample = new HttpConnectionExample();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        Boolean running = true;
        while (running){
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Calculadora</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Form with POST</h1>\n"
                    + "<form action=/comando>\n"
                    + " <label for=postname>Operacion:</label><br>\n"
                    + " <input type=text id=postname name=com><br><br>\n"
                    + " <input type=button value=Submit onclick=loadPostMsg(postname)>\n"
                    + "</form>\n"
                    + "<div id=postrespmsg></div> \n"
                    + "<script>\n"
                    + "function loadPostMsg(name){\n"
                    + " let url = /hellopost?com= + name.value;\n"
                    + "fetch (url, {method: 'POST'})\n"
                    + ".then(x => x.text())\n"
                    + ".then(y => document.getElementById(postrespmsg).innerHTML = y);}\n"
                    + "</script>\n"
                    + "</body>\n"
                    + "</html>\n";
            out.println(outputLine);
            in.close();
            out.close();
        }
        clientSocket.close();
        serverSocket.close();
    }
}