//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.ticket;

import java.util.List;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.ListQBFModelNumber;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.editor.JEditorKeys;
import com.openbravo.editor.JEditorString;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;

public class ProductFilterSales extends javax.swing.JPanel implements EditorCreator {
    
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    
    /** Creates new form ProductFilterSales */
    public ProductFilterSales(DataLogicSales dlSales, JEditorKeys jKeys) {
        initComponents();
        
        // El modelo de categorias
        m_sentcat = dlSales.getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();           
        
        m_jCboPriceBuy.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceBuy.addEditorKeys(jKeys);
        
        m_jCboPriceSell.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceSell.addEditorKeys(jKeys);
        
        m_jtxtCampania.addEditorKeys(jKeys);
        
        m_jtxtTalla.addEditorKeys(jKeys);
        
        m_jtxtName.addEditorKeys(jKeys);
        
        m_jtxtBarCode.addEditorKeys(jKeys);
    }
    
    public void activate() {
        
        m_jtxtBarCode.reset();
        m_jtxtBarCode.setEditModeEnum(JEditorString.MODE_123);
        m_jtxtCampania.reset();
        m_jtxtTalla.reset();
        m_jtxtName.reset();
        m_jPriceBuy.reset();
        m_jPriceSell.reset();
        m_jtxtName.activate();
        
        try {
            List catlist = m_sentcat.list();
            catlist.add(0, null);
            m_CategoryModel = new ComboBoxValModel(catlist);
            m_jCategory.setModel(m_CategoryModel);
        } catch (BasicException eD) {
            // no hay validacion
        }
    }
    
    public Object createValue() throws BasicException {
        
        Object[] afilter = new Object[12];
        
        // Nombre
        if (m_jtxtName.getText() == null || m_jtxtName.getText().equals("")) {
            afilter[0] = QBFCompareEnum.COMP_NONE;
            afilter[1] = null;
        } else {
            afilter[0] = QBFCompareEnum.COMP_RE;
            afilter[1] = "%" + m_jtxtName.getText() + "%";
        }
        
        //Campaña
        if (m_jtxtCampania.getText() == null || m_jtxtCampania.getText().equals("")) {
            afilter[2] = QBFCompareEnum.COMP_NONE;
            afilter[3] = null;
        } else {
            afilter[2] = QBFCompareEnum.COMP_EQUALS;
            afilter[3] = m_jtxtCampania.getValueInteger();
        }
        
        //Talla
        if (m_jtxtTalla.getText() == null || m_jtxtTalla.getText().equals("")) {
            afilter[4] = QBFCompareEnum.COMP_NONE;
            afilter[5] = null;
        } else {
            afilter[4] = QBFCompareEnum.COMP_RE;
            afilter[5] = "%" + m_jtxtTalla.getText() + "%";
        }
        
        // Precio de compra
        afilter[7] = m_jPriceBuy.getDoubleValue();
        afilter[6] = afilter[7] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceBuy.getSelectedItem();

        // Precio de venta
        afilter[9] = m_jPriceSell.getDoubleValue();
        afilter[8] = afilter[9] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceSell.getSelectedItem();
        
        // Categoria
        if (m_CategoryModel.getSelectedKey() == null) {
            afilter[10] = QBFCompareEnum.COMP_NONE;
            afilter[11] = null;
        } else {
            afilter[10] = QBFCompareEnum.COMP_EQUALS;
            afilter[11] = m_CategoryModel.getSelectedKey();
        }
        
        // el codigo de barras
        if (m_jtxtBarCode.getText() == null || m_jtxtBarCode.getText().equals("")) {
            afilter[12] = QBFCompareEnum.COMP_NONE;
            afilter[13] = null;
        } else{
            afilter[12] = QBFCompareEnum.COMP_RE;
            afilter[13] = "%" + m_jtxtBarCode.getText() + "%";
        }
        
        return afilter;
    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jtxtBarCode = new com.openbravo.editor.JEditorString();
        m_jtxtName = new com.openbravo.editor.JEditorString();
        m_jtxtCampania = new com.openbravo.editor.JEditorIntegerPositive();
        m_jtxtTalla = new com.openbravo.editor.JEditorString();
        m_jCategory = new javax.swing.JComboBox();
        m_jCboPriceBuy = new javax.swing.JComboBox();
        m_jPriceBuy = new com.openbravo.editor.JEditorCurrency();
        m_jCboPriceSell = new javax.swing.JComboBox();
        m_jPriceSell = new com.openbravo.editor.JEditorCurrency();

        setPreferredSize(new java.awt.Dimension(370, 170));
        setLayout(null);

        jLabel1.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 10, 110, 15);

        jLabel5.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 40, 110, 15);

        jLabel6.setText(AppLocal.getIntString("label.prodcampania")); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(20, 70, 100, 20);

        jLabel7.setText(AppLocal.getIntString("label.prodtalla")); // NOI18N
        add(jLabel7);
        jLabel7.setBounds(20, 100, 90, 15);

        jLabel2.setText(AppLocal.getIntString("label.prodcategory")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 130, 110, 15);

        jLabel4.setText(AppLocal.getIntString("label.prodpricebuy")); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(20, 160, 110, 15);

        jLabel3.setText(AppLocal.getIntString("label.prodpricesell")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 190, 110, 15);
        add(m_jtxtBarCode);
        m_jtxtBarCode.setBounds(130, 10, 290, 25);
        add(m_jtxtName);
        m_jtxtName.setBounds(130, 40, 290, 25);
        add(m_jtxtCampania);
        m_jtxtCampania.setBounds(130, 70, 290, 25);
        add(m_jtxtTalla);
        m_jtxtTalla.setBounds(130, 100, 290, 25);
        add(m_jCategory);
        m_jCategory.setBounds(130, 130, 260, 25);
        add(m_jCboPriceBuy);
        m_jCboPriceBuy.setBounds(130, 160, 150, 25);
        add(m_jPriceBuy);
        m_jPriceBuy.setBounds(290, 160, 130, 25);
        add(m_jCboPriceSell);
        m_jCboPriceSell.setBounds(130, 190, 150, 25);
        add(m_jPriceSell);
        m_jPriceSell.setBounds(290, 190, 130, 25);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JComboBox m_jCboPriceBuy;
    private javax.swing.JComboBox m_jCboPriceSell;
    private com.openbravo.editor.JEditorCurrency m_jPriceBuy;
    private com.openbravo.editor.JEditorCurrency m_jPriceSell;
    private com.openbravo.editor.JEditorString m_jtxtBarCode;
    private com.openbravo.editor.JEditorIntegerPositive m_jtxtCampania;
    private com.openbravo.editor.JEditorString m_jtxtName;
    private com.openbravo.editor.JEditorString m_jtxtTalla;
    // End of variables declaration//GEN-END:variables
    
}
