package com.jbm.urcap.sample.scriptCommunicator.communicator;

import com.ur.urcap.api.domain.value.Pose;
import com.ur.urcap.api.domain.value.PoseFactory;
import com.ur.urcap.api.domain.value.Position;
import com.ur.urcap.api.domain.value.Rotation;
import com.ur.urcap.api.domain.value.simple.Angle;
import com.ur.urcap.api.domain.value.simple.Length;
import com.ur.urcap.api.domain.value.simple.Length.Unit;


public class InterfaceTester {

	/**
	 * This class can be used to test the Exporter and Sender
	 * Hit the Play button in your IDE, to execute this
	 * 
	 */
	private static class TestPose implements Pose{
		private double x;
		private double y;
		private double z;
		private double rx;
		private double ry;
		private double rz;
		private Length.Unit lengthUnit;
		private Angle.Unit angleUnit;
		
		TestPose(double x, double y, double z, double rx, double ry, double rz, Length.Unit lengthUnit,
				Angle.Unit angleUnit) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.rx = rx;
			this.ry = ry;
			this.rz = rz;
			this.lengthUnit = lengthUnit;
			this.angleUnit = angleUnit;			
		}
		
		@Override
		public Position getPosition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Rotation getRotation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double[] toArray(Unit lengthUnit, Angle.Unit angleUnit) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean epsilonEquals(Pose other, double epsilon) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean epsilonEquals(Pose other, double lengthEpsilon, Unit lengthUnit, double angleEpsilon,Angle.Unit angleUnit) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public String toString() {
			return new String("p["+x+","+y+","+z+","+rx+","+ry+","+rz+"]");
			
		}
		
	}
	private static class TestPoseFactory implements PoseFactory{

		@Override
		public Pose createPose(double x, double y, double z, double rx, double ry, double rz, Length.Unit lengthUnit,
				Angle.Unit angleUnit) {
			return new TestPose( x,  y,  z,  rx,  ry,  rz, lengthUnit, angleUnit);
		}
		
	}
	
	public static void main(String[] args) {
		/**
		 * Testing Sender
		 */
		
		ScriptSender sender = new ScriptSender();
		
		ScriptCommand senderCommand = new ScriptCommand("SenderCommand");
		senderCommand.appendLine("textmsg(\"Add something to the logfile...\")");
		senderCommand.setAsSecondaryProgram();
		sender.sendScriptCommand(senderCommand);
		
		/**
		 * Testing Proxy
		 */
		PoseFactory poseFactory = new TestPoseFactory();
		ScriptTypeConverter typeConverter = new ScriptTypeConverter(poseFactory);
		ScriptProxy proxy = new ScriptProxy(typeConverter);
		Double acos = proxy.acos(0.6);
		//test against java
		System.out.println("Double acos result is: "+ acos);
		
		proxy.set_digital_out(5, true);
		System.out.println("dig in result is: "+ proxy.get_configurable_digital_in(6));
		
		System.out.println("force result is: "+ proxy.force());
		
		Pose actualTCP = proxy.get_actual_tcp_pose();
		System.out.println("actual TCP pose result is: "+ actualTCP);
		Pose invActualTCP = proxy.pose_inv(actualTCP);
		System.out.println("inverted actual TCP pose result is: "+ invActualTCP);
		Pose zeroPose = proxy.pose_trans(actualTCP, invActualTCP);
		System.out.println("zeroPose pose result is: "+ zeroPose);
		
		
		
		
		
		/**
		 * Testing Exporter
		 */
		
		ScriptExporter export = new ScriptExporter();
		
		ScriptCommand commandString = new ScriptCommand("Command1");
		commandString.appendLine("pose = get_actual_tcp_pose()");
		commandString.appendLine("z_value = pose[2]");
		
		String resultString = export.exportStringFromURScript(commandString, "z_value");
		System.out.println("String result is: "+resultString);
		
		ScriptCommand commandInt = new ScriptCommand("Command2");
		commandInt.appendLine("var_1 = 25 + 17");
		
		int resultInt = export.exportIntegerFromURScript(commandInt, "var_1");
		System.out.println("Integer result is: "+resultInt);
		
	}

}
