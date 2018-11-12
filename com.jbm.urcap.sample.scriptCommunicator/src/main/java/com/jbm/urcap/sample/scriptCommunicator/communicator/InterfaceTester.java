package com.jbm.urcap.sample.scriptCommunicator.communicator;

public class InterfaceTester {

	public static void main(String[] args) {
		ScriptSender sender = new ScriptSender();
		
		ScriptCommand command = new ScriptCommand("johnDoe");
		System.out.println("Command is");
		System.out.println(command);
		
		sender.sendScriptCommand(command);
		
		command.appendLine("popup(\"Lolz\")");
		System.out.println("Command is");
		System.out.println(command);
		
		sender.sendScriptCommand(command);
	}

}
