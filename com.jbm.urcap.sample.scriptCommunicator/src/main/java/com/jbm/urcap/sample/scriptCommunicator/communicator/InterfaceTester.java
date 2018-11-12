package com.jbm.urcap.sample.scriptCommunicator.communicator;

public class InterfaceTester {

	public static void main(String[] args) {
		ScriptExporter export = new ScriptExporter();
		
		ScriptCommand commandString = new ScriptCommand("JacobsCommand1");
		commandString.appendLine("var_1 = \"Hello World\"");
		
		export.exportStringFromURScript(commandString, "var_1");
		
		ScriptCommand commandInt = new ScriptCommand("JacobsCommand2");
		commandInt.appendLine("var_2 = 42");
		
		export.exportStringFromURScript(commandInt, "var_2");
	}

}
