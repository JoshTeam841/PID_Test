unsigned long lastTime;
double Input, Output, Setpoint;
double ITerm, lastInput;
double kp, ki, kd;
int SampleTime = 1000;
double outMin, outMax;
boolean inAuto = false;

boolean MANUAL = false;
boolean AUTOMATIC = true;

boolean DIRECT = false;
boolean REVERSE = true;

boolean controllerDirection = DIRECT;

public void initDefaultCommand(){
}
// Sets positive PID values 
public void SetTunings(double Kp, double, Ki, double Kd){
	if(Kp < 0 || Ki < 0 || Kd < 0 ){
		double SampleTimeInSec = SampleTime/1000;
		
		kp = Kp;
		ki = Ki * SampleTimeInSec;
		kd = Kd / SampleTimeinSec;
		if(controllerDirection == REVERSE){
			kp = (0 - kp);
			ki = (0 - ki);
			kd = (0 - kd);
		}
	}
}
// Sets Sample Time
public void SetSampleTime( int NewSampleTime){
	if (NewSampleTime > 0){
		
	}
}
