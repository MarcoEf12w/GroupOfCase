/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mpolo
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{
    
    public ButtonRenderer(){
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //Component com = getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        //JButton cellButton = new JButton("...");
        setText("...");
       setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        Dimension d = new Dimension();
        d.height = 18;
        d.width = 18;
       setSize(d);
       if ( ! isSelected && row % 2 ==0  ){
           setBackground(Color.WHITE);
       }else{
           setBackground(table.getBackground());
       }
       
        return this;
    }
    
    
}
