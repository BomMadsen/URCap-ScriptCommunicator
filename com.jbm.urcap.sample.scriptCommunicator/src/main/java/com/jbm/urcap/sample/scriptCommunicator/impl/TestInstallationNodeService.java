package com.jbm.urcap.sample.scriptCommunicator.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class TestInstallationNodeService implements SwingInstallationNodeService<TestInstallationNodeContribution, TestInstallationNodeView>{

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			TestInstallationNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
