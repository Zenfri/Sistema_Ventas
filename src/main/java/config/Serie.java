package config;

public class Serie {
    private String serie;
    
    //ya que nuestra serie contendrá una N inicial:
    //recibe una cadena, luego se evalúa su parte entera y se concatena con caracteres hacia la izquierda para darle un formato simétrico
    public String generarSiguienteSerie(String ult_serie){
        int num = Integer.parseInt(ult_serie.split("N")[1]); //nos quedamos solo con la parte entera, además,
        //la coversión de string a int omite los ceros a la izquierda
        
        num++; //el siguiente num para la nueva serie
        serie = "";
        //Acomodamos el num que recibimos para que se ajuste a 9 caracteres
        if(10000000<=num && num<100000000){
            /* 100000000 -> 010000000
               999999999 -> 099999999 */
            serie = "0"+num;
        }
        else if(1000000<=num && num<10000000){
            serie = "00"+num;
        }
        else if(100000<=num && num<1000000){
            serie = "000"+num;
        }
        else if(10000<=num && num<100000){
            serie = "0000"+num;
        }
        else if(1000<=num && num<10000){
            serie = "00000"+num;
        }
        else if(100<=num && num<1000){
            serie = "000000"+num;
        }
        else if(10<=num && num<100){
            /* 10 -> 000000010 */
            serie = "0000000"+num;
        }
        else if(0<=num && num<10){
            serie = "00000000"+num;
        }
        return "N"+serie; //Serán 10 caracteres en todas las series
    }
}
