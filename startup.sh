#!/usr/bin/env bash

docker build -t ancina/games .

docker run -d -p 8080:8080 -t ancina/games