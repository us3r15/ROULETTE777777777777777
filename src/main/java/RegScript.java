import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegScript
{
    private static boolean startup = true;
    private static String name = "";
    private static int game = 1;
    private static long chips = 4500;
    private static int betColorNum = 0;
    private static int winningNumber;
    private static String winningColor = "";
    private static long betAmount = 500;
    private static String betOn = null;
    private static int doubleDigit = 0;

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
        gameMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(RegScript.class.getResource("pixil-frame-0.png")));
        JLabel stats = new JLabel("<html>Insert name:</html>");
        JLabel controls = new JLabel("");
        JLabel wheel = new JLabel("");
        JLabel betStats = new JLabel("Press [R, G, or B] to make a bet");
        wheel.setHorizontalAlignment(SwingConstants.RIGHT);
        wheel.setVerticalAlignment(SwingConstants.TOP);
        wheel.setForeground(Color.WHITE);
        wheel.setVisible(true);
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
                        stats.setText("<html>Name: " + name + "<br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "</html>");
                        wheel.setText("<html><h3><span style='color:red'>#</span> |2|4|6|8|10|12|14|16|18|20|22|24|26|28|30|32|34|&emsp;&emsp;&ensp;[&nbsp;<span style='color:green'>G</span>&nbsp;]<br><span style='color:black'>#</span> |1|3|5|7|9|11|13|15|17|19|21|23|25|27|29|31|33|35|&emsp;|&nbsp;0&nbsp;|</h3></html>");
                        controls.setText("<html>[F] Place Bet&emsp;[R] Bet Red<br>[C] Bet -100&emsp;&nbsp;[G] Bet Green<br>[V] Bet +100&emsp;[B] Bet Black</html>");
                    }
                    else if (pressed.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    {
                        name = name.substring(0, name.length() - 1);
                        stats.setText("<html>Insert name:<br>" + name + "</html>");
                    }
                }
                else
                {
                    if (pressed.getKeyCode() == KeyEvent.VK_R)
                    {
                        gameMenu.add(betStats);
                        betColor(Color.RED);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_G)
                    {
                        gameMenu.add(betStats);
                        betColor(Color.GREEN);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_B)
                    {
                        gameMenu.add(betStats);
                        betColor(Color.BLACK);
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_C && betAmount > 100)
                    {
                        betAmount -= 100;
                        chips += 100;
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                        stats.setText("<html>Name: " + name + "<br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "<br>Last number: " + winningNumber + "</html>");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_V && chips > 0)
                    {
                        betAmount += 100;
                        chips -= 100;
                        betStats.setText("Bet " + betAmount + " on " + betOn + " ");
                        stats.setText("<html>Name: " + name + "<br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "<br>Last number: " + winningNumber + "</html>");
                    }

                    if (pressed.getKeyCode() == KeyEvent.VK_F && betOn != null)
                    {
                        winningNumber = (int) (Math.random() * 36);

                        if (winningNumber > 9)
                            doubleDigit = 1;
                        else
                            doubleDigit = 0;

                        if (wheel.getText().contains("X"))
                            wheel.setText(wheel.getText().substring(0, wheel.getText().indexOf("X")) + wheel.getText().substring(wheel.getText().indexOf("X") + 1));

                        if ((winningNumber % 2 == 1 && betColorNum == 2) || (winningNumber % 2 == 0 && betColorNum == 1 && winningNumber != 0) || (winningNumber == 0 && betColorNum == 3))
                            chips += betAmount * 2;

                        wheel.setText(wheel.getText().substring(0, wheel.getText().indexOf(winningNumber + "|") + 1 + doubleDigit) + "X" + wheel.getText().substring(wheel.getText().indexOf(winningNumber + "|") + 1 + doubleDigit));
                        System.out.println(winningNumber);

                        if (winningNumber % 2 == 1)
                        {
                            winningColor = "black";
                        }
                        else if (winningNumber == 0)
                        {
                            winningColor = "green";
                        }
                        else
                        {
                            winningColor = "red";
                        }

                        game += 1;
                        stats.setText("<html>Name: " + name + "<br>Round " + game + "<br>Chips: " + chips + "<br>Gain: " + (chips + betAmount - 5000) + "<br>Last color: " + winningColor + "<br>Last number: " + winningNumber + "</html>");

                        if (chips == 0)
                        {
                            System.out.print("\n\nYou flippity-dippin' SUCK");
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