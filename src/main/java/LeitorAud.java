
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import br.com.kantar.dao.pracas.hora;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class LeitorAud {

    
static List hora = new LinkedList();
static List pontos = new LinkedList();
    
 public static void leituraOfi(String intime)
 {
 
  try 
    {
       
        
        
        
   
BufferedReader bf = new BufferedReader(new FileReader(new File("Entrada/teste")));    
String linha = bf.readLine();
List dons = new LinkedList();
dons.add("0");


double pos;
String posg;
DecimalFormat df = new DecimalFormat("#.##");
df.setRoundingMode(RoundingMode.HALF_EVEN);


int contador=0;
      
while(linha!=null)
{

    
if(contador>0){




StringBuilder str = new StringBuilder(linha);
    
int n=3;
int idx = str.length()-n;
while(idx>0)
{
str.insert(idx," ");
idx = idx-n;
}


DecimalFormat dfs = new DecimalFormat("#.##");
dfs.setRoundingMode(RoundingMode.HALF_EVEN);
String[]valores = str.toString().split(" ");




hora.add(new hora().horarioMetd(intime).get(contador));
pontos.add(dfs.format(Double.parseDouble(valores[9])/10000));


JOptionPane.showMessageDialog(null,dfs.format(Double.parseDouble(valores[9])/10));

}    
    
linha = bf.readLine();
contador++;  

}  
      

//chamarComparar(hora,pontos,"teste");



    }
    catch (Exception e) 
    {
        
        JOptionPane.showMessageDialog(null, e);
        
    }
   
    
 
 
 }
    

public static void Leitor(int p1,int p2,String Praca)
{
    try 
    {
       
   
BufferedReader bf = new BufferedReader(new FileReader(new File("Entrada/teste")));    
String linha = bf.readLine();
List dons = new LinkedList();
dons.add("0");


double pos;
String posg;
DecimalFormat df = new DecimalFormat("#.##");
df.setRoundingMode(RoundingMode.HALF_EVEN);


      
while(linha!=null)
{
   
pos = Double.parseDouble(linha.substring(p1,p2))/10000; 
posg = df.format(pos);

System.out.println(posg);
linha = bf.readLine();
    

}  
        
    }
    catch (Exception e) 
    {
        
        JOptionPane.showMessageDialog(null, e);
        
    }
   
    



}



    
    public static void main(String[] args) throws Exception {
  
   
     
        leituraOfi("06:00");
    }
    
}
