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
	}

	@Override
	public String getTitle(Locale locale) {
		return "Script Communicator";
	}

	@Override
	public TestInstallationNodeView createView(ViewAPIProvider apiProvider) {
		return new TestInstallationNodeView();
	}

	@Override
	public TestInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			TestInstallationNodeView view, DataModel model, CreationContext context) {
		return new TestInstallationNodeContribution(view);
	}

}
