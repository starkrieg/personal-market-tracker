::
echo Building a new data loader image...
:: Build new Data Loader image
cd DataETL
docker build -f ./Docker/Dockerfile -t market-data-loader:latest .
:: Reset path
cd ..

echo Building a new data server image...
:: Build new Data Server image
cd DataServer
docker build -f ./docker/dockerfile -t my-market-viewer:latest .
:: Reset path
cd ..

echo Composing solution...
:: Run docker compose
docker-compose -p stock-market-data -f .\docker-compose.yml up -d