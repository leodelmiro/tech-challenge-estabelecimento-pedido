# language: pt
Funcionalidade: Pedido API

  Cenário: Iniciação de um novo Pedido com CPF
    Dado que eu tenho um CPF para Iniciar um Pedido
    Quando eu envio uma requisição para iniciar pedido com cpf
    Então o pedido com cpf deve ser criado com sucesso status 201

  Cenário: Inicação de um novo Pedido sem CPF
    Dado que eu quero Iniciar um Pedido sem CPF
    Quando eu envio uma requisição para iniciar pedido sem cpf
    Então o pedido sem cpf deve ser criado com sucesso status 201

  Cenário: Listagem de todos os pedidos
    Dado que eu busco todos os pedidos
    Quando eu envio uma requisição para listar todos os pedidos
    Então uma listagem de pedidos deve ser retornado com sucesso status 200

  Cenário: Listagem de todos os pedidos na fila
    Dado que eu busco os pedidos na fila
    Quando eu envio uma requisição para listar os pedidos na fila
    Então uma listagem de pedidos na fila deve ser retornado com sucesso status 200

  Cenário: Adição de Produto em um Pedido
    Dado que eu adiciono um produto a um pedido
    Quando eu envio uma requisição para adicionar produto
    Então o produto deve ser adicionado com sucesso status 200

  Cenário: Remoção de Produto em um Pedido
    Dado que eu removo um produto de um pedido
    Quando eu envio uma requisição para remover produto
    Então o produto deve ser removido com sucesso status 200

  Cenário: Avança Status de um Pedido
    Dado que eu avanço o status de pedido
    Quando eu envio uma requisição para avançar pedido
    Então o status do pedido deve ser avançado com sucesso status 200

  Cenário: Fechamento de pedido
    Dado que eu fecho um pedido
    Quando eu envio uma requisição para fechar pedido
    Então o pedido deve ser fechado com sucesso status 200