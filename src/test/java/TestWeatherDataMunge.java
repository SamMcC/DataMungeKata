import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestWeatherDataMunge {
    private WeatherDataMunge weatherDataMunge;

    @Before
    public void init(){
        String datFileLocation = "../resources/weather.dat";
        weatherDataMunge = new WeatherDataMunge(datFileLocation);
    }

    @Test
    public void testReadReturnsData(){
        int row = 1;
        int column = 1;
        String actual = weatherDataMunge.readWeatherData(row,column);
        String emptyString = "";
        assertNotEquals(emptyString, actual);
    }
}
