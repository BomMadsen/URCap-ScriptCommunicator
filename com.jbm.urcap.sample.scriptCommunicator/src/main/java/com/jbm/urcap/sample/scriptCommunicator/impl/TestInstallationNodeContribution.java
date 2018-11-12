package com.jbm.urcap.sample.scriptCommunicator.impl;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class TestInstallationNodeContribution implements InstallationNodeContribution {

	private final TestInstallationNodeView view;
	
	public TestInstallationNodeContribution(TestInstallationNodeView view) {
		this.view = view;
	}
	
	public void sendScriptTest() {
		
	}
	
	public void sendExporterTest() {
		
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
