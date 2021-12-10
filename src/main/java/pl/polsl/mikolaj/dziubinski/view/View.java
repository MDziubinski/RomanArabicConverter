package pl.polsl.mikolaj.dziubinski.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;


/**
 *View represents and manages the user interface
 * 
 * @author Mikolaj Dziubinski
 * @version 1.4
 */
public class View {
    
    /**Main application frame */
    private JFrame frame;
    /**Tabbed pane */
    private JTabbedPane tabbedPane;
    /**First panel to store text areas */
    private JPanel panel;
    /**Second tab panel to store table */
    private JPanel panelTwo;
    /**Central panel to store combo box and button */
    private JPanel centralPanel;
    /**Button to launch conversion */
    private JButton button;
    /**Text area to pass input values */
    private JTextArea textAreaInput;
    /**Text are to pass output values */
    private JTextArea textAreaOutput;
    /**First scroll pane to store input text area*/
    private JScrollPane scrollPaneInputText; 
    /**Second scroll pane to store output text area*/
    private JScrollPane scrollPaneOutputText;
    /**Third scroll pane to hold table in second tab */
    private JScrollPane scrollPaneTable;
    /**Combo box to choose conversion mode */
    private JComboBox conversionBox;
    /**Table to store logs and date of events */
    private JTable changeLogTable;
    /**Default model to edit table elements */
    private DefaultTableModel tableModel;
    
    /**String array to store combo box options */
    private static String[] conversions = new String[] {
    "Arabic to Roman",
    "Roman to Arabic",
    "Inteligent"
    };

    
    public View() {
        frame = new JFrame();
        frame.setTitle("Roman Arabic Converter");
    }
    
    /**
     * Method that creates graphical interface
     */
    public void initializeGUI()
    {

    //First Tab
    tabbedPane = new JTabbedPane();
    panel = new JPanel();
  
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    textAreaInput = new JTextArea(20, 40);
    textAreaInput.setLineWrap(true);
    textAreaInput.selectAll();
    textAreaInput.replaceSelection("");
    scrollPaneInputText = new JScrollPane(textAreaInput);
    scrollPaneInputText.setPreferredSize(new Dimension(450, 450));
    scrollPaneInputText.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
    panel.add(scrollPaneInputText);

    button = new JButton("Convert");
    conversionBox = new JComboBox(conversions);
    conversionBox.setBorder(BorderFactory.createTitledBorder("Conversion Method"));
    
    centralPanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx=0;
    constraints.gridy=1;
    centralPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    centralPanel.add(button,constraints);
    
    constraints.gridy=0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    centralPanel.add(conversionBox,constraints);
    panel.add(centralPanel);

    textAreaOutput = new JTextArea(20, 40);
    textAreaOutput.setLineWrap(true);
    scrollPaneOutputText = new JScrollPane(textAreaOutput);
    scrollPaneOutputText.setPreferredSize(new Dimension(450, 450));
    scrollPaneOutputText.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
    panel.add(scrollPaneOutputText);
    
    //Second Tab
    panelTwo = new JPanel();
    tableModel = new DefaultTableModel();
    changeLogTable = new JTable(tableModel);
    tableModel.addColumn("Date");
    tableModel.addColumn("Logs");
    changeLogTable.setBounds(30, 40, 800,300);
    scrollPaneTable = new JScrollPane(changeLogTable);
    scrollPaneTable.setPreferredSize(new Dimension(800, 450));
    panelTwo.add(scrollPaneTable);
    
    //Setting frame
    tabbedPane.addTab("Main Tab", panel);
    tabbedPane.addTab("History", panelTwo);
    frame.add(tabbedPane);
            
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 800);
    frame.setVisible(true);
    }
    
    /**
     * Getter to pass input string from text area
     * @return text as String
     */
    public String getInputString()
    {
        return textAreaInput.getText();
    }
    
    /**
     * Button getter
     * 
     * @return reference to button
     */
    public JButton getConvertButton()
    {
        return button;
    }
    
    /**
     * Getter to pass chosen option from combo box
     * @return option chosen in combo box as string
     */
    public String getComboBoxOption()
    {
        return String.valueOf(conversionBox.getSelectedItem());
    }
    
    /**
     * Creates popup message describing that input is not correct
     */
    public void getPopupEmptyInput()
    {
        JOptionPane.showMessageDialog(null, "Input text is empty!");
    cleanOut();
    }
    
    /**
     * Creates popup message describing that input is not correct
     * 
     * @param message incorrect part of input
     */
    public void getPopupWrongArabicInput(String message)
    {
        if(message.length()< 35)
        {
            JOptionPane.showMessageDialog(null, "Please verify theses values '" + message + "' as they are not valid arabic numbers");
        }
        else
        JOptionPane.showMessageDialog(null, "Please verify values, most of them are not valid arabic numbers");
        cleanOut();
    }
        
    /**
     * Creates popup message describing that input is not correct
     * 
     * @param message incorrect part of input
     */
    public void getPopupWrongRomanInput(String message)
    {
        if(message.length()< 35)
        {
            JOptionPane.showMessageDialog(null, "Please verify theses values '" + message + "' as they are not valid Roman numerals");
        }
        else
        JOptionPane.showMessageDialog(null, "Please verify values, most of them are not valid Roman numerals");
        cleanOut();
    }
    
    /**
     * Creates popup message describing that input is not correct
     * 
     * @param message incorrect part of input
     */
    public void getPopupWrongIntelligentInput(String message)
    {
        if(message.length()< 35)
        {
            JOptionPane.showMessageDialog(null, "Please verify theses values '" + message + "' as they are not valid Roman numerals nor arabic numbers");
        }
        else
        JOptionPane.showMessageDialog(null, "Please verify values, most of them are not valid Roman numerals nor arabic numbers");
        cleanOut();
    }
       
    /**
     * Appends input array as well as output array
     * 
     * @param inputList list with input values
     * @param outputList list with converted values
     */
    public void showResultGUI(List<String> inputList, List<String> outputList)
    {
        for(int i = 0; i < inputList.size(); i++)
        {
            textAreaOutput.append(inputList.get(i)+ " -> " + outputList.get(i) + "\n");
        }
        
    }

    /**
     *  Clears the text area
     */
    public void cleanOut()
    {
        textAreaOutput.selectAll();
        textAreaOutput.replaceSelection("");
    }
    
    /**
     * Method to get current time and date
     * 
     * @return return current date and time
     */
    public String getCurrentTime()
    {
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dateTime.format(now);
    }
    
    /**
     * Updates the table with logs 
     * 
     * @param message takes message to put in logs
     */
    public void setLogTable(String message)
    {
        tableModel.addRow(new Object[]{getCurrentTime(), message});
    }
                      
}
