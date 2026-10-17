import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegScript
{
    public static boolean startup = true;
    public static String name = "";
    public static int game = 1;
    public static long chips = 4500;
    public static int betColorNum = 0;
    public static int winningNumber = 36;
    public static String winningColor = "";
    public static long betAmount = 500;
    public static String betOn = null;
    private double degrees = 0;
    private ImageIcon myIcon;
    private static final double SPEED = 2.0;

    public static String betColor(Color inside)
    {
        if (inside == Color.RED)
        {
            betColorNum = 1;
            betOn = "red";
            return "red";
        }
        else if (inside == Color.BLACK)
        {
            betColorNum = 2;
            betOn = "black";
            return "black";
        }
        else
        {
            betColorNum = 3;
            betOn = "green";
            return "green";
        }
    }

    public static void RegGame()
    {
        JFrame gameMenu = new JFrame("Fun Game :)");
        JLabel stats = new JLabel("<html>Insert name:</html>");
        JLabel controls = new JLabel("");
        JLabel wheel = new JLabel(new ImageIcon(RegScript.class.getResource("pix.png")));
        JLabel innerWheel = new JLabel(new ImageIcon(RegScript.class.getResource("outer-wheel.png")));
        JLabel betStats = new JLabel("Press [R, G, or B] to make a bet");
        wheel.setHorizontalAlignment(SwingConstants.RIGHT);
        wheel.setVerticalAlignment(SwingConstants.TOP);
        wheel.setVisible(true);
        innerWheel.setHorizontalAlignment(wheel.getHorizontalAlignment());
        innerWheel.setVerticalAlignment(wheel.getVerticalAlignment());
        innerWheel.setVisible(true);
        innerWheel.setOpaque(false);
        betStats.setVerticalAlignment(SwingConstants.BOTTOM);
        betStats.setHorizontalAlignment(SwingConstants.RIGHT);
        betStats.setForeground(Color.WHITE);
        controls.setForeground(Color.WHITE);
        controls.setHorizontalAlignment(SwingConstants.LEFT);
        controls.setVerticalAlignment(SwingConstants.BOTTOM);
        stats.setForeground(Color.WHITE);
        stats.setHorizontalAlignment(SwingConstants.LEFT);
        stats.setVerticalAlignment(SwingConstants.TOP);
        gameMenu.setSize(750, 500);
        gameMenu.setResizable(false);
        gameMenu.getContentPane().setBackground(new Color(0, 161, 78));
        gameMenu.add(stats);
        gameMenu.setVisible(true);
        gameMenu.add(controls);
        gameMenu.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent pressed)
            {
                if (startup)
                {
                    if (pressed.getKeyCode() != KeyEvent.VK_ENTER && !Character.isISOControl(pressed.getKeyChar()))
                    {
                        if (pressed.getKeyCode() != KeyEvent.VK_SHIFT)
                        {
                            gameMenu.add(wheel);
                            name += pressed.getKeyChar();
                            stats.setText("<html>Insert name:<br>" + name + "</html>");
                        }
                    }
                    else if (pressed.getKeyCode() == KeyEvent.VK_ENTER && name.length() > 0)
                    {
                        startup = false;
                        stats.setText("<html><header>Name: " + name + "</header><br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "</html>");
                        controls.setText("<html>[F] Place Bet&emsp;[R] Bet Red<br>[C] Bet -100&emsp;&nbsp;[G] Bet Green<br>[V] Bet +100&emsp;[B] Bet Black</html>");
                        wheel.setIcon(new ImageIcon(RegScript.class.getResource("inner-wheel.png")));
                        gameMenu.add(innerWheel);
                    }
                    else if (pressed.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    {
                        name = name.substring(0, name.length() - 1);
                        stats.setText("<html>Insert name:<br>" + name + "</html>");
                    }
                }
                else
                {
                    gameMenu.add(betStats);
                    System.out.println(betStats.getSize());

                    if (pressed.getKeyCode() == KeyEvent.VK_R)
                    {
                        betColor(Color.RED);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_G)
                    {
                        betColor(Color.GREEN);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_B)
                    {
                        betColor(Color.BLACK);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_C && betAmount > 100)
                    {
                        betAmount -= 100;
                        chips += 100;
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                        stats.setText("<html><header>Name: " + name + "</header><br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "</html>");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_V && chips > 0)
                    {
                        betAmount += 100;
                        chips -= 100;
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                        stats.setText("<html><header>Name: " + name + "</header><br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "</html>");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_F && betOn != null)
                    {
                        winningNumber = (int) (Math.random() * 36);

                        if ((winningNumber % 2 == 1 && betColorNum == 2 && winningNumber != 0) || (winningNumber % 2 == 0 && betColorNum == 1 && winningNumber != 0) || (winningNumber == 0 && betColorNum == 3))
                            chips += betAmount * 2;

                        if (winningNumber % 2 == 1)
                            winningColor = "black";
                        else if (winningNumber == 0)
                            winningColor = "green";
                        else
                            winningColor = "red";

                        game += 1;
                        stats.setText("<html><header>Name: " + name + "</header><br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "</html>");

                        if (chips == 0)
                        {
                            System.out.print("\n\nYou fucken SUCK");
                            System.exit(130);
                        }

                        if (chips < betAmount)
                        {
                            chips -= 100;
                            betAmount = 100;
                            betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                        }
                        else
                            chips -= betAmount;
                    }
                }
            }
        });
    }
}