package backend.utils;

/**
 * Created by Felix on 21.10.2017.
 */
public class StringCutter {
    String input;

    public StringCutter(String input){
        this.input = input;
    }

    /**
     * cuts the string
     *
     * @param begin after this string will be extracted
     * @param end before this string will be extracted
     * @return cutted string
     */
    public String cut(String begin, String end){
        if (input.indexOf(begin) == -1) return null;
        int startIndex = input.indexOf(begin) + begin.length();
        int endIndex = input.indexOf(end, startIndex);
        return input.substring(startIndex, endIndex);
    }
}
