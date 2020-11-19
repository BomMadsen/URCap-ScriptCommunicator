package com.jbm.urcap.sample.scriptCommunicator.communicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ur.urcap.api.domain.value.Pose;
import com.ur.urcap.api.domain.value.PoseFactory;
import com.ur.urcap.api.domain.value.simple.Angle;
import com.ur.urcap.api.domain.value.simple.Length;




public class ScriptTypeConverter {
	PoseFactory poseFactory;
	
	ScriptTypeConverter(PoseFactory poseFactory){
		this.poseFactory = poseFactory;
	}

	static class URBoolean{
		Boolean instance;
		public URBoolean(Boolean booleanInput) {
			instance = booleanInput;
		}
		static Boolean parseURBoolean(String str) {
			return Boolean.parseBoolean(str.toLowerCase());		
		}
		
		public String toString() {
			String returnValue;
			if(instance.booleanValue()) {
				returnValue = "True";
			}
			else {
				returnValue = "False";
			}
			return returnValue;
			
		};
	}
	public String booleanToScript(Boolean boolInput) {
		URBoolean bool = new URBoolean(boolInput);
		return bool.toString();		
	}	
	public String integerToScript(Integer integerInput) {
		return integerInput.toString();		
	}		
	public String doubleToScript(Double doubleInput) {
		return doubleInput.toString();		
	}	

	public String poseToScript(Pose poseInput) {
		return poseInput.toString();		
	}
		
	public String listToScript(List<Object> listInput) {
		return listInput.toString();		
	}
	public Integer scriptToInterger(String scriptInteger) {
		return Integer.parseInt(scriptInteger);		
	}
	public Double scriptToDouble(String scriptDouble) {
		return Double.parseDouble(scriptDouble);		
	}	

	public Pose scriptToPose(String scriptPose) {
		List<String> strList = scriptToStringList(scriptPose);
		List<Double> p = new ArrayList <Double>(); 
		for(Iterator<String> i = strList.iterator(); i.hasNext(); ) {
			p.add(Double.parseDouble(i.next()));
		}		
		return poseFactory.createPose(p.get(0), p.get(1), p.get(2), p.get(3), p.get(4), p.get(5), Length.Unit.M, Angle.Unit.RAD);
	}
	
	public Boolean scriptToBoolean(String scriptBoolean) {
		return URBoolean.parseURBoolean(scriptBoolean);
	}	
	
	
	private List<String> scriptToStringList(String scriptList) {
		scriptList = scriptList.replace("p", "").replace("[", "").replace("]", "");
		return Arrays.asList(scriptList.split("\\s*,\\s*"));
	}
	public List<Boolean> scriptToBooleanList(String scriptList){
		List<String> items = scriptToStringList(scriptList);
		List<Boolean> returnValue = new ArrayList<Boolean>();
		for(Iterator<String> i = items.iterator(); i.hasNext(); ) {
			  String item = i.next();
			  returnValue.add(URBoolean.parseURBoolean(item));			  
		}
		return returnValue;
	}	
	public List<Integer> scriptToIntegerList(String scriptList){
		List<String> items = scriptToStringList(scriptList);
		List<Integer> returnValue = new ArrayList<Integer>();; 
		for(Iterator<String> i = items.iterator(); i.hasNext(); ) {
			  String item = i.next();
			  returnValue.add(Integer.parseInt(item));			  
		}
		return returnValue;
	}
	public List<Double> scriptToDoubleList(String scriptList){
		List<String> items = scriptToStringList(scriptList);
		List<Double> returnValue = new ArrayList<Double>();; 
		for(Iterator<String> i = items.iterator(); i.hasNext(); ) {
			  String item = i.next();
			  returnValue.add(Double.parseDouble(item));			  
		}
		return returnValue;
	}	
	/* TODO
	public List<Pose> scriptToDoubleList(String scriptList){
		List<String> items = scriptToStringList(scriptList);
		List<Pose> returnValue = new ArrayList<Pose>();; 
		for(Iterator<String> i = items.iterator(); i.hasNext(); ) {
			  String item = i.next();
			  returnValue.add(scriptToPose(item));			  
		}
		return returnValue;
	}
	*/	
}
