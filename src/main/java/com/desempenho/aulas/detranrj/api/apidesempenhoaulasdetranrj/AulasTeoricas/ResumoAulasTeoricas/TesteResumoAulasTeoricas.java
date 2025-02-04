package com.desempenho.aulas.detranrj.api.apidesempenhoaulasdetranrj.AulasTeoricas.ResumoAulasTeoricas;

import java.io.IOException;
import java.util.List;

public class TesteResumoAulasTeoricas {

    public static void main(String[] args) throws IOException {

        ResumoAulasTeoricasService disciplinaController = new ResumoAulasTeoricasService();

        // alex
        String renach = "332806847";

        // mae
        // String renach = "490994679";

        // joao
        // String renach = "491165714";

        // rayssa
        // String renach = "967904870";

        List<ResumoAulasTeoricasBean> disciplinaBeans = disciplinaController
                .convertRetornoResumo(disciplinaController.requestResumoAulas(renach));

        for (int i = 0; i < disciplinaBeans.size(); i++) {
            System.out.println(disciplinaBeans.get(i).getQuantidade() + " - " + disciplinaBeans.get(i).getNome());
        }
        /*
         * Dados retornados dia 02/09/21 02:16
         * 17 - Legislação De Trânsito
         * 4 - Primeiros Socorros
         * 13 - Direção Defensiva
         * 4 - Meio Ambiente Cidadania
         * 3 - Noções De Mecânica Veicular
         * 
         */
    }

}
