package com.illegra.qa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;

    private static final String BASE_URL = "https://blogdoagi.com.br/";

    // WordPress normalmente renderiza posts em <article>
    private final By cardsResultados = By.cssSelector("article");

    // Títulos comuns (varia por tema, então buscamos em mais de um lugar)
    private final By possiveisTitulos = By.cssSelector("#primary h1, main h1, h1");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int quantidadeCards() {
        List<WebElement> cards = driver.findElements(cardsResultados);
        return cards.size();
    }

    public boolean temResultados() {
        return quantidadeCards() > 0;
    }

    public String lerTextoTitulo() {
        List<WebElement> titulos = driver.findElements(possiveisTitulos);
        return titulos.isEmpty() ? "" : titulos.get(0).getText();
    }

    /**
     * Estado "sem resultados" mais robusto:
     * - tenta identificar via título e, se necessário, via texto da página.
     */
    public boolean temSemResultados() {
        String titulo = lerTextoTitulo().toLowerCase();
        String body = driver.findElement(By.tagName("body")).getText().toLowerCase();

        return titulo.contains("nenhum")
                || body.contains("nenhum resultado")
                || body.contains("no results")
                || body.contains("nothing found");
    }

    /**
     * Nova busca estável via URL.
     */
    public SearchResultsPage pesquisarNovamente(String termo) {
        String termoEncode = URLEncoder.encode(termo, StandardCharsets.UTF_8);
        driver.get(BASE_URL + "?s=" + termoEncode);
        return this;
    }
}