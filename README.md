# SDdeCoracao

#Utilizando o Publisher:

A camada Publisher deste projeto é uma rotina geradora threads que criam publishers que enviam ao banco de dados (middleware) mensagens com identificadores(tags) para classificar o conteúdo.

Para utilizar o executável do publisher por  linha de comando, basta escrever o seguinte modelo.
java -jar "endereco\do\projeto\SDdeCoracao-master\Publisher\dist\Publisher.jar" -ip ipdesejado

ipdesejado é um parâmtro que referencia o destino da mensagem. No caso de nenhum ip ser passado como parâmetro, o padrão é utilizar o ip do publisher para testes.