# Tender

Gift card platform with a merchant portal. *Personal learning project.*

## Services
| Service           | Purpose                                                       | Status      |
|-------------------|---------------------------------------------------------------|-------------|
| card-service      | Merchants, stores, gift cards: issue, balance, redeem, reload | In progress |
| ledger-service    | Transaction history from events                               | Planned     |
| reporting-service | SQL reports: liability, breakage, settlement                  | Planned     |
| portal            | React merchant portal                                         | Planned     |

## Stack
Java 21 · Spring Boot · PostgreSQL · Flyway · Kafka · Keycloak · Docker · GitHub Actions · React

## Run locally
- Prerequisites: Java 21 and Docker.

```bash
docker compose up -d
./gradlew build
./gradlew :card-service:bootRun
```

## API testing
- Requires the [Bruno](https://www.usebruno.com/) app
- Open the collection folder `bruno/`
- Select the `local` environment
- Start the app (see [Run locally](#run-locally))
- Right-click **card-service** → **Run**; every request has assertions

## Docs
- Architecture decisions: docs/adr/
- SQL reports: docs/sql/
