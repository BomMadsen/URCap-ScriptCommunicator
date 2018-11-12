package com.jbm.urcap.sample.scriptCommunicator.communicator;

// Used for reading from RealTime Client
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ScriptSender {
	
	
	
	// IP of the robot 
	private final String TCP_IP;
	// Port for secondary client
	private final int TCP_port = 30002;
	
	/**
	 * Default constructor, using localhost IP (127.0.0.1)
	 */
	public ScriptSender() { 
		this.TCP_IP = "127.0.0.1";
	}
	
	/**
	 * Constructor for IP different from localhost
	 * @param IP the IP address of the robot
	 */
	public ScriptSender(String IP) {
		this.TCP_IP = IP;
	}
	
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
			String thisCommand = "def myCustomCode():\n "+command+"\nend\n";
			
			// Send command
			out.write(thisCommand.getBytes("US-ASCII"));
			System.out.println("Send this: \n"+thisCommand);
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