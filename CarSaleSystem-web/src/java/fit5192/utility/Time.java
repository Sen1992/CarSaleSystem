/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 王森
 */
public class Time {
    public static String getDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
