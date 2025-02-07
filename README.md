
# 🚗 API Desempenho de Aulas da Auto Escola no Detran RJ
_Consulta de aulas e provas de autoescola._

<br>

🚨 **Importante: Transparência e Segurança** 🚨

Todos os dados fornecidos pela nossa API são **obtidos de forma totalmente pública** e consultados diretamente no site oficial do Detran RJ: [detran.rj.gov.br](https://detran.rj.gov.br).

⚠️ **Não temos acesso direto ao banco de dados do Detran**. Nosso processo consiste unicamente em exportar os dados disponíveis no site e repassá-los para você através da API.

🔒 **Nada é armazenado**: Não guardamos nenhum tipo de informação. Garantimos a privacidade e segurança dos dados.

<br>

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Swagger 3**
- **Requisições Http2 Assíncronas**
- **Requisições CURL**
- **Docker**
- **Render**
- **Trello**

<br>

## 🌐 Hospedagem

- **API**: [Acessar API](https://api-desempenho-aulas-detranrj.onrender.com/)

<br>

## 📋 Metodologia de Desenvolvimento

Utilizamos metodologias ágeis para o desenvolvimento desta API, com foco no **Kanban**. Acompanhe o progresso do projeto no nosso quadro do Trello: [Trello Board](https://trello.com/b/x0ljXeUp/desempenho-aulas-auto-escola-detran-rj).

<br>

## 🔧 Funcionalidades

- 📍 Consulta das **Aulas Teóricas**.
- 📍 Consulta das **Aulas Práticas**.
- 📍 Situação das **Provas**.
- 📑 **Documentação da API** com Swagger.

<br>

## 📚 Documentação da API

A documentação completa da API está disponível via Swagger: [Documentação Swagger](https://api-desempenho-aulas-detranrj.onrender.com/swagger-ui/index.html).


## 🛠️ Executar o Projeto

Para rodar a API localmente, use o seguinte comando:

```bash
    mvn spring-boot:run
```

## 🚢 Subir para Docker Hub

1. Realize o login no Docker:

```bash
    docker login
```

2. Construa a imagem Docker:

```bash
    docker build -t api-desempenho-aulas-detranrj:latest .
```

3. Marque a imagem com a tag desejada:

```bash
    docker tag api-desempenho-aulas-detranrj:latest seu-usuario/api-desempenho-aulas-detranrj:latest
```

4. Faça o push para o Docker Hub:

```bash
    docker push seu-usuario/api-desempenho-aulas-detranrj:latest
```

## 🌍 Deploy no Render

1. Acesse o painel do Render: [Dashboard Render](https://dashboard.render.com/web/srv-cuh95lhu0jms7380bf2g).
2. Clique em **Manual Deploy** → **Deploy latest reference**.


## 🤝 Contribuição

Se você deseja contribuir com este projeto, sinta-se à vontade para abrir um **pull request** ou **relatar um problema**.



## 💖 Faça uma Doação

Se você gostou do projeto e gostaria de contribuir, você pode fazer uma doação através do seguinte QR Code:

<p align="center">
  <img width="300" height="auto" src="https://raw.githubusercontent.com/AlexSouzaSilvax/desempenho-aulas-dentranrj/refs/heads/dev/assets/qrcode-pix.jpg" alt="QR Code de Doação" />
</p>

```
00020126760014br.gov.bcb.pix0136a942ad44-c3f1-4694-b427-5561f0baea9b0214MUITO OBRIGADO5204000053039865802BR5919Alex Souza Da Silva6009Sao Paulo62280524DoacaoProjetosOpenSource6304CCA3
```

## 📩 Contato

Se tiver dúvidas ou sugestões, entre em contato pelo e-mail: [alexsouzasilvax@gmail.com](mailto:alexsouzasilvax@gmail.com).

<br>


_Este projeto foi criado para atender às pessoas que estão em processo de tirar CNH no estado do Rio de Janeiro e precisam consultar quais aulas foram 'COMPUTADAS' no sistema do [Detran RJ](https://detran.rj.gov.br)._
