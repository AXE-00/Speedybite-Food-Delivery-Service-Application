FROM node:16-alpine as build-step
LABEL authors="AXE"
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install latest
COPY . /app
RUN npm run build -c production

FROM nginx:1.20.1
COPY --from=build-step /app/dist/food-delivery-app-front-end /usr/share/nginx/html
EXPOSE 4200:80

####Run "ng build -c production" before running compose.yml#####
