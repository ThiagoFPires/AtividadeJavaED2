import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static DefaultTableModel model;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Por gentileza, forneça as informações de cinco funcionários, adicionando um de cada vez, e depois pressione o botão CALCULAR.");

        // para criação da janela principal
        JFrame frame = new JFrame("Cadastro de Funcionários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLayout(new BorderLayout());

        // painel para a entrada de dados
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        frame.add(inputPanel, BorderLayout.SOUTH);

        // para criação das labels e campos de texto para entrada de dados
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel departamentoLabel = new JLabel("Departamento (1 - Administrativo, 2 - Produção):");
        JTextField departamentoField = new JTextField();

        JLabel horasLabel = new JLabel("Quantidade de horas trabalhadas:");
        JTextField horasField = new JTextField();

        // botão para calcular
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton calcularButton = new JButton("Calcular");
        buttonPanel.add(calcularButton, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        calcularButton.setPreferredSize(new Dimension(75, 30));
        calcularButton.setFocusPainted(false);

        // adicionar componentes ao painel de entrada
        inputPanel.add(nomeLabel);
        inputPanel.add(nomeField);
        inputPanel.add(departamentoLabel);
        inputPanel.add(departamentoField);
        inputPanel.add(horasLabel);
        inputPanel.add(horasField);
        inputPanel.add(buttonPanel);

        // criação da tabela para exibir os resultados
        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("NOME");
        model.addColumn("SALÁRIO BASE");
        model.addColumn("HORA EXTRA");
        model.addColumn("INSALUBRIDADE");
        model.addColumn("BONIFICAÇÃO");
        model.addColumn("INSS");
        model.addColumn("IMPOSTO DE RENDA");
        model.addColumn("PLANO DE SAÚDE");
        model.addColumn("SALÁRIO LÍQUIDO");

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // faz a ação do botão de calcular
        calcularButton.addActionListener(new ActionListener() {
            int funcionariosAdicionados = 0;

            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int departamento = Integer.parseInt(departamentoField.getText());
                double horas = Double.parseDouble(horasField.getText());

                Funcionario funcionario;

                if (departamento == 1)
                    funcionario = new Funcionario(nome, departamento, horas, 1);
                else
                    funcionario = new Funcionario(nome, departamento, horas, 2);

                adicionarFuncionarioNaTabela(funcionario);

                funcionariosAdicionados++;
                if (funcionariosAdicionados == 5) {
                    calcularButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Todos os 5 funcionários foram cadastrados.");
                }
            }
        });

        // exibe a janela
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // função para adicionar um funcionário à tabela
    static void adicionarFuncionarioNaTabela(Funcionario funcionario) {
        model.addRow(new Object[]{
                funcionario.nome,
                funcionario.salarioBase,
                funcionario.horaExtra,
                funcionario.insalubridade,
                funcionario.bonificacao,
                funcionario.inss,
                funcionario.impostoRenda,
                funcionario.planoSaude,
                funcionario.salarioLiquido
        });
    }
}
