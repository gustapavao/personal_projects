docker compose down

# Build
docker build -t backend-projects:latest ./backend

# Start environment
docker compose up --build --force-recreate --remove-orphans