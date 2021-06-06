#!/bin/bash

docker container stop alga-commerce-db || true && \
docker container rm alga-commerce-db || true && \
docker container run --name alga-commerce-db \
 -e MYSQL_ROOT_PASSWORD=root \
 -e MYSQL_DATABASE=algaworks_ecommerce \
 -p 3306:3306 \
 -d mysql:8.0.25 --character-set-server=utf8 --collation-server=utf8_unicode_ci
