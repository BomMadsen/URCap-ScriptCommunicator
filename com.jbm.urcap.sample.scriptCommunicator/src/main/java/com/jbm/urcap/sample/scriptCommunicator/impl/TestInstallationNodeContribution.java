package com.jbm.urcap.sample.scriptCommunicator.impl;

import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptCommand;
import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptExporter;
import com.jbm.urcap.sample.scriptCommunicator.communicator.ScriptSender;
import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class TestInstallationNodeContribution implements InstallationNodeContribution {

	private final TestInstallationNodeView view;
	
	private final ScriptSender sender;
	private final ScriptExporter exporter;
	
	public TestInstallationNodeContribution(TestInstallationNodeView view) {
		this.view = view;
		
		this.sender = new ScriptSender();
		this.exporter = new ScriptExporter();
	}
	
	public void sendScriptTest() {
		ScriptCommand sendTestCommand = new ScriptCommand("testSend");
		sendTestCommand.appendLine("popup(\"This is a popup\")");
		
		sender.sendScriptCommand(sendTestCommand);
	}
	
	public void sendExporterTest() {
		ScriptCommand exportTestCommand = new ScriptCommand("exportVariable");
		exportTestCommand.appendLine("pose = get_actual_tcp_pose()");
		exportTestCommand.appendLine("z_value = pose[2]");
		
		String returnValue = exporter.exportStringFromURScript(exportTestCommand, "z_value");
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
