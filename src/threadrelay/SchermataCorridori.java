package threadrelay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class SchermataCorridori extends JFrame {

    private static final int NUMERO_CORRIDORI = 4;

    private final JButton bottoneAvvia = new JButton("AVVIA SIMULAZIONE");
    private final JButton bottonePausa = new JButton("PAUSA");
    private final JButton bottoneRiprendi = new JButton("RIPRENDI");
    private final JButton bottoneStopDefinitivo = new JButton("STOP DEFINITIVO");
    private final JComboBox<String> selettoreVelocita = new JComboBox<>(new String[] {"Veloci", "Lenti"});

    private final JProgressBar[] barreCorridori = new JProgressBar[NUMERO_CORRIDORI];
    private final JLabel[] etichetteStato = new JLabel[NUMERO_CORRIDORI];

    public SchermataCorridori() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Simulazione Staffetta Atletica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(980, 620));

        JPanel root = new JPanel(new BorderLayout(0, 16));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.setBackground(new Color(245, 247, 250));
        setContentPane(root);

        root.add(creaHeader(), BorderLayout.NORTH);
        root.add(creaCentro(), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel creaHeader() {
        JPanel header = new JPanel(new BorderLayout(0, 8));
        header.setOpaque(false);

        JLabel titolo = new JLabel("Simulazione Staffetta 4x100");
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titolo.setForeground(new Color(15, 23, 42));

        JLabel descrizione = new JLabel(
                "Il runner successivo parte quando il precedente raggiunge quota 90");
        descrizione.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descrizione.setForeground(new Color(71, 85, 105));

        header.add(titolo, BorderLayout.NORTH);
        header.add(descrizione, BorderLayout.SOUTH);
        return header;
    }

    private JPanel creaCentro() {
        JPanel centro = new JPanel(new BorderLayout(12, 12));
        centro.setOpaque(false);
        centro.add(creaPannelloControlli(), BorderLayout.NORTH);
        centro.add(creaPannelloCorsie(), BorderLayout.CENTER);
        return centro;
    }

    private JPanel creaPannelloControlli() {
        JPanel pannelloControlli = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        pannelloControlli.setOpaque(false);
        pannelloControlli.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                "Controlli simulazione"));

        JLabel etichettaVelocita = new JLabel("Velocita corridori:");
        etichettaVelocita.setFont(new Font("Segoe UI", Font.BOLD, 13));
        selettoreVelocita.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        selettoreVelocita.setPreferredSize(new Dimension(130, 28));

        configuraBottone(bottoneAvvia, new Color(37, 99, 235));
        configuraBottone(bottonePausa, new Color(249, 115, 22));
        configuraBottone(bottoneRiprendi, new Color(5, 150, 105));
        configuraBottone(bottoneStopDefinitivo, new Color(220, 38, 38));

        pannelloControlli.add(etichettaVelocita);
        pannelloControlli.add(selettoreVelocita);
        pannelloControlli.add(bottoneAvvia);
        pannelloControlli.add(bottonePausa);
        pannelloControlli.add(bottoneRiprendi);
        pannelloControlli.add(bottoneStopDefinitivo);
        return pannelloControlli;
    }

    private JPanel creaPannelloCorsie() {
        JPanel pannelloCorsie = new JPanel(new GridLayout(NUMERO_CORRIDORI, 1, 0, 10));
        pannelloCorsie.setOpaque(false);

        for (int i = 0; i < NUMERO_CORRIDORI; i++) {
            JPanel corsia = new JPanel(new BorderLayout(10, 8));
            corsia.setBackground(Color.WHITE);
            corsia.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(203, 213, 225)),
                    BorderFactory.createEmptyBorder(12, 14, 12, 14)));

            JLabel nomeCorridore = new JLabel("Corridore " + (i + 1));
            nomeCorridore.setFont(new Font("Segoe UI", Font.BOLD, 16));
            nomeCorridore.setForeground(new Color(30, 41, 59));

            JLabel stato = new JLabel(i == 0 ? "PRONTO" : "IN ATTESA TESTIMONE");
            stato.setFont(new Font("Segoe UI", Font.BOLD, 12));
            stato.setHorizontalAlignment(SwingConstants.RIGHT);
            stato.setForeground(i == 0 ? new Color(22, 163, 74) : new Color(51, 65, 85));
            etichetteStato[i] = stato;

            JPanel rigaAlto = new JPanel(new BorderLayout());
            rigaAlto.setOpaque(false);
            rigaAlto.add(nomeCorridore, BorderLayout.WEST);
            rigaAlto.add(stato, BorderLayout.EAST);

            JProgressBar barra = new JProgressBar(0, 100);
            barra.setValue(0);
            barra.setStringPainted(true);
            barra.setString("0%");
            barra.setFont(new Font("Segoe UI", Font.BOLD, 12));
            barra.setForeground(new Color(14, 116, 144));
            barra.setBackground(new Color(241, 245, 249));
            barreCorridori[i] = barra;

            corsia.add(rigaAlto, BorderLayout.NORTH);
            corsia.add(barra, BorderLayout.CENTER);
            pannelloCorsie.add(corsia);
        }

        return pannelloCorsie;
    }

    private void configuraBottone(JButton bottone, Color colore) {
        bottone.setBackground(colore);
        bottone.setForeground(Color.WHITE);
        bottone.setFont(new Font("Segoe UI", Font.BOLD, 12));
        bottone.setFocusPainted(false);
        bottone.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
    }

    public JButton getBottoneAvvia() {
        return bottoneAvvia;
    }

    public JButton getBottonePausa() {
        return bottonePausa;
    }

    public JButton getBottoneRiprendi() {
        return bottoneRiprendi;
    }

    public JButton getBottoneStopDefinitivo() {
        return bottoneStopDefinitivo;
    }

    public JComboBox<String> getSelettoreVelocita() {
        return selettoreVelocita;
    }

    public JProgressBar[] getBarreCorridori() {
        return barreCorridori;
    }

    public JLabel[] getEtichetteStato() {
        return etichetteStato;
    }
}
