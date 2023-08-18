#include <netinet/in.h>
#include <cstdio>
#include <unistd.h>
#include <sys/epoll.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <bits/stdc++.h>
#include "Reactor.h"

Reactor::Reactor() {
    listenAddr = nullptr;
    listenFD = -1;
    epollFD = epoll_create(MAX_CONNECTIONS);
}

void Reactor::run() {
    ReactorStatus status = initSocket();
    if (status == ReactorStatus::ERROR)
        return;

    if (epollFD == -1) {
        printf("Fail to create epoll\n");
        return;
    }

    auto &&listenEvent = epoll_event{EPOLLIN | EPOLLET | EPOLLRDHUP, {.fd=listenFD}};
    if (epoll_ctl(epollFD, EPOLL_CTL_ADD, listenFD, &listenEvent) == -1) {
        printf("Fail to add listen addr to epoll\n");
        return;
    }

    epoll_event eventList[MAX_CONNECTIONS];
    while (true) {
        int ret = epoll_wait(epollFD, eventList, MAX_CONNECTIONS, TIMEOUT);
        if (ret < 0) {
            printf("Epoll error\n");
            break;
        } else if (ret == 0) {
            printf("Epoll timeout\n");
            continue;
        }

        for (auto event: eventList) {
            if (event.data.fd == listenFD) {
                Connection connection = Acceptor::waitAndAcceptConnection(listenFD);
                auto &&newEvent = epoll_event{EPOLLIN | EPOLLET, {.fd=connection.fd}};
                epoll_ctl(epollFD, EPOLL_CTL_ADD, connection.fd, &newEvent);
            } else {
                char buffer[4096];
                long bytesRead;
                bytesRead = recv(event.data.fd, buffer, sizeof(buffer), 0);
                if (bytesRead <= 0) {
                    printf("Error receiving data\n");
                    break;
                } else {
                    buffer[bytesRead] = '\0';
                    std::cout << "Received: " << buffer << std::endl;
                    if (strncmp(buffer, "ping", 4) == 0) {
                        std::cout << "send pong" << std::endl;
                        send(event.data.fd, "pong", 4, 0);
                    }
                }
            }
        }
    }
}

ReactorStatus Reactor::initSocket() {
    if (listenAddr == nullptr) {
        printf("No ListenAddr");
        return ReactorStatus::ERROR;
    }
    listenFD = socket(AF_INET, SOCK_STREAM, 0);
    if (listenFD == -1) {
        perror("Error creating socket\n");
        return ReactorStatus::ERROR;
    }
    if (bind(listenFD, (struct sockaddr *) listenAddr, sizeof(*listenAddr)) == -1) {
        perror("Error binding");
        close(listenFD);
        return ReactorStatus::ERROR;
    }
    if (listen(listenFD, MAX_CONNECTIONS) == -1) {
        perror("Error listening");
        close(listenFD);
        return ReactorStatus::ERROR;
    }
    printf("Successfully listen on port: %d\n", ntohs(listenAddr->sin_port));
    return ReactorStatus::OK;
}

void Reactor::setListenAddr(sockaddr_in *in) {
    listenAddr = in;
}

Reactor::~Reactor() {
    delete listenAddr;
}
