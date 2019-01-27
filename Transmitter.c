
//Microcontroller: can't hold a whole image contents, if using tx and rx it may just act as a wire with syncing process, if we can extend EEPROM we can
//condense images, We could send data in strings of 1000 Bytes. Let's talk to the group about that

#include<stdlib.h>
#include<io.h>
#include<util/delay.h>
#include<avr/interrupt.h>
//Initialize USART settings
void USART_init(unsigned int baud) {
 //set baud rate? !!!idk what we need this creates baud rate of 31250 bit/s
 UBRRH = (unsigned char)(baud>>8);
  UBRRL = (unsigned char)baud;
  //enable reciever and transmitter
  UCSRB = (1<<RXEN)|(1<<TXEN);
  //set data format: 8data, 1stop !!!(This is not th format we are working with)
  UCSRC = (1<<URSEL)|(3<<UCSZ0);
}

//Transmit data from board to PC
void USART_transmit(unsigned char data) {
  //wait for empty transmit buffer
  while( !(UCSRA & (1<<UDRE)) ) ;
  //put data into buffer to send data
  UDR = data;
}

//Recieve data from PC to board
unsigned char USART_receive(void) {
 //wait for data to be received while we remain in record mode & timer is under 4sec !!! 4 seconds is also wrong
 while( !(UCSRA & (1<<RXC)) && (TCNT1 < 62500) && ((PINA & (1<<2)) == 0b00000100)){
 // !!!!TCNT1 is based on a different data rate
 }
 if(TCNT1 >= 62500) return '\0'; //break out of record mode if timer is over 4sec
 if((PINA & (1<<2)) != 0b00000100) return '\0'; //break out of record mode if mode is switched
 //get and return recieved data from buffer
 return (unsigned char) UDR;
}
//EEPROM has 1kB
//Write 1 byte to EEPROM
unsigned int EEPROM_Write(unsigned int new_addr, unsigned char new_data) {
 char cSREG;
 cSREG = SREG; // Save interrupt status
 cli(); // Disable interrupts
 while(EECR & (1 << EEWE)); //2 // Wait for previous write to finish
 while(SPMCR & (1 << SPMEN)); //1
 EEAR = new_addr; // Store address
 EEDR = new_data; // Store data
 EECR |= (1 << EEMWE);//4
 EECR |= (1 << EEWE);//2
 SREG = cSREG; // Restore interrupt status
 return 0;
}
//Read 1 byte from EEPROM
unsigned char EEPROM_Read(unsigned int address){
 char cSREG;
 cSREG = SREG; // Save interrupt status
 cli(); // Disable interrupts
 while(EECR & (1 << EEWE)); // Wait for any writes to finish
 EEAR = address;
 EECR |= (1 << EERE);
 SREG = cSREG; // Restore interrupt status
 return EEDR;
}

int main(void){
 
 //General Setup
 DDRA = 0b00111111; //set A pins as input
 DDRB = 0xFF; //set B pins as output
 TCCR1B |= (1 << CS12); //set up clock prescaler!!!it's different
 USART_init(7); //initialize USART setting!!!possibly different
 //Say pin 1 turns the transmitter on
 while(PINA & 1 == 0b00000001){
  
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



//Lab 2 CompSys
/*
#include<stdlib.h>
#include<io.h>
#include<util/delay.h>
#include<avr/interrupt.h>
//Initialize USART settings
void USART_init(unsigned int baud) {
 //set baud rate
 UBRRH = (unsigned char)(baud>>8);
  UBRRL = (unsigned char)baud;
  //enable reciever and transmitter
  UCSRB = (1<<RXEN)|(1<<TXEN);
  //set data format: 8data, 1stop
  UCSRC = (1<<URSEL)|(3<<UCSZ0);
}

//Transmit data from board to PC
void USART_transmit(unsigned char data) {
  //wait for empty transmit buffer
  while( !(UCSRA & (1<<UDRE)) ) ;
  //put data into buffer to send data
  UDR = data;
}

//Recieve data from PC to board
unsigned char USART_receive(void) {
 //wait for data to be received while we remain in record mode & timer is under 4sec
 while( !(UCSRA & (1<<RXC)) && (TCNT1 < 62500) && ((PINA & (1<<2)) == 0b00000100)){
 //if(TCNT1 > 12500){ //////////////////////////////////////////////////////////
 // PORTB = 0x00;
 //}
 }
 if(TCNT1 >= 62500) return '\0'; //break out of record mode if timer is over 4sec
 if((PINA & (1<<2)) != 0b00000100) return '\0'; //break out of record mode if mode is switched
 //get and return recieved data from buffer
 return (unsigned char) UDR;
}

//Configure ADC settings
void ADC_init(void) {
 ADMUX = (3 << REFS0)|(0b00000111 << MUX0);
 ADCSRA = (1 << ADEN);
}
//Get factor by which to modulate data from photocell voltage
unsigned char ADC_mod() {
 unsigned char data1, data2;
 unsigned int factor, concat;
 while( (PINA & 1) == 0b00000001 ) {
 ADCSRA |= (0b01000000 & (1 << ADSC));
 //wait for ADC conversion
 while( (0b00010000 & (1 << ADIF)) == 0b00000000 ) ;
 data1 = ADCL;
 data2 = ADCH;
 concat = (data2 << 8) | data1;
 factor = (unsigned char) (concat / (unsigned int) 4);
 return factor;
 }
 return 0;
}
//not sure about this --> did Nevan write this?
unsigned char ADC_mod2() {
ADMUX = (3 << REFS0)|(0b00000110 << MUX0);
 unsigned char data1, data2;
 unsigned int factor, concat;
 while( (PINA & 1) == 0b00000001 ) {
 ADCSRA |= (0b01000000 & (1 << ADSC));
 //wait for ADC conversion
 while( (0b00010000 & (1 <<ADIF)) == 0b00000000 ) ;
 data1 = ADCL;
 data2 = ADCH;
 concat = (data2 << 8) | data1;
 factor = (unsigned char) (concat / (unsigned int) 4);
 ADMUX = (3 << REFS0)|(0b00000111 << MUX0);
 return factor;
 }
 ADMUX = (3 << REFS0)|(0b00000111 << MUX0);
 return 0;
}

//Write 1 byte to EEPROM
unsigned int EEPROM_Write(unsigned int new_addr, unsigned char new_data) {
 char cSREG;
 cSREG = SREG; // Save interrupt status
 cli(); // Disable interrupts
 while(EECR & (1 << EEWE)); //2 // Wait for previous write to finish
 while(SPMCR & (1 << SPMEN)); //1
 EEAR = new_addr; // Store address
 EEDR = new_data; // Store data
 EECR |= (1 << EEMWE);//4
 //EECR ^= (1 << EEWE);//2
 // ^ not sure if needed
 EECR |= (1 << EEWE);//2
 SREG = cSREG; // Restore interrupt status
 //new_addr += 0x01; // Increment address by a byte
 return 0;
}

//Read 1 byte from EEPROM
unsigned char EEPROM_Read(unsigned int address){
 char cSREG;
 cSREG = SREG; // Save interrupt status
 cli(); // Disable interrupts
 while(EECR & (1 << EEWE)); // Wait for any writes to finish
 EEAR = address;
 EECR |= (1 << EERE);
 SREG = cSREG; // Restore interrupt status
 //address += 0x01; // Increment address by 1 byte
 //if(TCNT1 > 12500) //////////////////////////////////////////////////////////
 // PORTB = 0x00;
 return EEDR;
}

//Write 2 bytes to EEPROM
unsigned int EEPROM_Write2Bytes(unsigned int new_addr, unsigned int new_data){
 EEPROM_Write(new_addr, (new_data >> 8));
 EEPROM_Write(new_addr + 0x01, (new_data << 8 >> 8));
 return 0;
}

//Read 2 bytes from EEPROM
unsigned int EEPROM_Read2Bytes(unsigned int address){
 unsigned int msB = EEPROM_Read(address);
 unsigned int lsB = EEPROM_Read(address + 0x01);
 unsigned int rret = (msB << 8) | lsB;
 return rret;
}

ISR(TIMER1_COMPB_vect){
PORTB = 0x00;
}

int main(void){
 
 //General Setup
 DDRA = 0b00111111; //set A pins as input
 DDRB = 0xFF; //set B pins as output
 TCCR1B |= (1 << CS12); //set up clock prescaler
 OCR1B = 12500;
 USART_init(7); //initialize USART setting
 
 TIMSK = (1 << OCIE1B); //enable "Compare B Match Interrupt"
 sei(); //enable global interrupts
 
 ADC_init();
 while(1){
  unsigned char data1, data2;
  unsigned int concat;
  while( (PINA & 1) == 0b00000001 );
  ADCSRA |= (0b01000000 & (1 << ADSC));
  //wait for ADC conversion
  while( (0b00010000 & (1 << ADIF)) == 0b00000000 ) ;
  data1 = ADCL;
  data2 = ADCH;
  concat = (data2 << 8) | data1;
  PORTB = concat;
 }
 //Infinite loop of modes
 while(1) {
  //IN RECORD MODE
  if( (PINA & (1<<2)) == 0b00000100 ) {
   
   unsigned int end_addr = 0x02; //set up memory allocation
   EEPROM_Write2Bytes(0, end_addr);
   //EEPROM_Write2Bytes(0, 0x02);
   unsigned char msb, mmb, lsb; //bytes to store MIDI data
   TCNT1 = 0; //start timer
   while((PINA & (1<<2)) == 0b00000100){
    msb = USART_receive(); //poll for incoming msb of MIDI note
    if(msb == '\0'){ //check if 4s has elapsed & still in record mode
     TCNT1 = 0;
     PORTB = 0xFF;
     while(TCNT1 < 50);
     PORTB = 0x00;
     while((PINA & (1<<2)) == 0b00000100);
     //break; //break out of record mode if not
    }
    if(EEPROM_Read2Bytes(0) > 0x3FC){
     while((PINA & (1<<2)) == 0b00000100);
    }
    EEPROM_Write2Bytes(EEPROM_Read2Bytes(0), (TCNT1 + 128)); //write ajusted timer value to EEPROM
    TCNT1 = 0; //reset timer
    EEPROM_Write2Bytes(0, EEPROM_Read2Bytes(0) + 0x02); //move pointer
    mmb = USART_receive(); //poll for incoming mmb
    PORTB = mmb;
    lsb = USART_receive(); //poll for incoming lsb
    EEPROM_Write(EEPROM_Read2Bytes(0), msb); //write msb
    EEPROM_Write2Bytes(0, EEPROM_Read2Bytes(0) + 0x01); //move pointer
    EEPROM_Write(EEPROM_Read2Bytes(0), mmb); //write mmb
    EEPROM_Write2Bytes(0, EEPROM_Read2Bytes(0) + 0x01); //move pointer
    EEPROM_Write(EEPROM_Read2Bytes(0), lsb); //write lsb
    EEPROM_Write2Bytes(0, EEPROM_Read2Bytes(0) + 0x01); //move pointer
   }
  }

  //IN PLAYBACK MODE
  if( (PINA & (1<<1)) == 0b00000010 ) {
   USART_init(7);
   //IN MODULATION MODE
   if( (PINA & 1) == 0b00000001 ) {
    ADC_init(); //initialize ADC
   }
   unsigned int startaddr = 0x02;
   while(startaddr != EEPROM_Read2Bytes(0) && (PINA & (1<<1)) == 0b00000010){
    int telap = EEPROM_Read2Bytes(startaddr); // Read 2 bytes of time data
    int factor;
    int median = 90;
    //IN MODULATION MODE
    if( (PINA & 1) == 0b00000001 ) {
     factor = ADC_mod(); //use the ADC to get a factor by which to modulate timing
      if (factor < median){
       factor = median -factor;
       if (factor < 1)
        factor = 1;
       telap = telap/factor;
      }
      else{
       factor -= median;
       if (factor < 1)
        factor = 1;
       telap = telap * factor;
      }
     }
     startaddr += 0x02; // Increment address to read by 2 bytes
     TCNT1 = 0; // Reset timer
 
     while(TCNT1 < telap);
     unsigned char data;
     data = EEPROM_Read(startaddr); // Read first byte of note
     USART_transmit(data); // Transmit it
     startaddr += 0x01; // Move address by 1 byte
     data = EEPROM_Read(startaddr); // Read second byte of note
     //IN MOD MODE
     if( (PINA & 1) == 0b00000001 ) {
      data = ADC_mod2();
     }
     USART_transmit(data); // Transmit it
     PORTB = data;
     startaddr += 0x01; // Move address by 1 byte
     data = EEPROM_Read(startaddr); // Read third byte of note
     USART_transmit(data); // Transmit it
     startaddr += 0x01; // Move address by 1 byte
    }	
   }
 }
}
*/
