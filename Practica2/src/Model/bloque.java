package Model;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Bloque extends BranchGroup {
    
    private int posx,posy;
    private Box box;
    private ColoringAttributes color;
    private Appearance ap;
    private boolean activado;
    
    public Bloque(Vector3f vector,boolean pickeable,int x,int y){
        posx=x;
        posy=y;
        
        ap=new Appearance();
        ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0.0f));
        color=new ColoringAttributes();
        ap.setColoringAttributes(color);
        
        box = new Box(0.7f, 0.3f, 0.7f,
            Primitive.ENABLE_APPEARANCE_MODIFY, ap);
        if(pickeable){
            box.setUserData(this);
            box.setPickable(true);
        }
        else{
            box.setPickable(false);
        }
        TransformGroup translacion = new TransformGroup();
        Transform3D transformtranslation = new Transform3D();
        transformtranslation.setTranslation(vector);
        translacion.setTransform(transformtranslation); 

        
       translacion.addChild(box);
       this.addChild(translacion);
    }
    public void activarFallo(){
        Color3f nuevocolor=new Color3f(0.6f,0.0f,0.0f);
        //ColoringAttributes
        ap.setColoringAttributes(new ColoringAttributes(nuevocolor,ColoringAttributes.SHADE_FLAT));
        activado=true;
    }
    
    public void activarAcierto(){
        Color3f nuevocolor=new Color3f(0.2f,0.6f,0.2f);
        //ColoringAttributes
        ap.setColoringAttributes(new ColoringAttributes(nuevocolor,ColoringAttributes.SHADE_FLAT));
        activado=true;
    }
    
    public void activarAgua(){
        Color3f nuevocolor=new Color3f(0.1f,0.1f,0.1f);
        //ColoringAttributes
        ap.setColoringAttributes(new ColoringAttributes(nuevocolor,ColoringAttributes.SHADE_FLAT));
        activado=true;
    }
    
    public int getX(){
        return posx;
    }
    public int getY(){
        return posy;
    }
    public boolean getActivado(){
        return activado;
    }
}
