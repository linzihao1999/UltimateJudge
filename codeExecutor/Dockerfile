FROM ubuntu:latest
LABEL authors="Zihao Lin <104664078@163.com>"

WORKDIR /root
ENV CODE_EXECUTOR_PORT=9125
COPY . .

RUN apt-get update && apt-get install -y g++ cmake
RUN cmake ./ && make

EXPOSE $CODE_EXECUTOR_PORT

ENTRYPOINT ["./executor"]

