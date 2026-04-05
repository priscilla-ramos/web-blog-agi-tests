package com.illegra.qa.web.tests;

import com.illegra.qa.web.base.BaseTest;
import com.illegra.qa.web.pages.BlogHomePage;
import com.illegra.qa.web.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogSearchTest extends BaseTest {

    @Test
    public void cenario01_buscaComTermoValido_deveExibirResultados() {
        SearchResultsPage results = new BlogHomePage(driver)
                .open()
                .pesquisar("cartão");

        Assert.assertTrue(results.temResultados(),
                "Esperava ao menos um artigo como resultado da busca.");
    }

    @Test
    public void cenario02_buscaComTermoInexistente_deveExibirSemResultados() {
        SearchResultsPage results = new BlogHomePage(driver)
                .open()
                .pesquisar("termo-inexistente-xyz-123");

        Assert.assertTrue(results.temSemResultados() || !results.temResultados(),
                "Esperava mensagem/estado de nenhum resultado encontrado.");
    }

    @Test
    public void cenario03_buscaComTermoParcial_deveExibirResultados() {
        SearchResultsPage results = new BlogHomePage(driver)
                .open()
                .pesquisar("car");

        Assert.assertTrue(results.temResultados(),
                "Esperava encontrar resultados para termo parcial.");
    }

    @Test
    public void cenario04_novaBuscaAposBuscaAnterior_devePermitirPesquisarNovamente() {
        SearchResultsPage results = new BlogHomePage(driver)
                .open()
                .pesquisar("cartão");

        results = results.pesquisarNovamente("pagamento");

        Assert.assertTrue(results.temResultados() || results.temSemResultados(),
                "Esperava que a nova busca fosse executada corretamente (resultados ou sem resultados).");
    }
}