package br.com.kantar.dao.pracas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class hora {
    
    
    public static LinkedList horario = new LinkedList();
    
    public static LinkedList horarioMetd(String inttime) throws Exception
    {
       
    Date data = new SimpleDateFormat("HH:mm").parse(inttime);
    
    Calendar x = Calendar.getInstance();
    x.setTime(data);
    
    
    for(int i=0;i<=1440;i++)
    {
    
    horario.add(new SimpleDateFormat("HH:mm").format(x.getTime()));
    x.add(Calendar.MINUTE, 1);
    

    }   
    

    
    return horario;
    }
    
    
    public static void main(String[] args) throws ParseException, Exception 
    {
    

        System.out.println(new hora().horarioMetd("02:00").get(1440));  
        
    }
    
    
    
    
}
