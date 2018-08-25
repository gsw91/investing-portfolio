package com.invest.Gui.frames;

import com.invest.Gui.tables.QuotationsTable;
import com.invest.Gui.tables.StatisticsTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuotationsFrame {

    private JFrame quotationsFrame;

    public JFrame createStatisticsFrame(boolean visibility) {
        quotationsFrame = new JFrame("Current quotations");
        quotationsFrame.setSize(800, 600);
        quotationsFrame.setLocation(300,200);

        QuotationsTable quotationsTable = new QuotationsTable();
        JTable table = quotationsTable.showTable();
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JButton close = new JButton("Close");
        close.addActionListener(new CloseButtonActionListener());

        quotationsFrame.getContentPane().add(scrollPane);
        quotationsFrame.getContentPane().add(BorderLayout.SOUTH, close);

        quotationsFrame.setVisible(visibility);

        return quotationsFrame;
    }

    class CloseButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            quotationsFrame.dispose();
        }
    }

}
