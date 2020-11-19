package com.jbm.urcap.sample.scriptCommunicator.communicator;

import java.util.List;

import com.ur.urcap.api.domain.value.Pose;
import com.ur.urcap.api.domain.value.PoseFactory;

public class ScriptProxy extends ScriptExporter{
	ScriptTypeConverter type;
	public ScriptProxy(ScriptTypeConverter type){
		super();
		this.type = type;
	}
	
	String COMMAND = "cmd";
	String TEMP_VALUE = "temp_value";
	String RETURN_VALUE = "return_value";
	String ASSIGN_STRING = TEMP_VALUE+"=";
		
	private String buildSendAndReceive(ScriptCommand command) {
		command.appendLine(RETURN_VALUE+"=to_str("+TEMP_VALUE+")");
		ScriptCommand newCommand = this.buildScriptCommandToExport(command, RETURN_VALUE);
		return this.readValueFromRobot(newCommand);
	}
	private void buildSend(ScriptCommand command) {
		command.appendLine(RETURN_VALUE+"=0");
		ScriptCommand newCommand = this.buildScriptCommandToExport(command, RETURN_VALUE);
		this.readValueFromRobot(newCommand);
	}	
	
	//Module urmath
	public Double acos(Double f){
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"acos("+ type.doubleToScript(f) + ")");
		String reply = buildSendAndReceive(command);

		return type.scriptToDouble(reply);
	}

	// TODO get_analog_in   				//Deprecated: Get analog input signal level
	// TODO get_analog_out 					//Deprecated: Get analog output signal level

	
	/* get_configurable_digital_in(n)
		Get configurable digital input signal level
		See also get_standard_digital_in and get_tool_digital_in.
		Parameters
		n: The number (id) of the input, integer: [0:7]
		Return Value
		boolean, The signal level.
		Example command: get_configurable_digital_in(1)
		• Example Parameters:
		– n is configurable digital input 1
		∗ Returns True or False
	 */
	public Boolean get_configurable_digital_in(Integer n) {
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"get_configurable_digital_in("+ type.integerToScript(n) + ")");
		String reply = buildSendAndReceive(command);
		return type.scriptToBoolean(reply);		
	}
	
	/* get_configurable_digital_out(n)
		Get configurable digital output signal level
		See also get_standard_digital_out and get_tool_digital_out.
		Parameters
		n: The number (id) of the output, integer: [0:7]
		Return Value
		boolean, The signal level.
		Example command: get_configurable_digital_out(1)
		• Example Parameters:
		– n is configurable digital output 1
		∗ Returns True or False
	*/
	public Boolean get_configurable_digital_out(Integer n) {
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"get_configurable_digital_out("+ type.integerToScript(n) + ")");
		String reply = buildSendAndReceive(command);
		return type.scriptToBoolean(reply);		
	}
	// TODO get_digital_in 
	// TODO get_digital_out
	// TODO get_flag  
	// TODO get_standard_analog_in  
	// TODO get_standard_analog_out  
	// TODO get_standard_digital_in  
	// TODO get_standard_digital_out  
	// TODO get_tool_analog_in  
	// TODO get_tool_digital_in  
	// TODO get_tool_digital_out  
	// TODO get_tool_digital_output_mode  
	// TODO get_tool_output_mode  
	// TODO modbus_add_signal  
	// TODO modbus_delete_signal  
	// TODO modbus_get_signal_status  
	// TODO modbus_send_custom_command  
	// TODO modbus_set_digital_input_action  
	// TODO modbus_set_output_register  
	// TODO modbus_set_output_signal  
	// TODO modbus_set_signal_update_frequency  
	// TODO read_input_boolean_register  
	// TODO read_input_float_register  
	// TODO read_input_integer_register  
	// TODO read_output_boolean_register  
	// TODO read_output_float_register  
	// TODO read_output_integer_register  
	// TODO read_port_bit  
	// TODO read_port_register  
	// TODO rpc_factory  
	// TODO rtde_set_watchdog  
	// TODO set_analog_inputrange  
	// TODO set_analog_out  
	// TODO set_configurable_digital_out  
	/* set_digital_out(n, b)
	Deprecated: Set digital output signal level
	Parameters
	n: The number (id) of the output, integer: [0:9]
	b: The signal level. (boolean)
	Deprecated: The set_standard_digital_out and
	set_tool_digital_out replace this function. Ports 8-9 should be
	changed to 0-1 for the latter function. This function might be removed
	in the next major release.
	Example command: set_digital_out(1,True)
	• Example Parameters:
	– n is digital output 1
	– b = True
	*/
	public void set_digital_out(Integer n, Boolean b) {
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine("set_digital_out("+ type.integerToScript(n) +","+type.booleanToScript(b)+")");
		buildSend(command);		
	}
	
	// TODO set_flag  
	// TODO set_standard_analog_out  
	// TODO set_standard_digital_out  
	// TODO set_tool_communication  
	// TODO set_tool_digital_out  
	// TODO set_tool_digital_output_mode  
	// TODO set_tool_output_mode  
	// TODO set_tool_voltage  

	// TODO socket_close  
	// TODO socket_get_var  
	// TODO socket_open  
	// TODO socket_read_ascii_float  
	// TODO socket_read_binary_integer  
	// TODO socket_read_byte_list  
	// TODO socket_read_line  
	// TODO socket_read_string  
	// TODO socket_send_byte  
	// TODO socket_send_int  
	// TODO socket_send_line  
	// TODO socket_send_string  
	// TODO socket_set_var  
	// TODO write_output_boolean_register  
	// TODO write_output_float_register  
	// TODO write_output_integer_register  
	// TODO write_port_bit  
	// TODO write_port_register  
	// TODO zero_ftsensor  

	/* force()
	Returns the force exerted at the TCP
	Return the current externally exerted force at the TCP. The force is the
	norm of Fx, Fy, and Fz calculated using get_tcp_force().
	Return Value
	The force in Newtons (float)
	Note: Refer to force_mode() for taring the sensor.
	*/
	public Double force(){  
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"force()");
		String reply = buildSendAndReceive(command);
		return type.scriptToDouble(reply);		
	}	
	// TODO get_actual_joint_positions  
	// TODO get_actual_joint_positions_history  
	// TODO get_actual_joint_speeds  
	
	/* get_actual_tcp_pose()
	Returns the current measured tool pose
	Returns the 6d pose representing the tool position and orientation
	specified in the base frame. The calculation of this pose is based on
	the actual robot encoder readings.
	Return Value
	The current actual TCP vector [X, Y, Z, Rx, Ry, Rz]
	*/
	public Pose get_actual_tcp_pose() {
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"get_actual_tcp_pose()");
		String reply = buildSendAndReceive(command);
		return type.scriptToPose(reply);			
	}
	// TODO get_actual_tcp_speed  
	// TODO get_actual_tool_flange_pose  
	// TODO get_controller_temp  
	// TODO get_forward_kin  
	// TODO get_inverse_kin  
	// TODO get_joint_temp  
	
	/* get_joint_torques()
	Returns the torques of all joints
	The torque on the joints, corrected by the torque needed to move the
	robot itself (gravity, friction, etc.), returned as a vector of length 6.
	Return Value
	The joint torque vector in Nm: [Base, Shoulder, Elbow, Wrist1,
	Wrist2, Wrist3]
	*/
	public List<Double> get_joint_torques(){
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"get_joint_torques()");
		String reply = buildSendAndReceive(command);
		return type.scriptToDoubleList(reply);		
	}
	// TODO get_steptime  
	// TODO get_target_joint_positions  
	// TODO get_target_joint_speeds  
	// TODO get_target_payload  
	// TODO get_target_payload_cog  
	// TODO get_target_tcp_pose  
	// TODO get_target_tcp_speed  
	// TODO get_target_waypoint  
	// TODO get_tcp_force  
	// TODO get_tcp_offset  
	// TODO get_tool_accelerometer_reading  
	// TODO get_tool_current  
	// TODO is_steady  
	// TODO is_within_safety_limits  
	// TODO popup  
	// TODO powerdown  
	// TODO set_gravity  

	// TODO set_payload  
	// TODO set_payload_cog  
	// TODO set_payload_mass  
	// TODO set_tcp  
	// TODO sleep  
	// TODO str_at  
	// TODO str_cat  
	// TODO str_empty  
	// TODO str_find  
	// TODO str_len  
	// TODO str_sub  
	// TODO sync  
	// TODO textmsg  
	// TODO to_num  
	// TODO to_str  
	// TODO tool_contact  
	// TODO tool_contact_examples  

	// TODO modbus_set_runstate_dependent_choice  
	// TODO set_analog_outputdomain  
	// TODO set_configurable_digital_input_action  
	// TODO set_gp_boolean_input_action  
	// TODO set_input_actions_to_default  
	// TODO set_runstate_configurable_digital_output_to_value  
	// TODO set_runstate_gp_boolean_output_to_value  
	// TODO set_runstate_standard_analog_output_to_value  
	// TODO set_runstate_standard_digital_output_to_value  
	// TODO set_runstate_tool_digital_output_to_value  
	// TODO set_standard_analog_input_domain  
	// TODO set_standard_digital_input_action  
	// TODO set_tool_analog_input_domain  
	// TODO set_tool_digital_input_action  

	// TODO conveyor_pulse_decode  
	// TODO encoder_enable_pulse_decode  
	// TODO encoder_enable_set_tick_count  
	// TODO encoder_get_tick_count  
	// TODO encoder_set_tick_count  
	// TODO encoder_unwind_delta_tick_count  
	// TODO end_force_mode  
	// TODO end_freedrive_mode  
	// TODO end_screw_driving  
	// TODO end_teach_mode  
	// TODO force_mode  
	// TODO force_mode_example  
	// TODO force_mode_set_damping  
	// TODO force_mode_set_gain_scaling  
	// TODO freedrive_mode  

	// TODO get_conveyor_tick_count  
	// TODO get_target_tcp_pose_along_path  
	// TODO get_target_tcp_speed_along_path  
	// TODO movec  
	// TODO movej  
	// TODO movel  
	// TODO movep  
	// TODO path_offset_disable  
	// TODO path_offset_enable  
	// TODO path_offset_get  
	// TODO path_offset_set  
	// TODO path_offset_set_alpha_filter  
	// TODO path_offset_set_max_offset  
	// TODO position_deviation_warning  
	// TODO reset_revolution_counter  
	// TODO screw_driving  
	// TODO servoc  
	// TODO servoj  
	// TODO set_conveyor_tick_count  
	// TODO set_pos  
	// TODO set_safety_mode_transition_hardness  
	// TODO speedj  
	// TODO speedl  
	// TODO stop_conveyor_tracking  
	// TODO stopj  
	// TODO stopl  
	// TODO teach_mode  
	// TODO track_conveyor_circular  
	// TODO track_conveyor_linear  

	// TODO mc_add_circular  
	// TODO mc_add_linear  
	// TODO mc_add_path  
	// TODO mc_get_target_rtcp_speed  
	// TODO mc_initialize  
	// TODO mc_load_path  
	// TODO mc_run_motion  
	// TODO mc_set_pcs  
	// TODO mc_set_speed_factor  

	// TODO acos  
	// TODO asin  
	// TODO atan  
	// TODO atan  
	// TODO binary_list_to_integer  
	// TODO ceil  
	// TODO cos  
	// TODO dr  

	// TODO floor  
	// TODO get_list_length  
	// TODO integer_to_binary_list  
	// TODO interpolate_pose  
	// TODO length  
	// TODO log  
	// TODO norm  
	// TODO normalize  
	// TODO point_dist  
	// TODO pose_add  
	// TODO pose_dist  
	/* pose_inv(p_from)
	Get the inverse of a pose
	Parameters
	p_from: tool pose (spatial vector)
	Return Value
	inverse tool pose transformation (spatial vector)
	Example command: pose_inv(p[.2,.5,.1,1.57,0,3.14])
	• Example Parameters:
	– p_from = p[.2,.5,.1,1.57,0,3.14] → The point
	∗ Returns p[0.19324,0.41794,-0.29662,1.23993,0.0,2.47985]
	*/
	public Pose pose_inv(Pose p_from) {
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"pose_inv("+ type.poseToScript(p_from)+")");
		String reply = buildSendAndReceive(command);
		return type.scriptToPose(reply);
		
	}
	// TODO pose_sub  
	/* pose_trans(p_from, p_from_to)
	Pose transformation
	The first argument, p_from, is used to transform the second argument,
	p_from_to, and the result is then returned. This means that the result is
	the resulting pose, when starting at the coordinate system of p_from,
	and then in that coordinate system moving p_from_to.
	This function can be seen in two different views. Either the function
	transforms, that is translates and rotates, p_from_to by the parameters
	of p_from. Or the function is used to get the resulting pose, when first
	making a move of p_from and then from there, a move of p_from_to.
	If the poses were regarded as transformation matrices, it would look
	like:
	T_world->to = T_world->from * T_from->to
	T_x->to = T_x->from * T_from->to
	Parameters
	p_from: starting pose (spatial vector)
	p_from_to: pose change relative to starting pose (spatial
	vector)
	Return Value
	resulting pose (spatial vector)
	Example command: pose_trans(p[.2,.5,.1,1.57,0,0],
	p[.2,.5,.6,1.57,0,0])
	• Example Parameters:
	– p_1 = p[.2,.5,.1,1.57,0,0] → The first point
	– p_2 = p[.2,.5,.6,1.57,0,0] → The second point
	∗ Returns p[0.4,-0.0996,0.60048,3.14,0.0,0.0]
	*/
	public Pose pose_trans(Pose p_from, Pose p_from_to){
		ScriptCommand command = new ScriptCommand(COMMAND);
		command.appendLine(ASSIGN_STRING+"pose_trans("+ type.poseToScript(p_from)+","+type.poseToScript(p_from_to)+")");
		String reply = buildSendAndReceive(command);
		return type.scriptToPose(reply);		
	}
	// TODO pow  
	// TODO rd  
	// TODO random  
	// TODO rotvecrpy  
	// TODO rpyrotvec  
	// TODO sin  
	// TODO sqrt  
	// TODO tan  
	// TODO wrench_trans  
	

}
