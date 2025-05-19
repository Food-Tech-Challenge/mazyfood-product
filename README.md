# mazyfood-product

Tech Challenge Fast Food desenvolvido no curso de Pós-Graduação em Arquitetura de Software na Pós-Tech FIAP.

---

## Sumário

1. [Sobre o Projeto](#sobre-o-projeto)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Pré-requisitos](#pré-requisitos)
4. [Como Executar Localmente](#como-executar-localmente)
5. [Documentação e Acesso](#documentação-e-acesso)
6. [Autores](#autores)

---

## Sobre o Projeto

O **MAZYFood Product** é um microsserviço desenvolvido em **Spring Boot**, seguindo o padrão de **Layered Architecture**.
Além da API em Spring Boot, o projeto utiliza o banco de dados **PostgresSQL**. 
Este projeto tem como objetivo oferecer uma base sólida para estudos e aplicação prática
de conceitos avançados em arquitetura de software com foco em microsserviços.

---

## Qualidade do Código

### Testes unitários
![Testes Unitários](./assets/testes.png)

### SonarQube
![Sonar_1](./assets/sonar.png)

### Cobertura de Testes
![Cobertura de Testes](./assets/jacoco.png)

---


## Tecnologias Utilizadas

- **Java** (Spring Boot): Framework para construção de aplicações robustas e escaláveis.
- **Docker**: Ferramenta de containerização para garantir a portabilidade e consistência do ambiente.
- **PostgresSQL**: Banco de dados SQL utilizado para persistência de dados.
- **Layered Architecture**: Padrão arquitetural utilizado em aplicações Spring Boot.
- **Helm**: Gerenciador de pacotes para Kubernetes.

---

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu ambiente:

- **Docker**: Para construir as imagens da aplicação.
- **Git**: Para clonar o repositório.
- **Kubectl**: Para gerenciar os recursos do cluster Kubernetes.
- **Helm**: Para gerenciar os pacotes Kubernetes.

---

## Como Executar Localmente

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local:

1. **Clonar o repositório**
   ```bash
   git clone git@github.com:Food-Tech-Challenge/mazyfood-product.git
   cd mazyfood-product
   ```

2. **Rodar o banco de dados com Docker Compose**
   ```bash
   docker compose up
   ```

3. **Configurar e rodar a aplicação com Minikube**
    - **Iniciar o Minikube**:
      ```bash
      minikube start
      ```

    - **Habilitar o Minikube no Docker**:
      ```bash
      eval $(minikube docker-env)
      ```

    - **Habilitar o registro de imagens no Minikube**:
      ```bash
      minikube addons enable registry
      ```

    - **Construir e enviar a imagem para o registro do Minikube**:
      ```bash
      docker image build -t localhost:5000/mazyfood-product:latest .
      docker image push localhost:5000/mazyfood-product:latest
      ```

    - **Deploy com Helm**:
      ```bash
      helm upgrade --install mazyfood-product helm/
      ```

4. **Obter o IP e porta do serviço**
    - **Encontrar o IP do Minikube**:
      ```bash
      minikube ip
      ```
    - **Encontrar a porta do NodePort do serviço**:
      ```bash
      kubectl get svc mazyfood-product-service -o wide
      ```

---

## Participantes

- **Alison Israel - RM358367**  
  *Discord*: @taykarus | E-mail: taykarus@gmail.com

- **José Matheus de Oliveira - RM358854**  
  *Discord*: @jsmatheus | E-mail: matheusoliveira.info@gmail.com

- **Victor Zaniquelli - RM358533**  
  *Discord*: @zaniquelli | E-mail: zaniquelli@outlook.com.br

- **Yan Gianini - RM358368**  
  *Discord*: @.gianini | E-mail: yangianini@gmail.com