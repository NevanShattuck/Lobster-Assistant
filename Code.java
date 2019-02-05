public class Transmitter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public byte[] encoder(byte d[]) {
		//Hamming even?
		byte c[] = {0};
		
		boolean m8 = ((d[1] & 0x80) == 0x80);
		boolean m7 = ((d[1] & 0x40) == 0x40);
		boolean m6 = ((d[1] & 0x20) == 0x20);
		boolean m5 = ((d[1] & 0x10) == 0x10);
		boolean m4 = ((d[1] & 0x08) == 0x08);
		boolean m3 = ((d[1] & 0x04) == 0x04);
		boolean m2 = ((d[1] & 0x02) == 0x02);
		boolean m1 = ((d[1] & 0x01) == 0x01);
		
		if(d.length==1) {
			byte p[] = new byte[2];
			if(m1^m2^m4^m5^m7) {
				p[1]+=0x01;
			}
			if(m1^m3^m4^m6^m7) {
public class Transmitter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public byte[] SVDCompression(byte d[]) {
		byte c[] = d;
		return c;
	}
	
	public byte[] HammingEncoder(byte d[]) {
		//Hamming even?
		byte c[] = {0};
		
		boolean m8 = ((d[1] & 0x80) == 0x80);
		boolean m7 = ((d[1] & 0x40) == 0x40);
		boolean m6 = ((d[1] & 0x20) == 0x20);
		boolean m5 = ((d[1] & 0x10) == 0x10);
		boolean m4 = ((d[1] & 0x08) == 0x08);
		boolean m3 = ((d[1] & 0x04) == 0x04);
		boolean m2 = ((d[1] & 0x02) == 0x02);
		boolean m1 = ((d[1] & 0x01) == 0x01);
		
		if(d.length==1) {
			byte p[] = new byte[2];
			if(m1^m2^m4^m5^m7) {
				p[1]+=0x01;
			}
			if(m1^m3^m4^m6^m7) {
				p[1]+=0x02;
			}
			if(m2^m3^m4^m8) {
				p[1]+=0x04;
			}
			if(m5^m6^m7^m8) {
				p[1]+=0x08;
			}
			p[2] = d[1];
			c = p;
		}
		else {
		System.out.println("Number of bytes not supported yet"); 
		}
		return c;
	}
	
	public byte[] HammingDecoder(byte d[]) {
		//Hamming even?
		byte c[] = {0};
		
		boolean e1 = false;
		boolean e2 = false;
		boolean e3 = false;
		boolean e4 = false;
		
		boolean m8 = ((d[2] & 0x80) == 0x80);
		boolean m7 = ((d[2] & 0x40) == 0x40);
		boolean m6 = ((d[2] & 0x20) == 0x20);
		boolean m5 = ((d[2] & 0x10) == 0x10);
		boolean m4 = ((d[2] & 0x08) == 0x08);
		boolean m3 = ((d[2] & 0x04) == 0x04);
		boolean m2 = ((d[2] & 0x02) == 0x02);
		boolean m1 = ((d[2] & 0x01) == 0x01);
		
		boolean p4 = ((d[1] & 0x08) == 0x08);
		boolean p3 = ((d[1] & 0x04) == 0x04);
		boolean p2 = ((d[1] & 0x02) == 0x02);
		boolean p1 = ((d[1] & 0x01) == 0x01);
		
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
			if(e1) { if(e2) { if(e3) { if(e4) System.out.println("Error with hamming Decoding");
			else {if(m4) m4 = false;
			else m4 = true;
			}
			}}}
			p[2] = d[1];
			c = p;
		}
		else {
		System.out.println("Number of bytes not supported yet"); 
		}
		return c;
	}
	
	public byte[] SVDDecompression(byte d[]) {
		byte c[] = d;
		return c;
	}

}
/* Arduino Transmitter
int message[8];
 short del = 10;
 short play = 100;
 int on = 0;
 int f0 = 35; //freq in kHz for 0
 int f1 = 27; //^ for 1
 
void setup() {
  // put your setup code here, to run once:
  pinMode(10, OUTPUT);
  Serial.begin(9600);
  Serial.println("start");
  //data bits
  message[0] = digitalRead(2);
  message[1] = digitalRead(3);
  message[2] = digitalRead(4);
  message[3] = digitalRead(5);
  message[4] = digitalRead(6);
  message[5] = digitalRead(7);
  message[6] = digitalRead(8);
  message[7] = digitalRead(9);
  Serial.println();
  for(int i =0; i<8; i++){
    Serial.print(message[i]);
  }
}
void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(11)==1){
    on = 1;
  }
  if(on ==1){
    sync();
    while(digitalRead(11)==1){
      for(int i=0; i<8; i++){
        if(message[i]==1){
          playfreqk(play,f1);
          delay(del);
        }
        if(message[i]==0){
          playfreqk(play,f0);
          delay(del);
     }}}
    on = 0;
    playfreqk(1,f1);
}}
void sync(){
  Serial.println("sync");
  //data bits
  message[0] = digitalRead(2);
  message[1] = digitalRead(3);
  message[2] = digitalRead(4);
  message[3] = digitalRead(5);
  message[4] = digitalRead(6);
  message[5] = digitalRead(7);
  message[6] = digitalRead(8);
  message[7] = digitalRead(9);
  Serial.println();
  for(int i =0; i<8; i++){
    Serial.print(message[i]);
  }
  Serial.println();
  playfreqk(play,f1);
  delay(del);
  playfreqk(play,f0);
  delay(del);
  playfreqk(play,f1);
  delay(del);
}
//Ardino Reciever
int dig = 1;
int lastDig = 1;
const int a = 7;
int diga[a];
int on = 0;
int del = 1000; //initial and end delay on bit reading
long T = 102000; //time of one bit in microseconds
//short delsync = T/3; not used anymore//delay between sampling on sync
unsigned long tim;
boolean byt[8];
float wait = 1.3;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(A0, OUTPUT);
  pinMode(3,OUTPUT);
  pinMode(4,OUTPUT);
  pinMode(5,OUTPUT);
  pinMode(6,OUTPUT);
  pinMode(7,OUTPUT);
  pinMode(8,OUTPUT);
  pinMode(9,OUTPUT);
  pinMode(10,OUTPUT);
}
void loop() {
  // put your main code here, to run repeatedly:
  int l = 0;
  if(digitalRead(11) ==1){
    on = 1;
  }
  if (on ==1){
    //l = 0; I don't know who put this here or why
    Serial.println("s");
    sync();
    //delay(T/1000);
    delay(wait*T/1000);
    Serial.print("d");
    //This loop prints correct values as long as nothing changes the timing of it and it doesn't go on longer than like 70
  for(int i=0; i<8; i++){
    tim = millis();
    int ave =0;
    int b = a;
    for(int k=0; k<a;k++){
        delayMicroseconds(T/a/3);
        diga[k] = digitalRead(2);
        Serial.print(diga[k]);
        if(tim+(T*(2-wait)/1000)<=millis()){
          b = k-1;
        }
      }
     if(ave==0){
       Serial.println();
       ave++;
       int sum = 0;
       for(int j=0; j<b; j++){
          sum += diga[j];
        }
        if(sum > b/2){
          if(l<8){
            byt[l] = 1;
            l++;
          }
        }
        else{
          if(l<8){
            byt[l] = 0;
            l++;
          }
        }
      }
      if(millis()<tim+(T*(2-wait)/1000)){
        delay((T/1000)+tim-millis());
      }
    }
for(int q = 0; q<8; q++){Serial.print(byt[q]);}
Serial.println();
  for(int q = 0; q<8; q++){Serial.print(byt[q]);}
  digitalWrite(3, byt[0]);
  digitalWrite(4, byt[1]);
  digitalWrite(5, byt[2]);
  digitalWrite(6, byt[3]);
  digitalWrite(7, byt[4]);
  digitalWrite(8, byt[5]);
  digitalWrite(9, byt[6]);
  digitalWrite(10, byt[7]);
  on = 0;
}
}
void sync(){
int count = 0;
long t0 = 0;
int t1 = 0;
int readin = digitalRead(2);
while(readin==0){
  readin = digitalRead(2);
 }
while(readin==1){
  readin = digitalRead(2);
 }
t0 = millis();
while(millis()<=(t0+(T/2000))||readin ==1){
  readin = digitalRead(2);
 }
while(readin==0){
  count++;
  readin = digitalRead(2);
 }
t1 = millis();
//Serial.println((t1 - t0)*1000);
Serial.println(T);
}
*/
				p[1]+=0x02;
			}
			if(m2^m3^m4^m8) {
				p[1]+=0x04;
			}
			if(m5^m6^m7^m8) {
				p[1]+=0x08;
			}
			p[2] = d[1];
			c = p;
		}
		else {
		System.out.println("Number of bytes not supported yet"); 
		}
		return c;
	}

}
/* Arduino Transmitter
int message[8];
 short del = 10;
 short play = 100;
 int on = 0;
 int f0 = 35; //freq in kHz for 0
 int f1 = 27; //^ for 1
 
void setup() {
  // put your setup code here, to run once:
  pinMode(10, OUTPUT);
  Serial.begin(9600);
  Serial.println("start");
  //data bits
  message[0] = digitalRead(2);
  message[1] = digitalRead(3);
  message[2] = digitalRead(4);
  message[3] = digitalRead(5);
  message[4] = digitalRead(6);
  message[5] = digitalRead(7);
  message[6] = digitalRead(8);
  message[7] = digitalRead(9);
  Serial.println();
  for(int i =0; i<8; i++){
    Serial.print(message[i]);
  }
}
void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(11)==1){
    on = 1;
  }
  if(on ==1){
    sync();
    while(digitalRead(11)==1){
      for(int i=0; i<8; i++){
        if(message[i]==1){
          playfreqk(play,f1);
          delay(del);
        }
        if(message[i]==0){
          playfreqk(play,f0);
          delay(del);
     }}}
    on = 0;
    playfreqk(1,f1);
}}
void sync(){
  Serial.println("sync");
  //data bits
  message[0] = digitalRead(2);
  message[1] = digitalRead(3);
  message[2] = digitalRead(4);
  message[3] = digitalRead(5);
  message[4] = digitalRead(6);
  message[5] = digitalRead(7);
  message[6] = digitalRead(8);
  message[7] = digitalRead(9);
  Serial.println();
  for(int i =0; i<8; i++){
    Serial.print(message[i]);
  }
  Serial.println();
  playfreqk(play,f1);
  delay(del);
  playfreqk(play,f0);
  delay(del);
  playfreqk(play,f1);
  delay(del);
}
//Ardino Reciever
int dig = 1;
int lastDig = 1;
const int a = 7;
int diga[a];
int on = 0;
int del = 1000; //initial and end delay on bit reading
long T = 102000; //time of one bit in microseconds
//short delsync = T/3; not used anymore//delay between sampling on sync
unsigned long tim;
boolean byt[8];
float wait = 1.3;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(A0, OUTPUT);
  pinMode(3,OUTPUT);
  pinMode(4,OUTPUT);
  pinMode(5,OUTPUT);
  pinMode(6,OUTPUT);
  pinMode(7,OUTPUT);
  pinMode(8,OUTPUT);
  pinMode(9,OUTPUT);
  pinMode(10,OUTPUT);
}
void loop() {
  // put your main code here, to run repeatedly:
  int l = 0;
  if(digitalRead(11) ==1){
    on = 1;
  }
  if (on ==1){
    //l = 0; I don't know who put this here or why
    Serial.println("s");
    sync();
    //delay(T/1000);
    delay(wait*T/1000);
    Serial.print("d");
    //This loop prints correct values as long as nothing changes the timing of it and it doesn't go on longer than like 70
  for(int i=0; i<8; i++){
    tim = millis();
    int ave =0;
    int b = a;
    for(int k=0; k<a;k++){
        delayMicroseconds(T/a/3);
        diga[k] = digitalRead(2);
        Serial.print(diga[k]);
        if(tim+(T*(2-wait)/1000)<=millis()){
          b = k-1;
        }
      }
     if(ave==0){
       Serial.println();
       ave++;
       int sum = 0;
       for(int j=0; j<b; j++){
          sum += diga[j];
        }
        if(sum > b/2){
          if(l<8){
            byt[l] = 1;
            l++;
          }
        }
        else{
          if(l<8){
            byt[l] = 0;
            l++;
          }
        }
      }
      if(millis()<tim+(T*(2-wait)/1000)){
        delay((T/1000)+tim-millis());
      }
    }
for(int q = 0; q<8; q++){Serial.print(byt[q]);}
Serial.println();
  for(int q = 0; q<8; q++){Serial.print(byt[q]);}
  digitalWrite(3, byt[0]);
  digitalWrite(4, byt[1]);
  digitalWrite(5, byt[2]);
  digitalWrite(6, byt[3]);
  digitalWrite(7, byt[4]);
  digitalWrite(8, byt[5]);
  digitalWrite(9, byt[6]);
  digitalWrite(10, byt[7]);
  on = 0;
}
}
void sync(){
int count = 0;
long t0 = 0;
int t1 = 0;
int readin = digitalRead(2);
while(readin==0){
  readin = digitalRead(2);
 }
while(readin==1){
  readin = digitalRead(2);
 }
t0 = millis();
while(millis()<=(t0+(T/2000))||readin ==1){
  readin = digitalRead(2);
 }
while(readin==0){
  count++;
  readin = digitalRead(2);
 }
t1 = millis();
//Serial.println((t1 - t0)*1000);
Serial.println(T);
}
*/
