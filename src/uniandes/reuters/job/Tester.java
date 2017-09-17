/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes.reuters.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asistente
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String txt = "<DATE>26-FEB-1987 15:21:16.13</DATE>";
        Pattern p = Pattern.compile("(<DATE>)");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        
        Calendar fch = Calendar.getInstance();
        Matcher m = p.matcher(txt);

        if(m.find()) {
            try {
                txt = txt.substring(6, (!txt.contains("</DATE>"))? txt.length() : txt.indexOf("</DATE>"));
                System.out.println(txt);
                System.out.println("final: " + sdf.parse(txt).toString());
            } catch (ParseException ex) {
                Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }    
}
