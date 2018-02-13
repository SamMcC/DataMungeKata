import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WeatherDataMunge {
    private Object[] fileLines;
    public WeatherDataMunge(String datFileLocation) {
        try {
            File inputFile = new File(datFileLocation);
            BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
            this.fileLines = fileReader.lines().toArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readWeatherData(int row, int column) {
        String outputColumn = "";
        row += 1;
        if (fileLines.length > row){
            String fullRow = (String) fileLines[row];
            String[] splitRow = fullRow.split(" +");
            outputColumn = splitRow[column];
        }

        return cleanOutputString(outputColumn);
    }

    private String cleanOutputString(String outputColumn) {
        return outputColumn.replaceAll("[^\\d.]", "");
    }

    public float calculateTemperatureSpreadForDay(int day){
        int minTemperatureColumn = 3;
        int maxTemperatureColumn = 2;
        String maxTemperatureString = readWeatherData(day,maxTemperatureColumn);
        String minTemperatureString = readWeatherData(day, minTemperatureColumn);
        float maxTemperature = Float.parseFloat(maxTemperatureString);
        float minTemperature = Float.parseFloat(minTemperatureString);

        return maxTemperature-minTemperature;
    }

    public int calculateMinimumTemperatureSpread(){
        float minimumSpread = Float.MAX_VALUE;
        int dayOfMinimumSpread = 0;
        for (int day = 1; day < 31; day++){
            float currentSpread = calculateTemperatureSpreadForDay(day);
            minimumSpread = Float.min(minimumSpread, currentSpread);
            dayOfMinimumSpread = getDayOfMinimumSpread(minimumSpread, dayOfMinimumSpread, day, currentSpread);
        }
        return dayOfMinimumSpread;
    }

    private int getDayOfMinimumSpread(float minimumSpread, int dayOfMinimumSpread, int day, float currentSpread) {
        if (minimumSpread == currentSpread){
            dayOfMinimumSpread = day;
        }
        return dayOfMinimumSpread;
    }

}
