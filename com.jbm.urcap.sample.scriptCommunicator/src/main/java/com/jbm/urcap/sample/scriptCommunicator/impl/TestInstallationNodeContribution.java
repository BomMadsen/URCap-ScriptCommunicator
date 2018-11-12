package com.jbm.urcap.sample.scriptCommunicator.impl;

import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptCommand;
import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptExporter;
import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptSender;
import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class TestInstallationNodeContribution implements InstallationNodeContribution {

	private final TestInstallationNodeView view;
	
	// Instance of the ScriptSender
	// Used to send a URScript for execution
	private final ScriptSender sender;
	
	// Instance of ScriptExporter
	// Used to extract values from URScript
	private final ScriptExporter exporter;
	
	public TestInstallationNodeContribution(TestInstallationNodeView view) {
		this.view = view;
		
		// Initialize Sender and Exporter here... 
		this.sender = new ScriptSender();
		this.exporter = new ScriptExporter();
	}
	
	/**
	 * This command is invoked by clicking the SEND button in the View 
	 * Sends a popup command to URControl
	 */
	public void sendScriptTest() {
		// Create a new ScriptCommand called "testSend"
		ScriptCommand sendTestCommand = new ScriptCommand("testSend");
		
		// Append a popup command to the ScriptCommand
		sendTestCommand.appendLine("popup(\"This is a popup\")");
		
		// Use the ScriptSender to send the command for immediate execution
		sender.sendScriptCommand(sendTestCommand);
	}
	
	/**
	 * This command is invoked by clicking the SEND button in the View 
	 * Sends a calculation script to URControl, extracts the Z-value of the current TCP pose
	 */
	public void sendExporterTest() {
		// Create a new ScriptCommand called "exportVariable"
		ScriptCommand exportTestCommand = new ScriptCommand("exportVariable");
		
		// Add the calculation script to the command
		exportTestCommand.appendLine("pose = get_actual_tcp_pose()");
		exportTestCommand.appendLine("z_value = pose[2]");
		
		// Use the exporter to send the script
		// Note the String name of the variable (z_value) to be returned
		String returnValue = exporter.exportStringFromURScript(exportTestCommand, "z_value");
		
		// Put the result back in the View
		view.setReturnValue(returnValue);
	}
	
	@Override
	public void openView() {
	}

	@Override
	public void closeView() {
	}

	@Override
	public void generateScript(ScriptWriter writer) {
	}

}
