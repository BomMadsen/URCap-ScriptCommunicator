package com.jbm.urcap.sample.scriptCommunicator.communicator;

public class ScriptCommand {

	private final String prefix;
	private final String postfix = "end\n";
	
	private String commandContent = "";
	
	/**
	 * Create a new ScriptCommand
	 */
	public ScriptCommand() {
		this.prefix = "def myCustomScript():\n";
	}
	
	/** 
	 * Create a new ScriptCommand with a custom name
	 * @param commandName the custom name (must be alphanumeric, start with letter)
	 */
	public ScriptCommand(String commandName) {
		this.prefix = "def "+commandName+"():\n";
	}
	
	/**
	 * Append a URScript line to the ScriptCommand
	 * @param command the URScript line to append
	 */
	public void appendLine(String command) {
		commandContent += " "+command+"\n";
	}
	
	@Override
	public String toString() {
		String command;
		command = this.prefix;
		command += this.commandContent;
		command += this.postfix;
		return command;
	}
	
}
