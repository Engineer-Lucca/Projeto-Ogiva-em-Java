# Projeto-Ogiva-em-Java


Aplicativo de Gráfico de Ogiva Interativo em Java
Este projeto é um aplicativo desktop desenvolvido em Java para gerar gráficos de ogiva (frequência acumulada) de forma interativa, permitindo ao usuário inserir, remover e limpar dados dinamicamente. O gráfico é renderizado usando a biblioteca JavaFX.

Tecnologias Utilizadas
Java 11+: Linguagem de programação principal usada para desenvolver a aplicação.

JavaFX: Framework para criação de interfaces gráficas em Java, utilizado para construção da interface do usuário, gráficos e interação.

FXML ou Código JavaFX direto: A interface gráfica foi criada programaticamente utilizando classes da JavaFX.

Coleções Java (List e ArrayList): Para armazenamento e manipulação dos dados inseridos pelo usuário.

Classe LineChart da JavaFX: Para plotagem do gráfico de linha representando a frequência acumulada (ogiva).

Classes de Controles JavaFX: TextField, Button, ListView, Labels, VBox, HBox para entrada e visualização de dados e controles da aplicação.

Manipulação de Eventos: Tratamento de ações dos botões para adicionar, remover e limpar dados, atualizando o gráfico em tempo real.

Funcionalidades
Interface com uma tela inicial simples e botão para iniciar a aplicação.

Permite a inserção de valores numéricos pelo usuário por meio de um campo de texto.

Os dados são listados na tela e exibidos em ordem crescente.

Botões para adicionar um novo valor, remover um valor selecionado da lista e limpar todos os dados.

Atualização automática do gráfico de ogiva conforme os dados são manipulados.

Gráfico de linha que mostra a frequência acumulada percentual dos valores.

Visualização clara e intuitiva com eixos rotulados e título.

Estrutura do Código
Classe principal que estende Application da JavaFX para gerenciar a interface.

Método start para construir a tela inicial e mostrar a interface principal.

Métodos específicos para:

Mostrar a tela inicial com imagem de fundo e botão para iniciar.

Mostrar a tela principal com controle de entrada e gráfico.

Atualizar a lista de dados exibida na interface.

Atualizar o gráfico de ogiva sempre que os dados são alterados.

Utilização da classe LineChart para desenhar o gráfico, adicionando pontos que representam o percentual acumulado dos dados ordenados.

Como Executar :
Tenha o Java 11 ou superior instalado.

Configure seu ambiente para rodar aplicações JavaFX (ajuste sua IDE ou variáveis de ambiente conforme necessário).

Compile o arquivo App.java.

Execute a aplicação.

Na tela inicial, clique em "Iniciar".

Insira números no campo "Digite um número" e pressione "Adicionar".

Visualize o gráfico sendo atualizado conforme os dados são inseridos.

Use os botões "Remover Selecionado" para excluir um valor e "Limpar Tudo" para reiniciar os dados.

Possíveis Melhorias Futuras
Permitir importação/exportação dos dados via arquivos CSV.

Implementar suporte a dados não numéricos ou tratamento de erros mais robusto.

Adicionar funcionalidades para análise estatística complementar.

Melhorar o layout visual e responsividade da interface.

Internacionalização para suportar outros idiomas.

Contato
Para dúvidas, sugestões ou contribuições, abra uma issue ou faça um pull request
