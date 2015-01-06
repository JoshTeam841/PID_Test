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
	if(Kp > 0 || Ki > 0 || Kd > 0 ){
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
		double ratio = (double) NewSampleTime / (double) SampleTime;
		
		ki *=ratio;
		kd /=ratio;
		
		SampleTime = (Unsigned Long) NewSampleTime;
	}
}
// Sets Limit to Output
public void SetOutputLimits( double Min, double Max){
	if(Min < Max){
		outMin = Min;
		outMax = Max;
		
		if(Output > outMax){
			Output = outMax;
		}
		else if(Output < outMin){
			Output = outMin;
		}
		if (ITerm > outMax){
			ITerm = outMax;
		}
		else if(ITerm < outMin){
			Iterm = outMin;
		}
	}
}
// Turns on PID loop
public void SetMode( boolean Mode){
	boolean newAuto = Mode;
	if(newAuto && !inAuto){
		Initialize();
	}
	inAuto =  newAuto;
}
//Clears up all data from past PID loop
public void Initialize(){
	lastInput = Input;
	ITerm = Output;
	if (ITerm > outMax){
		ITerm = outMax;
	}
	else if(ITerm < outMin){
		Iterm = outMin;
	}
}
//Set the PID output polarity
public void SetControllerDirection (boolean Direction){
	controllerDirection = Direction;
}
public void Compute(){
	if(inAuto){
		unsigned long now = millis();
	}
}