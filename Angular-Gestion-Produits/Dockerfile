FROM node:18-alpine AS build
RUN npm install -g @angular/cli
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build -- --configuration=production



EXPOSE 4200
CMD ["ng","serve","--host", "0.0.0.0"]
