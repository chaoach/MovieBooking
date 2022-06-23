
import java.util.Scanner;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class test {
    static public void main(String[]args){
        Scanner in = new Scanner(System.in);
        String Stime = in.next();
        
        
    }
    
    private boolean testTime(String Stime){
        String[] arrOfStr = Stime.split(":");
        //System.out.println(arrOfStr.length);
        if(arrOfStr.length>2){
            System.out.println("error in time");
            return false;
        }
        int hour, min;
        hour = Integer.valueOf(arrOfStr[0]);
        min = Integer.valueOf(arrOfStr[1]);
        if(hour<00 || hour>23){
            System.out.println("hour not exact");
            return false;
        }
        if(min<00 || min>59){
            System.out.println("min is not exact");
            return false;
        }
        
        return true;
    }
    
}
