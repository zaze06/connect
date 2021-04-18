package me.alien.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main{
    public Main() throws Exception {
        String clientSentece;
        ServerSocket welcomSocket = new ServerSocket(1337);
        Socket connectedSocket = welcomSocket.accept();
        Scanner in = new Scanner(System.in);

        System.out.println("starting");

        while(true){
            if(!connectedSocket.isConnected()){
                welcomSocket.accept();
            }else{
                System.out.println("Client connected");
                BufferedReader bw = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream()));
                clientSentece=bw.readLine();
                System.out.println("Recived: "+ clientSentece);
                DataOutputStream out = new DataOutputStream(connectedSocket.getOutputStream());
                out.writeBytes(in.nextLine());
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new Main();
    }
}