package neo.form;

//import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/*
 * The editor button that brings up the dialog.
 */
//public class TablePopupEditor extends AbstractCellEditor
public class TablePopupEditor extends DefaultCellEditor
    implements TableCellEditor
{
    private PopupDialog popup;
    private String currentText = "";
    private JButton editorComponent;

    public TablePopupEditor()
    {
        super(new JTextField());

        setClickCountToStart(2);

        //  Use a JButton as the editor component

        editorComponent = new JButton();
        editorComponent.setBackground(Color.white);
        editorComponent.setBorderPainted(false);
        editorComponent.setContentAreaFilled( false );

        // Make sure focus goes back to the table when the dialog is closed
        editorComponent.setFocusable( false );

        //  Set up the dialog where we do the actual editing

        popup = new PopupDialog();
    }

    public Object getCellEditorValue()
    {
        return currentText;
    }

    public Component getTableCellEditorComponent(
        JTable table, Object value, boolean isSelected, int row, int column)
    {

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                popup.setText( currentText );
                popup.setLocationRelativeTo( editorComponent );
                Point p = editorComponent.getLocationOnScreen();
                popup.setLocation(p.x, p.y + editorComponent.getSize().height);
                popup.show();
                fireEditingStopped();
            }
        });        
        currentText = value==null?"":value.toString();
        editorComponent.setText( currentText );
        return editorComponent;
    }

    /*
    *   Simple dialog containing the actual editing component
    */
    class PopupDialog extends JDialog implements ActionListener
    {
        private JTextArea textArea;

        public PopupDialog()
        {
            super((Frame)null, "Change Description", true);

            textArea = new JTextArea(5, 20);
            textArea.setLineWrap( true );
            textArea.setWrapStyleWord( true );
            KeyStroke keyStroke = KeyStroke.getKeyStroke("ENTER");
            textArea.getInputMap().put(keyStroke, "none");
            JScrollPane scrollPane = new JScrollPane( textArea );
            getContentPane().add( scrollPane );

            JButton cancel = new JButton("Cancel");
            cancel.addActionListener( this );
            JButton ok = new JButton("Ok");
            ok.setPreferredSize( cancel.getPreferredSize() );
            ok.addActionListener( this );

            JPanel buttons = new JPanel();
            buttons.add( ok );
            buttons.add( cancel );
            getContentPane().add(buttons, BorderLayout.SOUTH);
            pack();

            getRootPane().setDefaultButton( ok );
        }

        public void setText(String text)
        {
            textArea.setText( text );
        }

        /*
        *   Save the changed text before hiding the popup
        */
        public void actionPerformed(ActionEvent e)
        {
            if ("Ok".equals( e.getActionCommand() ) )
            {
                currentText = textArea.getText();
            }

            textArea.requestFocusInWindow();
            setVisible( false );
        }
    }

    private static void createAndShowUI()
    {
        String[] columnNames = {"Item", "Description"};
        Object[][] data =
        {
            {"Item 1", "Description of Item 1"},
            {"Item 2", "Description of Item 2"},
            {"Item 3", "Description of Item 3"}
        };

        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        // Use the popup editor on the second column

        TablePopupEditor popupEditor = new TablePopupEditor();
        table.getColumnModel().getColumn(1).setCellEditor( popupEditor );

        JFrame frame = new JFrame("Popup Editor Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JTextField(), BorderLayout.NORTH);
        frame.add( scrollPane );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowUI();
            }
        });
    }

}