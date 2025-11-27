FROM nginx:latest
COPY demo/conf/nginx /etc/nginx
COPY demo/data/nginx /usr/share/nginx