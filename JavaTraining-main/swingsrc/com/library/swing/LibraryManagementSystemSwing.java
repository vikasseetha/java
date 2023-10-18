package com.library.swing;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystemSwing {
    private static final String DATA_FILE = "library_data.txt";
    private List<Book> libraryData;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextField authorField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementSystemSwing librarySystem = new LibraryManagementSystemSwing();
            librarySystem.run();
        });
    }

    public void run() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"Title", "Author"}, 0);
        table = new JTable(tableModel);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    showEditDialog(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a book to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    deleteBook(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a book to delete.");
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(contentPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);

        libraryData = readLibraryDataFromFile();
        updateTable();
    }

    private void showAddDialog() {
        JFrame dialogFrame = new JFrame("Add Book");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300, 150);
        dialogFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                if (!title.isEmpty() && !author.isEmpty()) {
                    Book newBook = new Book(title, author);
                    libraryData.add(newBook);
                    saveLibraryDataToFile();
                    updateTable();
                    dialogFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialogFrame, "Please enter title and author.");
                }
            }
        });

        dialogFrame.getContentPane().add(panel, BorderLayout.CENTER);
        dialogFrame.getContentPane().add(addButton, BorderLayout.SOUTH);
        dialogFrame.setVisible(true);
    }

    private void showEditDialog(int row) {
        JFrame dialogFrame = new JFrame("Edit Book");
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setSize(300, 150);
        dialogFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField((String) tableModel.getValueAt(row, 0));
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField((String) tableModel.getValueAt(row, 1));
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                if (!title.isEmpty() && !author.isEmpty()) {
                    Book updatedBook = new Book(title, author);
                    libraryData.set(row, updatedBook);
                    saveLibraryDataToFile();
                    updateTable();
                    dialogFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialogFrame, "Please enter title and author.");
                }
            }
        });

        dialogFrame.getContentPane().add(panel, BorderLayout.CENTER);
        dialogFrame.getContentPane().add(saveButton, BorderLayout.SOUTH);
        dialogFrame.setVisible(true);
    }

    private void deleteBook(int row) {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            libraryData.remove(row);
            saveLibraryDataToFile();
            updateTable();
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Book book : libraryData) {
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor()});
        }
    }

    private List<Book> readLibraryDataFromFile() {
        List<Book> libraryData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String title = fields[0];
                    String author = fields[1];
                    libraryData.add(new Book(title, author));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraryData;
    }

    private void saveLibraryDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Book book : libraryData) {
                writer.write(book.getTitle() + "," + book.getAuthor());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
