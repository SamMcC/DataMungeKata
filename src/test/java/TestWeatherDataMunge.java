import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestWeatherDataMunge {
    private WeatherDataMunge weatherDataMunge;

    @Before
    public void init(){
        String datFileLocation = "src/main/resources/weather.dat";
        weatherDataMunge = new WeatherDataMunge(datFileLocation);
    }

    @Test
    public void testReadReturnsData(){
        int row = 1;
        int column = 1;
        String actual = weatherDataMunge.readWeatherData(row,column);
        String emptyString = "";
        assertNotEquals(String.format("Expected: NOT %s", emptyString),emptyString, actual);
    }

    @Test
    public void testReadColumnThreeRowThreeReturnsFiftyFive(){
        int row = 3;
        int column = 3;
        String expected = "55";
        String actual = weatherDataMunge.readWeatherData(row,column);
        assertEquals(String.format("Expected: %s, Got: %s", expected, actual), expected, actual);
    }

    @Test
    public void testTemperatureSpreadForDayOneIsTwentyNine(){
        int day = 1;
        float expected = 29;
        float actual = weatherDataMunge.calculateTemperatureSpreadForDay(day);
        assertEquals(String.format("Expected: %s, Got: %s", expected, actual), expected, actual, 0.01f);
    }

    @Test
    public void testMinimumTemperatureSpreadIsFromDayFourteen(){
        int expected = 14;
        int actual = weatherDataMunge.calculateMinimumTemperatureSpread();
        assertEquals(String.format("Expected: %s, Got: %s", expected, actual), expected, actual);
    }
}
