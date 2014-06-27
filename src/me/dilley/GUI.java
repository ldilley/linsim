/*
 * $Id: GUI.java 4 2011-12-13 15:37:54Z ldilley $
 * LinSim - The Linux Simulator
 * Copyright (C) 2011 Lloyd S. Dilley <lloyd@dilley.me>
 * https://github.com/ldilley/linsim
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
 * @author Lloyd S. Dilley
 */

package me.dilley;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class GUI
{
  public static void launch()
  {
    final JFrame frame=new JFrame();
    final JPanel panel=new JPanel();

    // Setup about dialog text and allow a clickable link to the website changing mouse cursors as needed
    final JEditorPane editorPane=new JEditorPane();
    editorPane.setPreferredSize(new Dimension(450, 150));
    editorPane.setEditable(false);
    editorPane.setContentType("text/html");
    editorPane.setText("<html>\n" +
                       "<body>\n" +
                       "<center>\n" +
                       "<p>\n" +
                       "<b>LinSim " + LinSim.version + " - <i>The Linux Simulator</i></b><br><br>\n" +
                       "Author: Lloyd S. Dilley<br><br>\n" +
                       "Copyright &copy; 2011\n<br><br>" +
                       "<a href=\"http://www.dilley.me/linsim/\">http://www.dilley.me/linsim/</a>\n" +
                       "</p>\n" +
                       "</center>\n" +
                       "</body>\n" +
                       "</html>\n");
    editorPane.addHyperlinkListener(new HyperlinkListener()
    {
      public void hyperlinkUpdate(final HyperlinkEvent e)
      {
        if(e.getEventType()==HyperlinkEvent.EventType.ENTERED)
        {
          EventQueue.invokeLater(new Runnable()
          {
            public void run()
            {
              SwingUtilities.getWindowAncestor(editorPane).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              editorPane.setToolTipText(e.getURL().toExternalForm());
            }
          });
        }
        else if(e.getEventType()==HyperlinkEvent.EventType.EXITED)
        {
          EventQueue.invokeLater(new Runnable()
          {
            public void run()
            {
              SwingUtilities.getWindowAncestor(editorPane).setCursor(Cursor.getDefaultCursor());
              editorPane.setToolTipText(null);
            }
          });
        }
        else if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
        {
          if(Desktop.isDesktopSupported())
          {
            try
            {
              Desktop.getDesktop().browse(e.getURL().toURI());
            }
            catch(Exception ex)
            {
              JOptionPane.showMessageDialog(null, "Unable to connect.", "LinSim Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      }
    });

    // Setup the menu
    JMenuBar menuBar=new JMenuBar();
    JMenu fileMenu=new JMenu("File");
    menuBar.add(fileMenu);
    JMenuItem exitMenuItem=new JMenuItem("Exit");
    exitMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        java.lang.System.exit(0);
      }
    });
    fileMenu.add(exitMenuItem);
    JMenu helpMenu=new JMenu("Help");
    menuBar.add(helpMenu);
    JMenuItem aboutMenuItem=new JMenuItem("About");
    aboutMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        JOptionPane.showMessageDialog(null, new JScrollPane(editorPane), "About LinSim", JOptionPane.PLAIN_MESSAGE);
      }
    });
    helpMenu.add(aboutMenuItem);

    // Set frame attributes
    Color bgColor=new Color(0, 0, 0);   // background color
    Color fgColor=new Color(0, 255, 0); // foreground/text color
    frame.setBackground(bgColor);
    frame.setJMenuBar(menuBar);
    frame.setTitle("LinSim v0.9");
    frame.setSize(700, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridBagLayout());
    frame.setResizable(false);
    Container container=frame.getContentPane();

    Font font=new Font("Verdana", Font.BOLD, 12);
    final JTextArea scrollbackBuffer=new JTextArea(40, 90);
    final JTextField commandInput=new JTextField(90);
    scrollbackBuffer.setForeground(fgColor);
    scrollbackBuffer.setEditable(false);
    scrollbackBuffer.setBackground(bgColor);
    scrollbackBuffer.setFont(font);
    scrollbackBuffer.setWrapStyleWord(true);
    scrollbackBuffer.setCaretColor(fgColor);
    scrollbackBuffer.setFocusable(false);
    scrollbackBuffer.setBorder(BorderFactory.createLineBorder(bgColor));
JScrollPane scrollPane=new JScrollPane(scrollbackBuffer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setViewportBorder(BorderFactory.createLineBorder(bgColor));
    scrollPane.setBorder(BorderFactory.createLineBorder(bgColor));
    scrollPane.setFocusable(false);
    commandInput.setForeground(fgColor);
    commandInput.setEditable(true);
    commandInput.setText("user@host$ ");
    commandInput.setBackground(bgColor);
    commandInput.setFont(font);
    //commandInput.requestFocusInWindow();
    commandInput.setCaretColor(fgColor);
    //commandInput.setCaretVisible(true);
    //commandInput.getCaret().setVisible(true);
    commandInput.setBorder(BorderFactory.createLineBorder(bgColor));
    commandInput.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event)
      {
        //commandInput.setText(Command.prompt);
        //StringTokenizer token=new StringTokenizer(commandInput.getText(), " \t");
        String commandOutput=CommandParser.parse(commandInput.getText());
        scrollbackBuffer.append(commandInput.getText() + "\n" + commandOutput);
        scrollbackBuffer.setCaretPosition(scrollbackBuffer.getDocument().getLength());
        commandInput.setText("");
        //commandInput.setText(Command.prompt);
      }
    });

        GridBagConstraints c=new GridBagConstraints();
        c.gridwidth=GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.BOTH;
        c.weightx=1.0;
        c.weighty=1.0;
        container.add(scrollPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        container.add(commandInput, c);


    //frame.add(scrollbackBuffer);
    //frame.add(commandInput);
    frame.pack();
    frame.setVisible(true);
  }
}
