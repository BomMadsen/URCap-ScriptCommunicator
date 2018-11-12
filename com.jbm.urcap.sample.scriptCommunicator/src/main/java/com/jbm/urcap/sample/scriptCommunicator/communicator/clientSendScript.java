package com.jbm.urcap.sample.scriptCommunicator.communicator;

// Used for reading from RealTime Client
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class clientSendScript {
	
	public clientSendScript() { }
	
	// localhost IP
	private String TCP_IP = "127.0.0.1";
	// Port for secondary client
	private int TCP_port = 30002;
	
	// Public method to send popup script to client interface
	public void createPopup(String message){
		sendToSecondary("popup(\""+message+"\")");
	}
	
	// Public method to send MoveJ command to client interface
	public void sendMoveJ(String pose){
		sendToSecondary("movej("+pose+",a=1.57,v=0.5)");
	}
	
	// Internal method that sends script to client
	private void sendToSecondary(String command){
		try{
			// Create a new Socket Client
			Socket sc = new Socket(TCP_IP, TCP_port);
			if (sc.isConnected()){
				System.out.println("Connected to UR Secondary Client");
			}
			
			// Create stream for data
			DataOutputStream out;
			out = new DataOutputStream(sc.getOutputStream());
			
			// Wrap command in "def" and "end"
			String thisCommand = "def myCustomCode():\n "+command+"\n end\n";
			
			// Send command
			out.writeUTF(thisCommand);
			System.out.println("Send this: "+thisCommand);
			out.flush();

			// Perform housekeeping 
			out.close();
			sc.close();
			System.out.println("Disconnected from UR Secondary Client");
		} 
		catch (IOException e){
			System.out.println(e);
		}
	}
}