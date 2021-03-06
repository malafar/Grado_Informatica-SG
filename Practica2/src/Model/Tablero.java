package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Tablero extends BranchGroup{
        ArrayList<String> matrizNaves;
        Tabla vertical,horizontal;
        int contadornaves;
        boolean perdedor;
        
    public Tablero(Color3f color,Color3f color2,String fichero) throws IOException{
         vertical=new Tabla(color,true);
         horizontal=new Tabla(color2,false);
         
         //Cargamos las naves y las introducimos en el tablero
         cargarNaves(fichero);
         contadornaves=20;
         perdedor=false;
         horizontal.añadirNaves(matrizNaves);
         
         TransformGroup translacionverti=new TransformGroup();
         Vector3f vector=new Vector3f(0.0f,13.0f,1.0f);
         Transform3D trans=new Transform3D();
         trans.setTranslation(vector);
         translacionverti.setTransform(trans);
         
         TransformGroup rotacion=new TransformGroup();
         Transform3D rotacionx=new Transform3D();
         rotacionx.rotX(Math.PI/2);
         rotacion.setTransform(rotacionx);
         
         
         TransformGroup translacion=new TransformGroup();
         vector=new Vector3f(0.0f,0.0f,13.0f);
         trans=new Transform3D();
         trans.setTranslation(vector);
         translacion.setTransform(trans);
         
         rotacion.addChild(vertical);
         translacionverti.addChild(rotacion);     
         
         translacion.addChild(horizontal);
         this.addChild(translacion);
         this.addChild(translacionverti);
         
    }
    
    /**
    *  Cargamos las naves a traves de un fichero que contiene la posicion de cada nave
    */

    private void cargarNaves(String fichero) throws FileNotFoundException, IOException{
      String cadena;
      matrizNaves=new ArrayList<String>();
      FileReader f = new FileReader(fichero);
      BufferedReader b = new BufferedReader(f);
      while((cadena = b.readLine())!=null) {
          matrizNaves.add(new String(cadena));
      }
      b.close();
      posicionarNaves();
    
    }

    /**
    *  Comprueba si la posición x,y donde se ha realizado el ataque es un acierto o 
    *  un fallo y devuelve un boolean segun el resultado del ataque(acierto/fallo)
    *  en caso de acierto disminuimos en 1 el contador de naves del jugador
    */

    public boolean posicionAtaque(int x,int y){
        boolean salida=false;
        if(matrizNaves.get(y).charAt(x)!='0'){
            salida=true;
            horizontal.setFallo(x, y);
            contadornaves--;
        }
        else{
            horizontal.setAgua(x, y);
        }
        return salida;
    }
    
    /**
    *  Comprueba si el contador de naves del jugador ha llegado a 0, si ese es el 
    *  caso el jugador habra perdido la partida
    */
    public boolean comprobarGanador() {
        if(contadornaves == 0)
            perdedor=true;
        return perdedor;
    }
    
    /**
    * Cargamos las naves en las posiciones indicadas por la matriz de naves,  
    *   Vamos recorriendo la matriz de naves y vamos detectando que nave es y su orientación
    *   para carga la nave en la posición adecuada.
    */
    public void posicionarNaves(){
        Nave nave1,nave2,nave3,nave4;
        boolean horizontal=false;
        float desviax=0,desviay=0;
        
        for(int y=0;y<matrizNaves.size();y++){
            for(int x=0;x<matrizNaves.get(y).length();x++){
                
                if(matrizNaves.get(y).charAt(x)!='0')
                    switch(matrizNaves.get(y).charAt(x)){
                        case '1':
                           nave1 = new Nave("naves\\E-TIE-I\\E-TIE-I.obj", 1,false,new Vector3f(-9f+(x*2), 1.5f, 4.0f+(y*2)));
                           //nave1 = new Nave("naves/E-TIE-I/E-TIE-I.obj", 1,false,new Vector3f(-9f+(x*2), 1.5f, 4.0f+(y*2)));
                           nave1.compile();
                           this.addChild(nave1);
                        break;
                        case '2':
                           if( y==0 || ( y-1>0 && matrizNaves.get(y-1).charAt(x)!='2')){
                                if(x+1<matrizNaves.get(y).length() && matrizNaves.get(y).charAt(x+1)=='2'){
                                    horizontal=true;
                                    desviax=1.0f;
                                    desviay=0.1f;
                                }
                                else{
                                    desviay=0.9f;
                                }
                                nave2 = new Nave("naves\\naveEspacial\\naveEspacial.obj", 2,horizontal,new Vector3f(-9f+desviax+(x*2), 1.5f, 4.0f+desviay+(y*2)));
                                //nave2 = new Nave("naves/naveEspacial/naveEspacial.obj", 2,horizontal,new Vector3f(-9f+desviax+(x*2), 1.5f, 4.0f+desviay+(y*2)));
                                nave2.compile();
                                this.addChild(nave2);
                                if(horizontal){
                                     horizontal=false;
                                     x++;
                                }
                                desviax=0;desviay=0;
                                
                           } 
                        break;
                            
                        case '3':
                            if( y==0 || ( y-1>0 && matrizNaves.get(y-1).charAt(x)!='3')){
                                if(x+1<matrizNaves.get(y).length() && matrizNaves.get(y).charAt(x+1)=='3'){
                                    horizontal=true;
                                    desviax=2.0f;
                                    desviay=0.1f;
                                }
                                else{
                                    desviay=1.9f;
                                }
                                nave3 = new Nave("naves\\FA-22_Raptor\\FA-22_Raptor.obj", 3,horizontal,new Vector3f(-9f+desviax+(x*2), 2.0f, 4.0f+desviay+(y*2)));
                                //nave3 = new Nave("naves/FA-22_Raptor/FA-22_Raptor.obj", 3,horizontal,new Vector3f(-9f+desviax+(x*2), 2.0f, 4.0f+desviay+(y*2)));
                                nave3.compile();
                                this.addChild(nave3);
                                if(horizontal){
                                     horizontal=false;
                                     x+=2;
                                }
                                desviax=0;desviay=0;
                                
                           } 
                        break;
                        case '4':
                            if( y==0 || ( y-1>0 && matrizNaves.get(y-1).charAt(x)!='4')){
                                if(x+1<matrizNaves.get(y).length() && matrizNaves.get(y).charAt(x+1)=='4'){
                                    horizontal=true;
                                    desviax=3.0f;
                                    desviay=0.1f;
                                }
                                else{
                                    desviay=2.9f;
                                }
                                nave4 = new Nave("naves\\naveEspacial\\naveEspacial.obj", 4,horizontal,new Vector3f(-9f+desviax+(x*2), 2.0f, 4.0f+desviay+(y*2)));
                                //nave4 = new Nave("naves/naveEspacial/naveEspacial.obj", 4,horizontal,new Vector3f(-9f+desviax+(x*2), 2.0f, 4.0f+desviay+(y*2)));
                                nave4.compile();
                                this.addChild(nave4);
                                if(horizontal){
                                     horizontal=false;
                                     x+=3;
                                }
                                desviax=0;desviay=0;
                                
                           }  
                        break;
                    
                    }
            
            }
        }
    
    }
    

    public char getPosValor(int x, int y){
        return  matrizNaves.get(y).charAt(x);
    }
    
    public void setPosValor(int x, int y, char valor){
        String s = matrizNaves.get(y).substring(0,x)+valor+matrizNaves.get(y).substring(x+1);
        matrizNaves.set(y,s);
        for(String i:matrizNaves){
            System.out.println(i);
        }
    }
    /**
    * Comprobaciones para saber si un barco ha sido tocado o hundido 
    * Realizamos comprobaciones en las 4 direccioen posibles desde una posicion x,y
    * pasada por parametro. En el mappa detectaremos que se ha realizado un ataque 
    * con exito cambiando su valor por un 5
    */

    public boolean comprobarEstadoNave(int x,int y){
        boolean hundido = true;
        boolean ctrlizq=true,ctrldch=true,ctrlup=true,ctrldown=true;
        char naveActual= getPosValor(x, y);
        setPosValor(x, y, '5');
        int rango = Integer.parseInt(naveActual+"") -1;
        int i = rango;
        while (hundido && i != 0) {
            for (int j = 1; j <= rango; j++, i--) {
                //Comprobación lado izquierdo
                if (ctrlizq && (x - j) >= 0) {
                    if (getPosValor(x - j, y) == naveActual) {
                        hundido = false;
                    } else if (getPosValor(x - j, y) == '0') {
                        ctrlizq = false;
                    }
                }
                //Comprobación lado derecho
                if (ctrldch && (x + j) < matrizNaves.get(y).length()) {
                    if (getPosValor(x + j, y) == naveActual) {
                        hundido = false;
                    } else if (getPosValor(x + j, y) == '0') {
                        ctrldch = false;
                    }
                }
                //Comprobación lado superior
                if (ctrlup && (y - j) >= 0) {
                    if (getPosValor(x, y - j) == naveActual) {
                        hundido = false;
                    } else if (getPosValor(x, y - j) == '0') {
                        ctrlup = false;
                    }
                }
                //Comprobación lado inferior
                if (ctrldown && (y + j) < matrizNaves.size()) {
                    if (getPosValor(x, y + j) == naveActual) {
                        hundido = false;
                    }
                    if (getPosValor(x, y + j) == '0') {
                        ctrldown = false;
                    }
                }
            }
        }        
        return hundido;
    }
}
