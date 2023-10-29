#include <LiquidCrystal.h>
// Importamos la libería e instanciamos un objeto de LiquidCrystal
// El constructor de liquidCrystal(RS, E, D4, D5, D6, D7);
LiquidCrystal lcd(6, 7, 5, 4, 3, 2);
String cadenita;

void setup() {
  Serial.begin(9600);
  // begin(columna, fila);
  lcd.begin(16, 2);
  // setCursor(columna, fila);
  lcd.setCursor(0, 0);
  lcd.print(".- Alumnos BD -.");
  lcd.setCursor(0, 1);
  lcd.print("  BY ALEJANDRO  ");
  delay(5000);
  lcd.clear();
}

void loop() {
  lcd.setCursor(0, 0);
  while(Serial.available() > 0) {
    cadenita += char(Serial.read());
  }
  lcd.print(cadenita);
  for(int i = 0; i < 10; i++) {
    lcd.scrollDisplayLeft();
    delay(250);
  }
  cadenita = "";
}
