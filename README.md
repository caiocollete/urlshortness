# URL Shortness API

Uma API simples para encurtamento de URLs desenvolvida com Spring Boot.

## 📋 Descrição

Esta API permite criar URLs encurtadas e recuperar URLs originais através de IDs. O sistema utiliza um banco de dados para armazenar as URLs e retorna IDs únicos que podem ser usados para acessar as URLs originais.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **H2 Database** (configurável)
- **Maven**

## 📚 Endpoints da API

### Base URL

```
http://localhost:8080/api/short
```

### 1. Salvar URL (Criar URL encurtada)

**Endpoint:** `POST /api/short`

**Parâmetros:**

- `url` (String): URL original que será encurtada

**Exemplo de requisição:**

```bash
curl -X POST "http://localhost:8080/api/short?url=https://www.google.com"
```

**Resposta de sucesso:**

```
HTTP 200 OK
1
```

**Nota:** O endpoint retorna o ID da URL salva. Use este ID para recuperar a URL original posteriormente.

**Resposta de erro:**

```
HTTP 204 No Content
```

### 2. Recuperar URL Original

**Endpoint:** `GET /api/short`

**Parâmetros:**

- `id` (Long): ID da URL encurtada

**Exemplo de requisição:**

```bash
curl "http://localhost:8080/api/short?id=1"
```

**Resposta de sucesso:**

```
HTTP 200 OK
"https://www.google.com"
```

**Resposta quando ID não encontrado:**

```
HTTP 204 No Content
```

## ⚠️ **IMPORTANTE PARA O FRONTEND**

**Para recuperar a URL original, basta chamar a API passando o ID retornado!**

### Fluxo Completo:

1. **Salvar URL** → Recebe o ID da URL salva
2. **Usar o ID** → Chama a API com o ID para recuperar a URL original

### Exemplo de uso no Frontend:

```javascript
// Exemplo em JavaScript
async function getOriginalUrl(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/short?id=${id}`);
    if (response.ok) {
      const originalUrl = await response.text();
      console.log("URL Original:", originalUrl);
      // Redirecionar ou usar a URL original
      window.location.href = originalUrl;
    } else {
      console.log("URL não encontrada");
    }
  } catch (error) {
    console.error("Erro ao buscar URL:", error);
  }
}

// Uso: getOriginalUrl(1);
```

```typescript
// Exemplo em TypeScript
async function getOriginalUrl(id: number): Promise<string | null> {
  try {
    const response = await fetch(`http://localhost:8080/api/short?id=${id}`);
    if (response.ok) {
      return await response.text();
    }
    return null;
  } catch (error) {
    console.error("Erro ao buscar URL:", error);
    return null;
  }
}
```

## 🗄️ Estrutura do Banco de Dados

A aplicação utiliza JPA com H2 Database por padrão. A tabela `url` possui:

- `id` (Long, Primary Key, Auto Increment): ID único da URL
- `original_url` (String): URL original armazenada

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/caiocollete/urlshortness/
│   │   ├── controller/
│   │   │   └── UrlShortnessController.java
│   │   ├── model/
│   │   │   └── Url.java
│   │   ├── repository/
│   │   │   └── IUrlRepository.java
│   │   ├── services/
│   │   │   └── UrlService.java
│   │   └── UrlShortnessApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/caiocollete/urlshortness/
        └── UrlShortnessApplicationTests.java
```

## 🔧 Configuração

As configurações do banco de dados podem ser ajustadas no arquivo `application.properties`:

```properties
# Configurações do banco de dados
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (opcional)
spring.h2.console.enabled=true
```

## 🧪 Testes

Para executar os testes:

```bash
./mvnw test
```

## 📝 Exemplos de Uso Completo

### 1. Criar uma URL encurtada:

```bash
curl -X POST "http://localhost:8080/api/short?url=https://www.github.com"
```

### 2. Recuperar a URL original usando o ID:

```bash
curl "http://localhost:8080/api/short?id=1"
```

### 3. Fluxo completo no Frontend:

```javascript
// 1. Salvar URL
async function saveUrl(originalUrl) {
  const response = await fetch(
    `http://localhost:8080/api/short?url=${encodeURIComponent(originalUrl)}`,
    {
      method: "POST",
    }
  );
  if (response.ok) {
    const id = await response.text();
    return parseInt(id);
  }
  return null;
}

// 2. Recuperar URL original
async function getOriginalUrl(id) {
  const response = await fetch(`http://localhost:8080/api/short?id=${id}`);
  if (response.ok) {
    return await response.text();
  }
  return null;
}

// Exemplo de uso
saveUrl("https://www.example.com").then((id) => {
  if (id) {
    console.log("URL salva com sucesso! ID:", id);
    // Agora você pode usar este ID para recuperar a URL original
    getOriginalUrl(id).then((originalUrl) => {
      console.log("URL Original recuperada:", originalUrl);
    });
  } else {
    console.log("Erro ao salvar URL");
  }
});
```

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**Caio Collete**

---

**Lembre-se: Para recuperar a URL original, basta chamar a API passando o ID!**
