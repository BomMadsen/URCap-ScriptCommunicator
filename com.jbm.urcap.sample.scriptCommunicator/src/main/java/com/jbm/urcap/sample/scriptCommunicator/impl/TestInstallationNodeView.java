package com.jbm.urcap.sample.scriptCommunicator.impl;

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
		panel.add(createExporterTestButton(contribution));
	}
	
	public void setReturnValue(String value) {
		RETURN_VALUE.setText(value);
	}
	
	private Box createSenderTestButton(final TestInstallationNodeContribution contribution) {
		Box box = Box.createVerticalBox();
		
		String text = "When below button is pushed, the following URScript will be sent:\n";
		text += "def testSend():\n";
		text += " popup(\"This is a popup\")\n";
		text += "end";
		
		JLabel description = new JLabel(text);
		
		JButton button = new JButton("SEND");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contribution.sendScriptTest();
			}
		});
		
		box.add(description);
		box.add(button);
		
		return box;
	}
	
	private Box createExporterTestButton(final TestInstallationNodeContribution contribution) {
		Box box = Box.createVerticalBox();
		
		String text = "When below button is pushed, the value of \"z_value\" is exported to the URCap:\n";
		text += "def testSend():\n";
		text += " pose = get_actual_tcp_pose()\n";
		text += " z_value = pose[2]\n";
		text += "end";
		
		JLabel description = new JLabel(text);
		
		JButton button = new JButton("SEND");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contribution.sendExporterTest();
			}
		});
		
		JLabel returnDescription = new JLabel("Returned value:");
		
		box.add(description);
		box.add(button);
		box.add(returnDescription);
		box.add(this.RETURN_VALUE);
		
		return box;
	}

}
