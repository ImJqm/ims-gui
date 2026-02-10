import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Main extends JPanel {

    private Color bg0 = new Color(22, 22, 28);
    private Color bg1 = new Color(25, 26, 34);
    private Color bgHover = new Color(37, 41, 58);
    private Color title0 = new Color(255, 106, 116);
    private Color txt0 = new Color(159, 168, 208);
    private Color txt1 = new Color(185, 195, 243);
    private Color txtHover = new Color(111, 152, 246);
    private Color txtClicked = new Color(164, 131, 226);
    private Color border = new Color(37, 41, 58);

    private JTextField name, pid, price, quantity;

    private Inventory inv = new Inventory();

    private JButton update;

    private JTable table;

    private String[] columnNames = {
        "Name",
        "Product ID",
        "Price",
        "Quantity",
    };

    private JButton searchButton = new JButton("\udb85\udfa9 Search");
    private JButton removeButton = new JButton("\uf00d Remove");

    private JTextField query;
    private JTextField removeQuery;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Main() {
        super.setLayout(new BorderLayout());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Inventory Management Systtem");
        frame.setPreferredSize(new Dimension(500, 750));

        frame.getContentPane().add(this);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bg0);
        topPanel.setPreferredSize(new Dimension(500, 250));
        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);

        JLabel topLabel = new JLabel(
            "Add/Update Product",
            SwingConstants.CENTER
        );
        topLabel.setFont(new Font("JetBrainsMono Nerd Font", Font.BOLD, 30));
        topLabel.setForeground(title0);
        topPanel.add(topLabel, BorderLayout.PAGE_START);

        int leftOffset = 10;
        JPanel topLabels = new JPanel();
        topLabels.setLayout(new BoxLayout(topLabels, BoxLayout.Y_AXIS));
        topLabels.setBackground(bg0);
        topLabels.setBorder(new EmptyBorder(0, leftOffset, 0, 0));

        topLabels.add(Box.createVerticalStrut(11));
        topLabels.add(smallText("Product Name:"));
        topLabels.add(Box.createVerticalStrut(22));
        topLabels.add(smallText("Product ID:"));
        topLabels.add(Box.createVerticalStrut(22));
        topLabels.add(smallText("Price:"));
        topLabels.add(Box.createVerticalStrut(22));
        topLabels.add(smallText("Quantity:"));

        topPanel.add(topLabels, BorderLayout.LINE_START);

        JPanel topTextFeilds = new JPanel();
        topTextFeilds.setLayout(new BoxLayout(topTextFeilds, BoxLayout.Y_AXIS));
        topTextFeilds.setBackground(bg0);

        name = new JTextField("Cool Name", 20);
        name.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));
        name.setBackground(bg0);
        name.setForeground(txt1);
        name.setBorder(BorderFactory.createLineBorder(border, 1));
        topTextFeilds.add(name);

        pid = new JTextField("0001", 20);
        pid.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));
        pid.setBackground(bg0);
        pid.setForeground(txt1);
        pid.setBorder(BorderFactory.createLineBorder(border, 1));
        topTextFeilds.add(pid);

        price = new JTextField("10.99", 20);
        price.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));
        price.setBackground(bg0);
        price.setForeground(txt1);
        price.setBorder(BorderFactory.createLineBorder(border, 1));
        topTextFeilds.add(price);

        quantity = new JTextField("100", 20);
        quantity.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));
        quantity.setBackground(bg0);
        quantity.setForeground(txt1);
        quantity.setBorder(BorderFactory.createLineBorder(border, 1));
        topTextFeilds.add(quantity);

        topPanel.add(topTextFeilds, BorderLayout.LINE_END);

        update = new JButton("\uf0fe Add/Update Product");
        update.setPreferredSize(new Dimension(100, 30));
        update.setBackground(bg0);
        update.setOpaque(false);
        update.setBorderPainted(false);
        update.setFocusPainted(false);
        update.setForeground(txt1);
        update.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));

        topPanel.add(update, BorderLayout.PAGE_END);

        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setPreferredSize(new Dimension(500, 300));
        dataPanel.setBackground(Color.RED);
        frame.getContentPane().add(dataPanel, BorderLayout.CENTER);

        //Using JTable instead of Text Feild for better formating

        DefaultTableModel dataModel = new DefaultTableModel(
            updateData(inv.getProducts()),
            columnNames
        );

        table = new JTable(dataModel);
        table.setBackground(bg1);
        table.setForeground(txt1);
        table.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(bgHover);
        table.setSelectionForeground(txtHover);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        table.getTableHeader().setBackground(bg0);
        table.getTableHeader().setForeground(title0);
        table
            .getTableHeader()
            .setFont(new Font("JetBrainsMono Nerd Font", Font.BOLD, 16));
        table.getTableHeader().setBorder(new EmptyBorder(0, 0, 0, 0));

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.getViewport().setBackground(bg1);
        tableScroll.setBorder(BorderFactory.createLineBorder(border, 1));

        dataPanel.add(tableScroll);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(bg0);
        buttonPanel.setPreferredSize(new Dimension(500, 100));
        frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        JButton showAll = new JButton("\udb81\ude16 Show All");
        showAll.setPreferredSize(new Dimension(100, 30));
        showAll.setBackground(bg0);
        showAll.setOpaque(false);
        showAll.setBorderPainted(false);
        showAll.setFocusPainted(false);
        showAll.setForeground(txt1);
        showAll.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));

        buttonPanel.add(showAll);

        JButton idSearch = new JButton("\uf002 Search by ID");
        idSearch.setPreferredSize(new Dimension(100, 30));
        idSearch.setBackground(bg0);
        idSearch.setOpaque(false);
        idSearch.setBorderPainted(false);
        idSearch.setFocusPainted(false);
        idSearch.setForeground(txt1);
        idSearch.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));

        buttonPanel.add(idSearch);

        JButton remove = new JButton("\uf146 Remove");
        remove.setPreferredSize(new Dimension(100, 30));
        remove.setBackground(bg0);
        remove.setOpaque(false);
        remove.setBorderPainted(false);
        remove.setFocusPainted(false);
        remove.setForeground(txt1);
        remove.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));

        buttonPanel.add(remove);

        showAll.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setModel(
                        new DefaultTableModel(
                            updateData(inv.getProducts()),
                            columnNames
                        )
                    );
                }
            }
        );

        remove.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog removeDialog = new JDialog(
                        SwingUtilities.getWindowAncestor(Main.this),
                        "Remove by ID",
                        Dialog.ModalityType.APPLICATION_MODAL
                    );
                    removeDialog.setUndecorated(false);
                    removeDialog.setSize(300, 200);

                    JPanel panel = new JPanel(new FlowLayout());
                    panel.setBackground(bg0);

                    JLabel message = new JLabel("Enter Product ID:");
                    message.setForeground(txt1);
                    message.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );

                    removeQuery = new JTextField(20);
                    removeQuery.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );
                    removeQuery.setBackground(bg0);
                    removeQuery.setForeground(txt1);
                    removeQuery.setBorder(
                        BorderFactory.createLineBorder(border, 1)
                    );

                    removeButton.setPreferredSize(new Dimension(200, 30));
                    removeButton.setBackground(bg0);
                    removeButton.setOpaque(false);
                    removeButton.setBorderPainted(false);
                    removeButton.setFocusPainted(false);
                    removeButton.setForeground(txt1);
                    removeButton.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );

                    panel.add(message);
                    panel.add(removeQuery);
                    panel.add(removeButton);

                    removeDialog.setContentPane(panel);
                    removeDialog.setVisible(true);
                }
            }
        );

        idSearch.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog searchDialog = new JDialog(
                        SwingUtilities.getWindowAncestor(Main.this),
                        "ID Search",
                        Dialog.ModalityType.APPLICATION_MODAL
                    );
                    searchDialog.setUndecorated(false);
                    searchDialog.setSize(300, 200);

                    JPanel panel = new JPanel(new FlowLayout());
                    panel.setBackground(bg0);

                    JLabel message = new JLabel("Enter Product ID:");
                    message.setForeground(txt1);
                    message.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );

                    query = new JTextField(20);
                    query.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );
                    query.setBackground(bg0);
                    query.setForeground(txt1);
                    query.setBorder(BorderFactory.createLineBorder(border, 1));

                    searchButton.setPreferredSize(new Dimension(200, 30));
                    searchButton.setBackground(bg0);
                    searchButton.setOpaque(false);
                    searchButton.setBorderPainted(false);
                    searchButton.setFocusPainted(false);
                    searchButton.setForeground(txt1);
                    searchButton.setFont(
                        new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                    );

                    panel.add(message);
                    panel.add(query);
                    panel.add(searchButton);

                    searchDialog.setContentPane(panel);
                    searchDialog.setVisible(true);
                }
            }
        );

        searchButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setModel(
                        new DefaultTableModel(
                            updateData(inv.search(query.getText())),
                            columnNames
                        )
                    );
                }
            }
        );

        removeButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inv.removeProduct(removeQuery.getText());
                    table.setModel(
                        new DefaultTableModel(
                            updateData(inv.getProducts()),
                            columnNames
                        )
                    );
                }
            }
        );

        update.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        boolean exists = inv.search(pid.getText()).size() != 0 ;
                        if (exists) {
                          if (Double.valueOf(price.getText()) < 0 || (inv.search(pid.getText()).get(0).getQuantity()+Integer.valueOf(quantity.getText())<0)) {
                              throw new NumberFormatException("Negative Input");
                          }
                        } else {
                          if (Double.valueOf(price.getText()) < 0 || Integer.valueOf(quantity.getText()) < 0) {
                              throw new NumberFormatException("Negative Input");
                          }
                        }
                        inv.addProduct(
                            new Product(
                                name.getText(),
                                pid.getText(),
                                Double.valueOf(price.getText()),
                                Integer.valueOf(quantity.getText())
                            )
                        );

                        table.setModel(
                            new DefaultTableModel(
                                updateData(inv.getProducts()),
                                columnNames
                            )
                        );
                    } catch (NumberFormatException err) {
                        JDialog errorDialog = new JDialog(
                            SwingUtilities.getWindowAncestor(Main.this),
                            "Error",
                            Dialog.ModalityType.APPLICATION_MODAL
                        );
                        errorDialog.setUndecorated(false);
                        errorDialog.setSize(200, 200);

                        JPanel panel = new JPanel();
                        panel.setBackground(bg0);

                        JLabel message = new JLabel("Invalid Input");
                        message.setForeground(txt1);
                        message.setFont(
                            new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                        );

                        panel.add(message);
                        errorDialog.setContentPane(panel);
                        errorDialog.setVisible(true);

                        //im using a dialogue instead of Joption pane here becuase I dont like the window decorations and I want visual consistancy
                        // Here is what the code would be like if I used a message pain:
                        /*
                        JPanel panel = new JPanel();
                        panel.setBackground(bg0);

                        JLabel message = new JLabel("Invalid Input");
                        message.setForeground(txt1);
                        message.setFont(
                            new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16)
                        );

                        panel.add(message);
                        JOptionPane.showMessageDialog(
                            null,
                            panel,
                            "Error",
                            JOptionPane.PLAIN_MESSAGE
                        );
                        */
                    }

                    inv.printProducts(0, inv.getAmount());
                    System.out.println(inv.getProducts());
                }
            }
        );
        update.addChangeListener(e -> {
            ButtonModel model = update.getModel();
            if (model.isPressed()) {
                update.setBackground(bgHover);
                update.setForeground(txtClicked);
            } else {
                update.setBackground(bg0);
                update.setForeground(txt1);
            }
        });

        showAll.addChangeListener(e -> {
            ButtonModel model = showAll.getModel();
            if (model.isPressed()) {
                showAll.setBackground(bgHover);
                showAll.setForeground(txtClicked);
            } else {
                showAll.setBackground(bg0);
                showAll.setForeground(txt1);
            }
        });

        idSearch.addChangeListener(e -> {
            ButtonModel model = idSearch.getModel();
            if (model.isPressed()) {
                idSearch.setBackground(bgHover);
                idSearch.setForeground(txtClicked);
            } else {
                idSearch.setBackground(bg0);
                idSearch.setForeground(txt1);
            }
        });

        remove.addChangeListener(e -> {
            ButtonModel model = remove.getModel();
            if (model.isPressed()) {
                remove.setBackground(bgHover);
                remove.setForeground(txtClicked);
            } else {
                remove.setBackground(bg0);
                remove.setForeground(txt1);
            }
        });

        searchButton.addChangeListener(e -> {
            ButtonModel model = searchButton.getModel();
            if (model.isPressed()) {
                searchButton.setBackground(bgHover);
                searchButton.setForeground(txtClicked);
            } else {
                searchButton.setBackground(bg0);
                searchButton.setForeground(txt1);
            }
        });

        frame.pack();
        frame.setVisible(true);
        topLabels.setBorder(new EmptyBorder(0, leftOffset, 0, 0));
    }

    private JLabel smallText(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16));
        label.setForeground(txt0);
        return label;
    }

    private Object[][] updateData(ArrayList<Product> arr) {
        Object[][] data = new Object[arr.size()][4];
        for (int i = 0; i < arr.size(); i++) {
            Object[] row = {
                arr.get(i).getName(),
                arr.get(i).getProductID(),
                arr.get(i).getPrice(),
                arr.get(i).getQuantity(),
            };
            data[i] = row;
        }
        return data;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    Main main = new Main();
                    main.createAndShowGUI();
                }
            }
        );
    }
}
