import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Lightweight styling helpers used across the Swing screens to keep the look consistent.
 */
public final class UITheme {
    public static final Color PRIMARY = new Color(25, 118, 210);
    public static final Color ACCENT = new Color(13, 71, 161);
    public static final Color BACKGROUND = new Color(245, 248, 252);
    public static final Color BORDER = new Color(224, 230, 237);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private UITheme() {}

    /**
     * Creates a primary button with consistent styling.
     */
    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFont(TEXT_FONT);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(8, 14, 8, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    /**
     * Returns a white "card" panel with padding and a subtle border.
     */
    public static JPanel createCard(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(new LineBorder(BORDER), new EmptyBorder(12, 12, 12, 12)));
        return panel;
    }

    /**
     * Creates a header bar with a title and subtitle.
     */
    public static JPanel createHeader(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY);
        header.setBorder(new EmptyBorder(14, 16, 14, 16));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(TITLE_FONT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setForeground(new Color(210, 227, 248));
        subtitleLabel.setFont(SUBTITLE_FONT);

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);
        return header;
    }

    /**
     * Produces a label using the standard text font and color.
     */
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TEXT_FONT);
        label.setForeground(new Color(55, 65, 81));
        return label;
    }

    /**
     * Produces a text field with consistent padding and font.
     */
    public static JTextField createTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(TEXT_FONT);
        field.setBorder(new CompoundBorder(new LineBorder(BORDER), new EmptyBorder(6, 8, 6, 8)));
        return field;
    }

    /**
     * Applies the standard font/background to toggle controls (checkboxes, radio buttons).
     */
    public static void styleToggle(javax.swing.AbstractButton button) {
        button.setFont(TEXT_FONT);
        button.setBackground(Color.WHITE);
    }
}
