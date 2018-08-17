package com.invest.Gui.tables;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserTable extends AbstractTableModel {

    private String[] columnNames = { "Name", "Quantity", "Buy", "Now", "Value", "Change", "Result"};
    private Object[][] data;

    public JTable showTable() {
        List<UserData> list = new ArrayList<>();
        list.add(new UserData("Krezus", 1000L, BigDecimal.valueOf(3.69), BigDecimal.valueOf(1.87)));
        list.add(new UserData("Orlen", 300L, BigDecimal.valueOf(82.56), BigDecimal.valueOf(94.56)));
        list.add(new UserData("Cognor", 2500L, BigDecimal.valueOf(1.69), BigDecimal.valueOf(2.09)));

        setData(list);
        return new JTable(data, columnNames);
    }

    private void setData(List<UserData> userDataList) {
        data = new Object[userDataList.size()][7];

        for (int i=0; i<userDataList.size(); i++) {
            data[i][0] = userDataList.get(i).getName();
            data[i][1] = userDataList.get(i).getQuantity();
            data[i][2] = userDataList.get(i).getBuy();
            data[i][3] = userDataList.get(i).getNow();
            data[i][4] = userDataList.get(i).getValue();
            data[i][5] = userDataList.get(i).getChange();
            data[i][6] = userDataList.get(i).getResult();
        }
    }


    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

}
