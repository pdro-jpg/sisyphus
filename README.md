# Sísifo 🪨

> Seu tempo livre não precisa ser perfeito.

## 💭 Sobre o projeto

Conversando com um amigo, uma frase me chamou muito a atenção:

> “Ter um dia de folga é a pior coisa do mundo!”

Pensando bem, eu concordo.

Por que eu não tenho o que fazer? Por que eu simplesmente gosto de trabalhar?

O buraco é mais em baixo e afeta muito mais pessoas do que parece.

Temos vários passatempos, mas dedicar tanto tempo tentando escolher a melhor atividade pode acabar transformando o lazer em frustração — e em *doomscrolling*.

> “Tenho tempo livre, mas não sei o que fazer.”

A famosa **Paralisia da Decisão**.

"A Paralisia da Decisão, definida como a incapacidade de tomar decisões devido a muitas opções ou incerteza, é um sintoma frequentemente negligenciado do Transtorno de Déficit de Atenção / Hiperatividade (TDAH). Indivíduos com TDAH lutam frequentemente com disfunção executiva, dificultando a priorização de tarefas, avaliando escolhas e tomando decisões oportunas."

Estudo de referência: https://bit.ly/4zoWBP9

Daí surgiu a ideia do projeto: diminuir essa barreira inicial que enfrentamos às vezes para começar ou continuar alguma atividade.

**Sísifo** é um projeto em desenvolvimento que busca ajudar pessoas que têm dificuldade para decidir como aproveitar o tempo livre.

O nome faz referência ao mito de Sísifo e à busca repetitiva por um “dia perfeito”. A proposta é incentivar pequenas escolhas e valorizar o tempo dedicado às atividades de que gostamos.

## Funcionalidades atuais:

A versão atual funciona no console e permite:

- Cadastrar vários hobbies durante a execução.
- Impedir o cadastro de nomes vazios.
- Listar os hobbies cadastrados.
- Remover hobbies pelo número exibido na lista.
- Acessar cadastro, listagem, remoção e sorteio por um menu, ou sair com a opção `0`.
- Sortear uma atividade.
- Repetir o sorteio pelo menu.
- Exibir o total de sorteios da execução ao sair.
- Salvar os hobbies em arquivo e recuperá-los na próxima execução.
- Tratar opções inválidas e impedir sorteios quando a lista está vazia.

##  Como usar

O menu reaparece após cada ação:

| Opção | Ação |
| --- | --- |
| `1` | Cadastrar um hobby. |
| `2` | Listar os hobbies com seus números. |
| `3` | Remover um hobby pelo número da lista. |
| `4` | Sortear uma atividade entre os hobbies cadastrados. |
| `0` | Sair da aplicação. |

No cadastro e na remoção, pressione Enter sem digitar nada para voltar ao menu.

##  Salvamento dos hobbies

Os hobbies são salvos automaticamente em `hobbies.txt` a cada cadastro ou remoção
e carregados ao iniciar a aplicação. O arquivo usa UTF-8, com um hobby por linha,
e fica no diretório de onde o programa é executado (diretório de trabalho da IDE).
Execute a aplicação a partir da mesma pasta para carregar a mesma lista.

Na primeira execução, a lista começa vazia e o arquivo é criado no primeiro cadastro.
O arquivo é ignorado pelo Git para manter os hobbies pessoais fora do repositório.
O total de sorteios continua sendo contado apenas durante a execução atual.

Se o carregamento falhar, a aplicação exibe uma mensagem e encerra. Se o salvamento
falhar, a alteração não é aplicada e o menu continua disponível.

##  Organização do código

- `src/Main.java`: interação com o usuário, menu, leitura das entradas e exibição das mensagens.
- `src/HobbyManager.java`: cadastro, listagem, remoção, sorteio, contagem de sorteios e leitura e gravação do arquivo de hobbies.

##  Como executar:

Com o JDK instalado, execute na raiz do projeto:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

No IntelliJ IDEA, execute o método `main` da classe `Main` e confira o diretório de trabalho na configuração de execução.

##  Funcionalidades planejadas

- [ ] Organizar hobbies por categorias e subopções, como livros e jogos.
- [ ] Criar um timer de compromisso de 15 minutos.
- [ ] Exibir lembretes que ajudem a reconhecer o tempo aproveitado.
- [ ] Celebrar pequenas conquistas sem transformar o lazer em cobrança.
- [ ] Desenvolver uma interface web com uma roleta de decisão.

Essas funcionalidades serão construídas gradualmente.

## 🛠️ Tecnologias

- **Java**
- **Git e GitHub**

## 📚 Aprendizado

Este projeto está sendo desenvolvido passo a passo como parte dos meus estudos de Java, aplicando conceitos como:

- Variáveis e condicionais.
- Laços de repetição.
- Listas com `ArrayList`.
- Entrada de dados com `Scanner`.
- Sorteio de valores com `Random`.
- Separação de responsabilidades entre classes.
- Encapsulamento dos dados dos hobbies.
- Leitura e gravação de arquivos com `Files` e `Path`.
- Tratamento de exceções e validação das entradas do menu.

## 📌 Status

**Em desenvolvimento — versão de console com menu e salvamento dos hobbies em arquivo.**

---

E, pelo amor de Deus, vai pra lá com essa ideia de se tornar a sua “melhor versão” todos os dias.
