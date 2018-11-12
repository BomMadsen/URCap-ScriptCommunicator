package com.jbm.urcap.sample.scriptCommunicator.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;

public class TestInstallationNodeView implements SwingInstallationNodeView<TestInstallationNodeContribution>{

	private JLabel RETURN_VALUE = new JLabel();
	
	@Override
	public void buildUI(JPanel panel, TestInstallationNodeContribution contribution) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(createSenderTestButton(contribution));
		panel.add(createSpacer(40));
		panel.add(createExporterTestButton(contribution));
	}
	
	public void setReturnValue(String value) {
		RETURN_VALUE.setText(value);
	}
	
	private Box createSenderTestButton(final TestInstallationNodeContribution contribution) {
		Box box = Box.createVerticalBox();
		
		box.add(new JLabel("When below button is pushed, the following URScript will be sent:"));
		box.add(new JLabel(" def testSend():"));
		box.add(new JLabel("   popup(\"This is a popup\")"));
		box.add(new JLabel(" end"));
		
		JButton button = new JButton("SEND");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contribution.sendScriptTest();
			}
		});
		
		box.add(createSpacer(10));
		box.add(button);
		
		return box;
	}
	
	private Box createExporterTestButton(final TestInstallationNodeContribution contribution) {
		Box box = Box.createVerticalBox();
		
		box.add(new JLabel("When below button is pushed, the value of \"z_value\" is exported to the URCap:"));
		box.add(new JLabel(" sec exportVariable():"));
		box.add(new JLabel("   pose = get_actual_tcp_pose()"));
		box.add(new JLabel("   z_value = pose[2]"));
		box.add(new JLabel(" end"));
		
		JButton button = new JButton("SEND");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contribution.sendExporterTest();
			}
		});

		box.add(createSpacer(10));
		box.add(button);
		box.add(createSpacer(10));
		box.add(new JLabel("Returned value:"));
		box.add(this.RETURN_VALUE);
		
		return box;
	}
	
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}

}
