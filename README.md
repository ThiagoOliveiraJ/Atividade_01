# 🌦️ Atividade 01 - API REST de Clima com Spring Boot

API REST desenvolvida em **Java + Spring Boot** que consulta a API pública **[Open-Meteo](https://open-meteo.com/)** e disponibiliza informações meteorológicas de **Belo Horizonte - MG** (e, opcionalmente, de outras cidades) através de endpoints próprios.

> Atividade em dupla (Pair Programming) — 2,5 pts.

## 📊 Sumário

- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Como executar localmente](#-como-executar-localmente)
- [Configuração da API Key](#-configuração-da-api-key)
- [Endpoints disponíveis](#-endpoints-disponíveis)
- [Limitações conhecidas](#-limitações-conhecidas)
- [Integrantes](#-integrantes)

## 🛠 Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.1.1** (`spring-boot-starter-webmvc`)
- **Maven** (com Maven Wrapper — `mvnw` / `mvnw.cmd`)
- **RestTemplate** para consumo da API externa
- **[Open-Meteo](https://open-meteo.com/)** como provedor de dados meteorológicos (gratuita, sem necessidade de API Key)

## 📦 Estrutura do projeto

```text
src/
└── main/
    ├── java/
    │   └── com/example/clima/
    │       ├── ClimaApplication.java     # Classe principal (main)
    │       ├── controller/
    │       │   └── ClimaController.java  # Endpoints REST
    │       ├── service/
    │       │   └── ClimaService.java     # Consumo da API externa (Open-Meteo)
    │       └── dto/
    │           └── ClimaResponse.java    # Modelo de dados da resposta do clima
    └── resources/
        └── application.properties
```

## ▶️ Como executar localmente

### Pré-requisitos

- **JDK 25** instalado (`java -version`)
- Não é necessário ter o Maven instalado globalmente — o projeto usa o Maven Wrapper.

### Passo a passo

1. Clone o repositório:

   ```bash
   git clone <URL-DO-REPOSITORIO>
   cd Atividade_01
   ```

2. Execute a aplicação com o Maven Wrapper:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows:

   ```bash
   mvnw.cmd spring-boot:run
   ```

3. A aplicação sobe por padrão em:

   ```text
   http://localhost:8080
   ```

4. Teste os endpoints (via navegador, `curl` ou Postman):

   ```bash
   curl http://localhost:8080/clima
   curl http://localhost:8080/clima/rio-de-janeiro
   ```

### Rodando os testes

```bash
./mvnw test
```

## 🔑 Configuração da API Key

Este projeto utiliza a **Open-Meteo**, que **não exige API Key** para os usos implementados (previsão do tempo e geocodificação de cidades). Por isso, nenhuma chave precisa ser configurada em `application.properties`.

Caso a dupla opte por migrar para outro provedor (OpenWeather, WeatherAPI, Tomorrow.io etc.), a chave deve ser adicionada em `src/main/resources/application.properties`, por exemplo:

```properties
clima.api.key=${CLIMA_API_KEY}
```

e fornecida via variável de ambiente (`CLIMA_API_KEY`), evitando expor a chave diretamente no código-fonte ou no repositório.

## 🌐 Endpoints disponíveis

### `GET /clima`

Retorna os dados meteorológicos de **Belo Horizonte - MG**, consultados diretamente na Open-Meteo.

**Exemplo de requisição:**

```http
GET /clima
```

**Exemplo de resposta (JSON bruto retornado pela Open-Meteo):**

```json
{
  "latitude": -19.555,
  "longitude": -49.555,
  "hourly": {
    "time": ["2026-08-26T00:00", "2026-08-26T01:00", "..."],
    "temperature_2m": [18.4, 17.9, "..."]
  }
}
```

### `GET /clima/{cidade}`

Consulta a geolocalização e os dados meteorológicos de qualquer cidade informada no path, usando a API de geocodificação da Open-Meteo.

**Exemplo de requisição:**

```http
GET /clima/rio-de-janeiro
```

> Cidades com mais de uma palavra devem ser informadas separadas por hífen (ex.: `sao-paulo`, `belo-horizonte`), pois o serviço substitui `-` por espaço antes de consultar a API externa.

## ⚠️ Limitações conhecidas

Este é o estado atual da implementação — pontos que podem ser evoluídos como desafio extra:

- Os endpoints retornam o **JSON bruto** devolvido pela Open-Meteo (`String`), em vez de mapear a resposta para o DTO `ClimaResponse` já criado em `dto/ClimaResponse.java`.
- As coordenadas fixas usadas em `GET /clima` (`ClimaService.BASE_URL_BH`) devem ser conferidas/ajustadas para as coordenadas reais de Belo Horizonte (aprox. `-19.9167, -43.9345`).
- O tratamento de erros cobre apenas o código de status HTTP da resposta; falhas de conexão (timeout, host indisponível) ainda não são tratadas com uma exceção customizada.
- `GET /clima/{cidade}` retorna atualmente apenas o resultado da geocodificação (nome, latitude/longitude), sem os dados de temperatura da cidade pesquisada.

## ⭐ Possíveis melhorias (desafio extra)

- Mapear a resposta da Open-Meteo para o DTO `ClimaResponse`, retornando um JSON estruturado e mais legível.
- Buscar as coordenadas via geocodificação e, em seguida, consultar o clima real da cidade em `GET /clima/{cidade}`.
- Adicionar previsão para os próximos dias (`daily` na Open-Meteo).
- Implementar tratamento de exceções (`@ControllerAdvice`) para respostas de erro padronizadas.

## 👥 Integrantes

- Thiago Junio Oliveira da Silva
- Arthur Augusto Silva Conceição

## 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.
