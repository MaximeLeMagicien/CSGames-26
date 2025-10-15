# CS Qualif Step 2 - Device Management API

## Stories done
1. Enregistrement d'un appareil TV
   - All done, just need to handle the last exception
     - 🔴 400 Bad Request - Valeur de firmwareVersion, location ou timezone non supporté

## Prerequisites
install poetry
```bash
curl -sSL https://install.python-poetry.org | python3 -
```
or 
```bash
pip install poetry
```

## Project Setup

Poetry will automatically create a virtual environment and install all dependencies:
```bash
poetry install
```

### Run the Application

```bash
poetry run python main.py
```

## Project Structure

```
cs-qualif-step2/
├── main.py                     # Application entry point
├── pyproject.toml             # Poetry configuration and dependencies
├── poetry.lock               # Lock file with exact dependency versions
└── cs_qualif_step2/          # Main application package
    ├── config/               # Configuration modules
    ├── core/                 # Core application logic
    │   ├── api/             # API layer (routes and handlers)
    │   ├── application/     # Application services
    │   ├── domain/          # Domain models and business logic
    │   └── infra/           # Infrastructure layer
```
