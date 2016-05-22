package GUI;

import Model.Camara;
import Model.Picking;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO, JAVIER MARTINEZ MONTILLA, MANUEL ALBERTO LAFUENTE ARANDA
 */
public class Control extends JFrame {
    
  /**
   * Creates new form Control
   */
    boolean turno;
    ArrayList<Camara> camaras;
    int camaraActual;
    Picking pick;
    
  public Control(ArrayList<Camara> camara,Picking picar) {
    initComponents();
    // Atributos de referencia
    camaras=camara;
    pasarturno.setEnabled(false);
    turno=true;
    camaraActual=0;
    pick=picar;
    
    // Atributos de la ventana
    setTitle ("Control Window");
    setLocation (500, 520);
    
    // Se construye y se abre la ventana de visualizacion
    
    // Cuando se cierra esta ventana se llama al método encargado del cierre
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        exit (0);
      }
    });
    
    repaint();
  }

  /// El método encargado del cierre de la aplicación es único
  public void exit (int status) {
    System.exit (status);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jb_exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        camataque = new javax.swing.JButton();
        pasarturno = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaMensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jb_exit.setMnemonic('X');
        jb_exit.setText("Exit");
        jb_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_exitActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Camaras"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        camataque.setText("Camara de Ataque");
        camataque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camataqueActionPerformed(evt);
            }
        });
        jPanel2.add(camataque, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 160, 50));

        pasarturno.setText("Pasar turno");
        pasarturno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarturnoActionPerformed(evt);
            }
        });
        jPanel2.add(pasarturno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 120, 50));

        areaMensajes.setColumns(15);
        areaMensajes.setRows(5);
        areaMensajes.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensajes"));
        jScrollPane1.setViewportView(areaMensajes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_exit)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jb_exit)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Vistas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void jb_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_exitActionPerformed
    exit(0);
  }//GEN-LAST:event_jb_exitActionPerformed

    public void activarAtaque(){
        camataque.setEnabled(true);
    }
    
    public void activarCambioTurno(){
        pasarturno.setEnabled(true);
    }
  
    public void desactivarAtaque(){
        camataque.setEnabled(false);
    }
    
    public void desactivarCambioTurno(){
        pasarturno.setEnabled(false);
    }
    
    public void setAreaMensajes(String s){
        areaMensajes.setText(s);
    }
    public boolean getTurno(){
        return turno;
    }
  
    private void camataqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camataqueActionPerformed
        // Desactivamos el canvas de la vista activa y la asignamos a la vista lunas
             System.out.println("trueb2");
                camaras.get(camaraActual).removeCanvas();
                camaraActual++;
                camaras.get(camaraActual).addCanvas();
                desactivarAtaque();
                activarCambioTurno();
                pick.setCamAtaque(true);
                desactivarCambioTurno();

    }//GEN-LAST:event_camataqueActionPerformed

    private void pasarturnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarturnoActionPerformed

      camaras.get(camaraActual).removeCanvas();
      if(turno){
          camaraActual=2;
      }
      else{
          camaraActual=0;
      }
      System.out.println("CamarasActual "+camaraActual);
      camaras.get(camaraActual).addCanvas();
      turno=!turno;
      desactivarCambioTurno();
      activarAtaque();
      pick.setCamAtaque(false);
      pick.setCont(0);
      System.out.println("Cambiooos:"+pick.getCont());
    }//GEN-LAST:event_pasarturnoActionPerformed
//FIN NUEVO

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaMensajes;
    private javax.swing.JButton camataque;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_exit;
    private javax.swing.JButton pasarturno;
    // End of variables declaration//GEN-END:variables
}
