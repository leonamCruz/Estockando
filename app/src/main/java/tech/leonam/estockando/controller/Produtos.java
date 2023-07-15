package tech.leonam.estockando.controller;

record Produtos(
        String nomeDoProduto,
        String descricaoDoProduto,
        String dataCadastro,
        String preco,
        String codigoDeBarras,
        String imagemDoProduto
) {}
