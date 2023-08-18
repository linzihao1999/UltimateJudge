#include <netinet/in.h>
#include <cstdio>
#include <unistd.h>
#include <arpa/inet.h>
#include "Acceptor.h"

Connection Acceptor::waitAndAcceptConnection(int listenSocket) {
    Connection connection;
    sockaddr_in clientAddr{};
    socklen_t clientAddrLen = sizeof(clientAddr);
    connection.fd = accept(listenSocket, (struct sockaddr *) &clientAddr, &clientAddrLen);
    if (connection.fd == -1) {
        printf("Error accepting connection\n");
        close(listenSocket);
        connection.status = AcceptorStatus::ERROR;
        return connection;
    }
    printf("Accepted connection from %s:%u\n", inet_ntoa(clientAddr.sin_addr), ntohs(clientAddr.sin_port));
    connection.status = AcceptorStatus::ACCEPT_CONNECTION;
    return connection;
}
