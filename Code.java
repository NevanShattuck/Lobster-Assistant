import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Transmitter {

	final int bittime = 100; //in milli
	final int deslength = 2; //designated legnth of signal in bytes including parity
	
	//Creating pins for transmission
			// create gpio controller
	        	final GpioController gpio = GpioFactory.getInstance();

	        	// provision gpio pin #01 as an output pin and turn on
	        	final GpioPinDigitalOutput pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "35kHz", PinState.LOW);
	        	final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "27kHz", PinState.LOW);
	        	final GpioPinDigitalInput pin2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02);

	        	
			// functions include pin0.toggle() .low() .high() .pulse(time milli, true/false), Thread.sleep(time milli), gpio.shutdown()
		
	public static void main(String[] args) {
		HammingTest((byte) 20);
		Transmitter tran = new Transmitter();
		byte[] byt = new byte[]{(byte) 0b10101010, 0b00110011, 0b00001111};
		try {
			tran.Transmit(byt);
	     } catch (InterruptedException e) {
	         Thread.currentThread().interrupt();  // set interrupt flag
	         System.out.println("Failed to transmit byte sum");
	     }
		}
	
	public void Transmit(byte d[]) throws InterruptedException {
		// set shutdown state for this pin
    	pin0.setShutdownOptions(true, PinState.LOW);
    	pin1.setShutdownOptions(true, PinState.LOW);
		pin1.pulse(bittime,true);
		Thread.sleep(bittime);
		pin0.pulse(bittime,true);
		Thread.sleep(bittime);
		for(int i = 1; i < deslength + 1; i++){
			for(int j = 0; j<8; j++){
				if((d[i] & (2^j)) == (2^j)){
					pin1.pulse(bittime,true);	
				}
				else{
					pin0.pulse(bittime,true);
				}
				Thread.sleep(bittime);
			}
		}
		//make sure reciever stays high
		pin1.pulse(bittime,true);
	}
	
	public byte[] Recieve() throws InterruptedException{
		byte d[] = new byte[deslength];
		while(pin2.isHigh());
		Thread.sleep((int)(bittime*1.5));
		for(int i = 1; i < deslength+1; i++){
			for( int j = 0; j<8; j++){
				if(pin2.isHigh()){
				d[i] += 2^j;	
				}
				else{
					
				}
				Thread.sleep(bittime);
			}
		}
		return d;
	}
	
	public static byte[] SVDCompression(byte d[]) {
		byte c[] = d;
		return c;
	}
	
	public static byte[] HammingEncoder(byte d[]) {
		//Hamming even?
		byte c[] = {0};
		
		boolean m8 = ((d[0] & 0x80) == 0x80);
		boolean m7 = ((d[0] & 0x40) == 0x40);
		boolean m6 = ((d[0] & 0x20) == 0x20);
		boolean m5 = ((d[0] & 0x10) == 0x10);
		boolean m4 = ((d[0] & 0x08) == 0x08);
		boolean m3 = ((d[0] & 0x04) == 0x04);
		boolean m2 = ((d[0] & 0x02) == 0x02);
		boolean m1 = ((d[0] & 0x01) == 0x01);
		
		if(d.length==1) {
			byte p[] = new byte[2];
			if(m1^m2^m4^m5^m7) {
				p[0]+=0x01;
			}
			if(m1^m3^m4^m6^m7) {
				p[0]+=0x02;
			}
			if(m2^m3^m4^m8) {
				p[0]+=0x04;
			}
			if(m5^m6^m7^m8) {
				p[0]+=0x08;
			}
			p[1] = d[0];
			c = p;
		}
		else {
		System.out.println("Number of bytes not supported yet"); 
		}
		return c;
	}
	
	public static byte[] HammingDecoder(byte d[]) {
		//Hamming even?
		byte c[] = {0};
		
		boolean e1 = false;
		boolean e2 = false;
		boolean e3 = false;
		boolean e4 = false;
		
		boolean m8 = ((d[1] & 0x80) == 0x80);
		boolean m7 = ((d[1] & 0x40) == 0x40);
		boolean m6 = ((d[1] & 0x20) == 0x20);
		boolean m5 = ((d[1] & 0x10) == 0x10);
		boolean m4 = ((d[1] & 0x08) == 0x08);
		boolean m3 = ((d[1] & 0x04) == 0x04);
		boolean m2 = ((d[1] & 0x02) == 0x02);
		boolean m1 = ((d[1] & 0x01) == 0x01);
		
		boolean p4 = ((d[0] & 0x08) == 0x08);
		boolean p3 = ((d[0] & 0x04) == 0x04);
		boolean p2 = ((d[0] & 0x02) == 0x02);
		boolean p1 = ((d[0] & 0x01) == 0x01);
		
		if(d.length==2) {
			byte p[] = new byte[1];
			if(m1^m2^m4^m5^m7^p1) {
				e1 = true;
			}
			if(m1^m3^m4^m6^m7^p2) {
				e2 = true;
			}
			if(m2^m3^m4^m8^p3) {
				e3 = true;
			}
			if(m5^m6^m7^m8^p4) {
				e4 = true;
			}
			//I have to look more in depth if their is java code for this because this will take forever
			if(e1){ 	if(e2){ 	if(e3){ 	if(e4) System.out.println("Error with hamming Decoding");
									else {	if(m4) m4 = false;
										else m4 = true;}}
							else {		if(e4){ if(m7) m7 = false;
				       						else m7 = true; }
									else{	if(m1) m1 = false;
										else m1 = true;}}}
					else{ 		if(e3){ 	if(e4){ System.out.println("Error with hamming Decoding");}
					     				else{ 	if(m2) m2 = false;
					     					else m2 = true;}}
							else { 		if(e4) {if(m5) m5 = false;
										else m5 = true;}
									else { 	if(p1) p1 = false;
										else p1 = true;}}}}
			else { 		if(e2){ 	if(e3){ 	if(e4) System.out.println("Error with hamming Decoding");
									else { 	if(m3) m3 = false;
										else m3 = true;}}
							else { 		if(e4) { if(m6) m6 = false;
										else m6 = true;}
									else { 	if(p2) p2 = false;
										else p2 = true;}}}
					else {		if(e3) {	if(e4) {if(m8) m8 = false;
										else m8 = true;}
									else {	if(p3) p3 = false;
										else p3 = true;}}
							else {		if(e4) { if(p4) p4 = false;
										else p4 = true;}
									else { System.out.println("No Hamming Correction Done");}}}}
			
			if(m8) p[0] += 0x80;
			if(m7) p[0] += 0x40;
			if(m6) p[0] += 0x20;
			if(m5) p[0] += 0x10;
			if(m4) p[0] += 0x08;
			if(m3) p[0] += 0x04;
			if(m2) p[0] += 0x02;
			if(m1) p[0] += 0x01;
			
			c = p;
		}
		else {
		System.out.println("Number of bytes not supported yet"); 
		}
		return c;
	
	}
	public static byte[] SVDDecompression(byte d[]) {
		byte c[] = d;
		return c;
	}

	
	public static void HammingTest(byte b) {
	
		byte[] a = {b};
		System.out.println(a[0]);
		a = HammingEncoder(a);
		System.out.println(a[0]);
		a = HammingDecoder(a);
		System.out.println(a[0]);
		
	}
}
