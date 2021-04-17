package me.alien.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main{

    private static final int IP = 0;
    private static final int PORT = 1;
    
    private Socket socket;
    private Scanner scanner;

    private Main(InetAddress ip, int port){
        this.socket = new Socket(ip, port);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException{
        String in;
        while(true){
            in = scanner.nextLine();

            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(in);
            out.flush();
        }
    }

    public static void main(String[] args)throws Exception{
        Main client = new Main(
            InetAddress.getByName(read(System.getProperty("usr.dir")+"/ip.conf", IP)),
            Integer.parseInt(read(System.getProperty("usr.dir")+"/ip.conf", PORT))
        );
        System.out.println("Connected to " + read(System.getProperty("usr.dir")+"/ip.conf", IP) + ":" + read(System.getProperty("usr.dir")+"/ip.conf", PORT));
        client.start();
    }

    private static String read(String path, int type) throws Exception{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
            System.out.println("add ip and port for the server in the file "+path);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("IP: xxx.xxx.xxx.xxx\n");
            bw.write("PORT: xxxxx");
            bw.close();
            System.exit(10);
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String in;
        while((in = br.readLine()) != null){
            if(in.startsWith("IP: ") && type == IP){
                in = in.substring(4);
                break;
            }else if(in.startsWith("PORT: ") && type == PORT){
                in = in.substring(6);
                break;
            }
        }

        return in;
    }
}