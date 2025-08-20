// Exercise 11.2, Page 113

import tester.*;
// Represents a date
class Date2 {
    int day;
    int month;
    int year;

    Date2(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}

// Represents a temperature range
class TemperatureRange {
    int high;
    int low;

    TemperatureRange(int high, int low) {
        this.high = high;
        this.low = low;
    }

    int getHigh() {
      return this.high;
    }

    int getLow() {
      return this.low;
    }
}

// Represents a weather record
class WeatherRecord {
    Date2 d;
    TemperatureRange today;
    TemperatureRange normal;
    TemperatureRange record;
    double precipitation;

    WeatherRecord(Date2 d, TemperatureRange today, TemperatureRange normal, TemperatureRange record) {
        this.d = d;
        this.today = today;
        this.normal = normal;
        this.record = record;
    }

    boolean withinRange() {
      return this.today.getLow() >= this.normal.getLow() &&
              this.today.getHigh() <= this.normal.getHigh();
    }

    boolean rainyDay(double threshold) {
      return this.precipitation >= threshold;
    }

    boolean recordDay() {
      return this.today.getHigh() >= this.record.getHigh() ||
             this.today.getLow() <= this.record.getLow();
    }
}

class ExamplesWeatherRecord {
    Date2 date1 = new Date2(15, 3, 2023);
    TemperatureRange today1 = new TemperatureRange(20, 13);
    TemperatureRange normal1 = new TemperatureRange(22, 12);
    TemperatureRange record1 = new TemperatureRange(25, 8);
    WeatherRecord record1Obj = new WeatherRecord(date1, today1, normal1, record1);

    Date2 date2 = new Date2(16, 3, 2023);
    TemperatureRange today2 = new TemperatureRange(30, 8);
    TemperatureRange normal2 = new TemperatureRange(28, 14);
    TemperatureRange record2 = new TemperatureRange(32, 10);
    WeatherRecord record2Obj = new WeatherRecord(date2, today2, normal2, record2);

    boolean testWithinRange(Tester t) {
        return t.checkExpect(record1Obj.withinRange(), true) &&
               t.checkExpect(record2Obj.withinRange(), false);
    }

    boolean testRainyDay(Tester t) {
        record1Obj.precipitation = 5.0;
        record2Obj.precipitation = 0.0;
        return t.checkExpect(record1Obj.rainyDay(3.0), true) &&
               t.checkExpect(record2Obj.rainyDay(3.0), false);
    }

    boolean testRecordDay(Tester t) {
        return t.checkExpect(record1Obj.recordDay(), false) &&
               t.checkExpect(record2Obj.recordDay(), true);
    }
}
