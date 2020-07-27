package com.billhorvath.schedulinator;

import java.util.*;
import java.util.stream.*;

public class Schedulinator {

    public static void main(String[] args) {
        // Let's get out of the startup class...
        int result = Schedulinator.validate(args);
        if (result > 0){
            System.err.println("ERROR: you must supply an even number of two or more integer arguments when running " +
                    "this application. E.g., java -cp . com.billhorvath.schedulinator.Schedulinator 2 3 4 7 2 4");
            System.exit(result);
        }
        List<Integer> asIntegers = Arrays.stream(args).map(Integer::parseInt).collect(Collectors.toList());
        int size = asIntegers.size();
        int half = size / 2;
        ScheduleAnalyzer analyzer = new ScheduleAnalyzer(asIntegers.subList(0, half), asIntegers.subList(half, size));
    }


    /**
     * Returns 0 if the supplied args are valid for use in a ScheduleAnalyzer instance; 1 if args is null, has more
     * than 50 elements, is of length 0, or does not have an even number of elements; or 2 if at least one element of
     * args cannot be parsed as an integer.
     */
    static int validate(String[] args){

        if (args == null
            || args.length > 50
            || args.length == 0
            || args.length % 2 != 0){
                return 1;
        }
        for (String arg: args){
            try{
                Integer.parseInt(arg);
            }
            catch(NumberFormatException e){
                return 2;
            }
        }
        return 0;
    }
}
