////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

////////////////////////////////////////
// Weather Manager
////////////////////////////////////////

public class WeatherManager {

    private static final String WEATHER_API_KEY = "8769765458a7cb847e0881b00aa7adf8";

    public WeatherManager(){
    }

    public static String getWeatherForLatLon(String lat, String lon){
        try {

            //Get weather from Yahoo API
            String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=imperial&APPID="+WEATHER_API_KEY;
            String weatherResult = getHTML(weatherUrl);

            Pattern rTemp = Pattern.compile("\"temp\":[+-]?([0-9]*[.])?[0-9]+,");
            Pattern rCond = Pattern.compile("\"main\":\"[A-Za-z]*\",");
            Matcher mTemp = rTemp.matcher(weatherResult);
            Matcher mCond = rCond.matcher(weatherResult);

            String weatherTemperature = "No Temp";
            String weatherCondition = "No Cond";

            if (mTemp.find()) {
                weatherTemperature = mTemp.group(0);
                //System.out.println(weatherTemperature);
                weatherTemperature = weatherTemperature.replace("\"temp\":","");
                weatherTemperature = weatherTemperature.replace(",","");
            }
            if (mCond.find()){
                weatherCondition = mCond.group(0);
                //System.out.println(weatherCondition);
                weatherCondition = weatherCondition.replace("\"main\":\"","");
                weatherCondition = weatherCondition.replace("\",","");
            }

            return weatherCondition+ ", "+weatherTemperature+"F ";
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        String Latitude = "42.332";
        String Longitude = "-71.0202";
        System.out.println(getWeatherForLatLon(Latitude,Longitude));

    }
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}

////////////////////////////////////////
// End of Module
////////////////////////////////////////