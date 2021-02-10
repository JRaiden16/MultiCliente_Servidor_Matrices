package clienteservidor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;

public class frameMatriz extends javax.swing.JFrame {

    int ngeneral;
    ArrayList genLista;
    //Variables globales del formulario
    JButton[][] botones;
    
    int tamanioX = 0;
    int tamanioY = 0;
    
    Random rand = new Random();
    
    int xlayer = 0;
    int ylayer = 0;

    static final int TableroX = 620;
    static final int TableroY = 620;

    
    public frameMatriz() {
        initComponents();
    }
    public frameMatriz(int num, ArrayList vector) {
        initComponents();
        ngeneral = num;
        genLista = vector;
    }

    private void initComponents() {
        pnlMenu = new javax.swing.JPanel();
        btgenera = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlTablero = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matríz Colorida");
        setName("frameMatriz");
        setResizable(false);
        setSize(new java.awt.Dimension(620, 620));

        btgenera.setText("GENERAR");
        btgenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btgeneraActionPerformed(evt);
                RedibujarTablero();
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btgenera)
                .addGap(60, 60, 60)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btgenera)))
        );

        pnlTablero.setBackground(new java.awt.Color(255, 255, 255));
        pnlTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        pnlTablero.setToolTipText("");

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btgeneraActionPerformed(java.awt.event.ActionEvent evt) {

        botones = new JButton[ngeneral][ngeneral];
        
        pnlTablero.setLayout(new GridLayout(ngeneral, ngeneral));
        ObtenerTamanioObjetos(ngeneral, ngeneral);

        int aux = 0;
        int c1, c2;
        //float r = rand.nextFloat();
        for (c1 = 0; c1 < ngeneral; c1++) {
            for (c2 = 0; c2 < ngeneral; c2++) {
                JButton btnuevo1 = new JButton();
                btnuevo1.setSize(tamanioX, tamanioY);

                //////////////////////////////////////////
                btnuevo1.setBackground(new Color((int)(Math.random() * 0x1000000)));
                
                ///////////////////////////////////////////
                btnuevo1.setToolTipText(Integer.toString(c1) + "," + Integer.toString(c2));
                botones[c1][c2] = btnuevo1;
                botones[c1][c2].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        Click(btnuevo1);
                    }});
                pnlTablero.add(botones[c1][c2]);
                RedibujarTablero();
                aux++;
            }
        }
    }

    private void RedibujarTablero() {
        pnlTablero.validate();
        pnlTablero.repaint();
    }

    private void Click(JButton btn) {
        JOptionPane.showMessageDialog(this, "Celda: " + btn.getToolTipText());
    }

    boolean esNumerico(String valorString) {
        try {
            Integer.parseInt(valorString);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private void ObtenerTamanioObjetos(int cantX, int cantY) {
        tamanioX = TableroX / cantX;
        tamanioY = TableroY / cantY;
    }

    static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frameMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frameMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frameMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameMatriz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameMatriz().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btgenera;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlTablero;
}
