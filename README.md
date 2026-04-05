# Teste Técnico QA – Web | Blog do Agi

Automação de testes Web para a funcionalidade de **busca de artigos** do Blog do Agi.

---

## 🎯 Objetivo

Validar o comportamento da busca de artigos considerando cenários relevantes para o usuário final, garantindo estabilidade e aderência ao funcionamento real do site.

---

## ✅ Cenários Automatizados

1. **Busca com termo válido**  
   → Deve retornar artigos relacionados

2. **Busca com termo inexistente**  
   → Deve exibir estado ou mensagem de “nenhum resultado encontrado”

3. **Busca com termo parcial**  
   → Deve retornar artigos relacionados

4. **Nova busca após uma busca anterior**  
   → Deve permitir pesquisar novamente e atualizar os resultados

---

## 🛠 Tecnologias Utilizadas

- Java 11
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- IntelliJ IDEA Community Edition

---

## 📁 Estrutura do Projeto

```text
src/test/java
 └── com.illegra.qa.web
     ├── base       → BaseTest (setup/teardown do driver)
     ├── pages      → Page Objects (BlogHomePage, SearchResultsPage)
     └── tests      → Casos de teste (BlogSearchTest)

src/test/resources
 └── testng.xml     → Suite de execução do TestNG
```

## ▶️ Como Executar os Testes

### Via IntelliJ IDEA

1. Abrir o projeto no IntelliJ IDEA
2. Acessar: `View → Tool Windows → Maven`
3. Executar: `Lifecycle → test`

### Via linha de comando

Caso o Maven esteja configurado no PATH:

```bash
mvn test
```

### Execução em modo headless (opcional)

A execução em modo headless é recomendada para ambientes de CI/CD ou quando não é necessário abrir o navegador graficamente.

```bash
mvn test -Dheadless=true
```

> **Observação sobre execução via linha de comando**
> Para executar os testes utilizando o comando `mvn test`, é necessário que o **Maven esteja instalado** na máquina.
>
> Alternativamente, os testes podem ser executados diretamente pelo **IntelliJ IDEA**, que possui suporte nativo ao Maven, permitindo a execução do projeto sem a necessidade de instalação prévia da ferramenta.

## 📊 Relatórios

Após a execução dos testes, os relatórios padrão do **Maven Surefire** são gerados automaticamente no diretório:

```text
target/surefire-reports/
```
Nesse diretório é possível encontrar:

- Relatórios em formato `.xml` e `.txt`
- Status de cada cenário executado
- Tempo de execução dos testes
- Detalhes de falhas, caso ocorram

Esses relatórios podem ser utilizados tanto para análise local quanto para integração com pipelines de **CI/CD**, já que o formato gerado pelo Maven Surefire é amplamente suportado por ferramentas de automação.

## 🧠 Decisão Técnica Importante

O Blog do Agi é desenvolvido em **WordPress utilizando o tema Astra**.  
A funcionalidade de busca do site é realizada por meio de navegação via **parâmetro de URL (`?s=termo`)**, e o campo de pesquisa pode não estar presente ou visível no DOM inicial da página.

Durante a análise técnica, foi identificado que o ícone da lupa é representado por um elemento **SVG**, sem atributos estáveis para interação direta via Selenium, e que a busca efetivamente ocorre por redirecionamento de URL.

Para garantir **estabilidade**, **simplicidade** e evitar **flakiness** causada por elementos dinâmicos de layout (como SVGs e inputs que não são renderizados inicialmente), a automação executa a busca navegando diretamente para a URL correspondente ao termo pesquisado.

Essa abordagem reflete o comportamento real da aplicação e é uma prática comum em automações web profissionais para aplicações WordPress.

# 🚀 Integração Contínua (CI)

Este projeto possui um pipeline de **Integração Contínua** configurado com **GitHub Actions**, responsável por executar automaticamente os testes Selenium em múltiplos sistemas operacionais (**Linux, Windows e macOS**).

A pipeline é executada a cada push ou pull request na branch `main` e:
- Configura automaticamente o Java 11
- Executa os testes via Maven em modo headless
- Garante que o projeto seja executável de forma independente do sistema operacional


> Os testes também podem ser executados automaticamente via GitHub Actions,
> tanto de forma automática (push/pull request) quanto manual através da aba Actions.
