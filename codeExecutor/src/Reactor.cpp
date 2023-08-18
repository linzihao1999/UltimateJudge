#include <netinet/in.h>
#include <cstdio>
#include <unistd.h>
#include "Reactor.h"

Reactor::Reactor() {
    listenAddr = nullptr;
    listenSocket = -1;
}

void Reactor::run() {
    ReactorStatus status = initSocket();
    if (status == ReactorStatus::ERROR)
        return;

}

ReactorStatus Reactor::initSocket() {
    if (listenAddr == nullptr) {
        printf("No ListenAddr");
        return ReactorStatus::ERROR;
    }
    listenSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (listenSocket == -1) {
        perror("Error creating socket\n");
        return ReactorStatus::ERROR;
    }
    if (bind(listenSocket, (struct sockaddr *) listenAddr, sizeof(*listenAddr)) == -1) {
        perror("Error binding");
        close(listenSocket);
        return ReactorStatus::ERROR;
    }
    if (listen(listenSocket, MAX_CONNECTIONS) == -1) {
        perror("Error listening");
        close(listenSocket);
        return ReactorStatus::ERROR;
    }
    printf("Successfully listen on port: %d\n", ntohl(listenAddr->sin_port));
    return ReactorStatus::OK;
}

void Reactor::setListenAddr(sockaddr_in *in) {
    listenAddr = in;
}

Reactor::~Reactor() {
    delete listenAddr;
}
