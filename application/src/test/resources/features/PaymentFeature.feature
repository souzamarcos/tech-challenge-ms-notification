# language: pt
Funcionalidade: API - Pagamentos

  Cenario: Buscar um pagamento existente pelo id
    Dado que um pagamento já foi registrado
    Quando requisitar a busca de um pagamento por id
    Entao pagamento é exibido com sucesso

  Cenario: Buscar pagamentos existente pelo order id
    Dado que um pagamento já foi registrado
    Quando requisitar a busca de um pagamento por order id
    Entao pagamentos são exibidos com sucesso

  Cenario: Receber weebhook com status de pagamento
    Dado que um pagamento já foi registrado
    Quando receber weebhook com novo status do pagamento
    Entao pagamento é exibido com sucesso com novo status