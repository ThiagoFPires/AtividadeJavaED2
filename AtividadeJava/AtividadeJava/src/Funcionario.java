public class Funcionario {
    String nome;
    int departamento;
    double qntHorasTrab;
    double horasAdicionais;
    double horaExtra;
    double insalubridade;
    double bonificacao;
    double inss;
    double impostoRenda;
    double salarioBase;
    double salarioBruto;
    double salarioLiquido;
    final double planoSaude = 20;

    // construtor para inicializar os atributos diretamente
    public Funcionario(String nome, int departamento, double qntHorasTrabalhadas, int departamentoType) {
        this.nome = nome;
        this.departamento = departamento;
        this.qntHorasTrab = qntHorasTrabalhadas;

        if (departamentoType == 1)
            departamentoAdministracao();
        else
            departamentoProducao();
    }

    // função para calcular horas adicionais
    void calculaHorasAdicionais() {
        horasAdicionais = Math.round(qntHorasTrab - 40);
    }

    // função para calcular hora extra
    void calculaHoraExtra() {
        if (departamento == 1)
            horaExtra = Math.round(22 * 2 * horasAdicionais);
        else
            horaExtra = Math.round(12 * 2 * horasAdicionais);
    }

    // função para calcular INSS
    void calculaInss() {
        inss = Math.round(salarioBruto * 0.07);
    }

    // função para calcular Imposto de Renda
    void calculaImpostoDeRenda() {
        impostoRenda = Math.round(salarioBruto * 0.12);
    }

    // função para calcular salário bruto
    void calculaSalarioBruto() {
        salarioBruto = Math.round(salarioBase + horaExtra + insalubridade + bonificacao);
    }

    // função para calcular salário líquido
    void calculaSalarioLiquido() {
        salarioLiquido = Math.round(salarioBruto - inss - impostoRenda - planoSaude);
    }

    // função para o departamento de administração
    void departamentoAdministracao() {
        if (qntHorasTrab > 40) {
            salarioBase = Math.round(22 * qntHorasTrab);
            calculaHorasAdicionais();
            calculaHoraExtra();
        } else {
            salarioBase = Math.round(qntHorasTrab * 22);
            horasAdicionais = 0;
            horaExtra = 0;
        }

        insalubridade = 0;

        if (20 < qntHorasTrab && qntHorasTrab < 30)
            bonificacao = Math.round(salarioBase * 0.03);
        else if (30 < qntHorasTrab && qntHorasTrab < 40)
            bonificacao = Math.round(salarioBase * 0.05);
        else if (qntHorasTrab >= 40)
            bonificacao = Math.round(salarioBase * 0.1);

        calculaSalarioBruto();
        calculaInss();
        calculaImpostoDeRenda();
        calculaSalarioLiquido();
    }

    // função para o departamento de produção
    void departamentoProducao() {
        if (qntHorasTrab > 40) {
            salarioBase = Math.round(12 * qntHorasTrab);
            calculaHorasAdicionais();
            calculaHoraExtra();
        } else {
            salarioBase = Math.round(qntHorasTrab * 12);
            horasAdicionais = 0;
            horaExtra = 0;
        }

        insalubridade = Math.round(salarioBase * 0.15);
        bonificacao = 0;

        calculaSalarioBruto();
        calculaInss();
        calculaImpostoDeRenda();
        calculaSalarioLiquido();
    }
}
