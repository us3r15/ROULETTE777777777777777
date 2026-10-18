import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main
{
    public static String typed = "";
    public static boolean starting = false;
    public static boolean started = false;
    public static String sting1 = "<header>RouletteOS</header><br>Commands:<br>start or exit<br><br><br><br><br><br><br><br><br><br>[player@RoulOS ~]# ";
    public static String sting2 = "Starting!<br>Spicy mode?:<br>spicy mode will gamble your OS, <br>so losing the game means losing your data.<br><br><br><br><br><br><br>Type yes or no:<br>[player@RoulOS ~]# ";

    public static void main(String[] args)
    {
        boolean startMenu = true;

        JFrame menu = new JFrame("777");
        JLabel startText = new JLabel("<html>" + sting1 + "</html>");
        startText.setHorizontalAlignment(SwingConstants.LEFT);
        startText.setVerticalAlignment(SwingConstants.TOP);
        startText.setForeground(Color.WHITE);
        menu.add(startText);
        menu.setSize(250, 250);
        menu.setResizable(false);
        menu.getContentPane().setBackground(Color.BLACK);
        menu.setVisible(true);

        if(startMenu && !starting)
        {
            menu.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent check)
                {
                    System.out.println("\nKeyCode: " + check.getKeyCode());
                    System.out.println("KeyChar: " + check.getKeyChar());

                    if (!Character.isISOControl(check.getKeyChar()) && check.getKeyCode() != 8 && check.getKeyCode() != 10 && typed.length() < 5 && !started)
                    {
                        typed += check.getKeyChar();
                        if (!starting)
                        {
                            startText.setText("<html>" + sting1 + typed + "</html>");
                        }
                        else
                        {
                            startText.setText("<html>" + sting2 + typed + "</html>");
                        }
                    }
                    else if (check.getKeyCode() == KeyEvent.VK_BACK_SPACE && !started)
                    {
                        if (typed.length() >= 1)
                        {
                            typed = typed.substring(0, typed.length() - 1);
                        }

                        if (!starting)
                        {
                            startText.setText("<html>" + sting1 + typed + "</html>");
                        }
                        else
                        {
                            startText.setText("<html>" + sting2 + typed + "</html>");
                        }
                    }
                    else if (check.getKeyCode() == KeyEvent.VK_ENTER && !started)
                    {
                        System.out.println(typed);
                        System.out.println(typed.equals("start"));
                        if (typed.equals("start"))
                        {
                            starting = true;
                            startText.setText("<html>" + sting2 + "</html>");
                        }
                        else if (typed.equals("exit"))
                        {
                            System.exit(0);
                        }
                        if (!starting)
                        {
                            typed = "";
                            startText.setText("<html>" + sting1 + typed + "</html>");
                        }
                        else
                        {
                            if (typed.equals("yes"))
                            {
                                menu.setVisible(false);
                                //run SpicyFile
                                started = true;
                            }
                            else if (typed.equals("no"))
                            {
                                menu.setVisible(false);
                                RegScript.RegGame();
                                started = true;
                            }
                            typed = "";
                            startText.setText("<html>" + sting2 + typed + "</html>");
                        }
                    }
                }
            });
        }
    }
}