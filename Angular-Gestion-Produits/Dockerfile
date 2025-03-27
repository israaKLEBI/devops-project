FROM node:18-alpine AS build
WORKDIR /app
COPY . .
RUN npm install -g @angular/cli
RUN npm run build -- --configuration=production



EXPOSE 4200
CMD ["ng","serve","--host", "0.0.0.0"]
