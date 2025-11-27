# 🛡️ SecureTask Manager

> Um sistema Fullstack de gerenciamento de tarefas com foco explícito em Segurança da Aplicação (AppSec), implementando as melhores práticas de defesa em profundidade.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green)
![Security](https://img.shields.io/badge/Focus-AppSec-red)

## 🏗️ Arquitetura do Projeto

O projeto é dividido em dois módulos principais:
- **Backend:** API RESTful robusta construída com Java e Spring Boot.
- **Frontend:** (Em desenvolvimento) Interface reativa construída com Vue.js.

## 🔒 Implementações de Segurança (AppSec)

Este projeto vai além do funcional, focando em mitigar as principais vulnerabilidades da OWASP Top 10:

### 1. Autenticação e Gestão de Sessão
- **JWT (JSON Web Token):** Implementação manual e transparente de tokens assinados com algoritmo **HMAC256**.
- **Stateless:** A API não mantém estado de sessão no servidor, eliminando vetores de ataque como *Session Fixation*.
- **Senha Forte:** Utilização do algoritmo **BCrypt** para hashing de senhas. Senhas nunca são salvas em texto plano.

### 2. Controle de Acesso (Authorization)
- **RBAC (Role-Based Access Control):** Diferenciação estrita entre perfis `USER` e `ADMIN`.
- **Method Security:** Proteção em nível de endpoint, garantindo que rotas administrativas não sejam acessadas por usuários comuns.

### 3. Configuração Segura
- **Variáveis de Ambiente:** Segredos (como a chave de assinatura do token) são injetados via `application.properties` e não hardcoded, permitindo gestão segura via variáveis de ambiente em produção.
- **Tratamento de Erros:** Exceções de segurança (como `SilentExit`) são tratadas para não vazar stack traces sensíveis.

## 🚀 Como Rodar o Backend

### Pré-requisitos
- Java JDK 21
- Maven

### Instalação
1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU-USUARIO/TaskManager-AppSec.git](https://github.com/SEU-USUARIO/TaskManager-AppSec.git)