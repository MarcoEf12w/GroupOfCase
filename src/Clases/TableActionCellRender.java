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
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mpolo
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = 
         super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        JButton cellButton = new JButton("...");
       cellButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       cellButton.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        Dimension d = new Dimension();
        d.height = 18;
        d.width = 18;
       cellButton.setSize(d);
       if ( ! isSelected && row % 2 ==0  ){
           cellButton.setBackground(Color.WHITE);
       }else{
           cellButton.setBackground(com.getBackground());
       }
       
        return cellButton;
    }
    
    
    
}
