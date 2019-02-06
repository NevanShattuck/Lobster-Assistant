public class Transmitter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Some other guys hamming code
	

	//Hamming Code 
import java.util.*;

class sjs7007_Hamming_Code
{
	public static void main(String[] args) 
	{
		Scanner ip = new Scanner(System.in);
		System.out.print("Enter Message : ");
		String msg = ip.next();
		int r=0,m=msg.length(); 
		//calculate number of parity bits needed using m+r+1<=2^r
		while(true)
		{
			if(m+r+1<=Math.pow(2,r))
			{
				break;
			}
			r++;
		}
		System.out.println("Number of parity bits needed : "+r);
		int transLength = msg.length()+r,temp=0,temp2=0,j=0;
		int transMsg[]=new int[transLength+1]; //+1 because starts with 1
		for(int i=1;i<=transLength;i++)
		{
			temp2=(int)Math.pow(2,temp);
			if(i%temp2!=0)
			{
				transMsg[i]=Integer.parseInt(Character.toString(msg.charAt(j)));
				j++;
			}
			else
			{
				temp++;
			}
		}
		for(int i=1;i<=transLength;i++)
		{
			System.out.print(transMsg[i]);
		}
		System.out.println();	

		for(int i=0;i<r;i++)
		{
			int smallStep=(int)Math.pow(2,i);
			int bigStep=smallStep*2;
			int start=smallStep,checkPos=start;
			System.out.println("Calculating Parity bit for Position : "+smallStep);
			System.out.print("Bits to be checked : ");
			while(true)
			{
				for(int k=start;k<=start+smallStep-1;k++)
				{
					checkPos=k;
					System.out.print(checkPos+" ");
					if(k>transLength)
					{
						break;
					}
					transMsg[smallStep]^=transMsg[checkPos];
				}
				if(checkPos>transLength)
				{
					break;
				}
				else
				{
					start=start+bigStep;
				}
			}
			System.out.println();
		}	
		//Display encoded message
		System.out.print("Hamming Encoded Message : ");
		for(int i=1;i<=transLength;i++)
		{
			System.out.print(transMsg[i]);
		}
		System.out.println();
	}
}

/* Output
Enter Message : 1001
Number of parity bits needed : 3
0010001
Calculating Parity bit for Position : 1
Bits to be checked : 1 3 5 7 9 
Calculating Parity bit for Position : 2
Bits to be checked : 2 3 6 7 10 
Calculating Parity bit for Position : 4
Bits to be checked : 4 5 6 7 12 
Hamming Encoded Message : 0011001
*/
	
	
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
									else { System.out.println("No Hamming Done");}}}}
			
			if(m8) p[1] += 0x80;
			if(m7) p[1] += 0x40;
			if(m6) p[1] += 0x20;
			if(m5) p[1] += 0x10;
			if(m4) p[1] += 0x08;
			if(m3) p[1] += 0x04;
			if(m2) p[1] += 0x02;
			if(m1) p[1] += 0x01;
			
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
