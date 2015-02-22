
class PIDLoop {

	private long lastTime;
	private double Input, Output, Setpoint;
	private double ITerm, lastInput;
	private double kp, ki, kd;
	private int SampleTime = 1000;
	private double outMin, outMax;
	private boolean inAuto = false;

	private boolean MANUAL = false;
	private boolean AUTOMATIC = true;

	private boolean DIRECT = false;
	private boolean REVERSE = true;
	
	private double ffTableY[15] = 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0; 
    private double ffTablex[15] = 0,10,20,30,40,50,60,70,80,90,100,110,120,130,140,150;
	
	
	private boolean controllerDirection = DIRECT;

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
		
			SampleTime = (Long) NewSampleTime;
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
	//Computes the PID values to be updated
	public void Compute(){
		if(inAuto){
			long now = millis();
			int timeChange = (now - lastTime);
		
			if(timeChange >= SampleTime){
				//Compute all the working variables
				double error = Setpoint - Input;
				ITerm += (ki * error);
				if( ITerm > outMax ){
					ITerm = outMax;
				}
				else if( ITerm < outMin ){
					ITerm = outMin;
				}
				double dInput = (Input - lastInput);
			
				//Compute PID Output
				Output = kp * error + ITerm - kd * dInput;
			
				if( Output > outMax ){
					Output = outMax;
				}
				else if( Output < outMin ){
					Output = outMin;
				}
			
				//Remember some variables for next time
				lastInput = Input;
				lastTime = now;
			}
		}
	}
	// Calculate FF term from table
	public double CalcFF(){
		 for(int i=1; input ; i++){
		 
			  }

		(ffTable[ Math.ceil(_input) ] - ffTable[ Math.floor(_input) ]) / _input * Input 
	 
	}
}