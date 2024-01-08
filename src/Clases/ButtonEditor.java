/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author mpolo
 */
public class ButtonEditor extends DefaultCellEditor{
    protected JButton btn;
    private String lbl;
    private Boolean isClicked;
    
    public ButtonEditor(JTextField jtf) {
        super(jtf);
        btn = new JButton();
        btn.setOpaque(true);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Component component = super.getTableCellEditorComponent(table, value, isSelected, row, column); //To change body of generated methods, choose Tools | Templates.
        
        
        return component;
    }
    
    
    
    
}
