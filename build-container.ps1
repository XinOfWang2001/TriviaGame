aws ecr get-login-password | docker login --username AWS --password-stdin 683210040241.dkr.ecr.eu-north-1.amazonaws.com

./gradlew bootJar

docker build -t trivia-api .

docker tag trivia-api:latest 683210040241.dkr.ecr.eu-north-1.amazonaws.com/trivia/base-api:latest